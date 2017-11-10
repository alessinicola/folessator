package folessator.sparql;

import java.util.*;
import org.apache.jena.query.*;

import folessator.Answer;
import folessator.Partita;
import folessator.sparql.endpoints.*;

public class PartitaSPARQL implements Partita {
	

	private Map<String, Answer> answers = new LinkedHashMap<>();
	//private SparqlEndpoint sparqlEndpoint= new YagoEndpoint();
	private SparqlEndpoint sparqlEndpoint= new DbpediaEndpoint();
	
	@Override
	public String getNextTopic() {	
		
		int personCount= getPersonCount();
		String topic=null;
		
		if(personCount!=1) //don't have a guess, getting next topic			
			topic=getNextTopic(personCount);			
		else
			{
			topic="GUESS="+getGuess();	
			System.out.println(topic);
			
			/* 
			 * nel caso in cui abbia sbagliato persona
			 * considera solo le risposte migliori e riparte
			 * con l'algoritmo 
			 * */
			String filter=getINFilters();			
			List<String> orderedRelevantAnswers=getRelevantAnswers(filter);
			answers = new LinkedHashMap<>();
			
		
			int keep=3;
			for(int i=0;i<keep && i<orderedRelevantAnswers.size();i++) 
				{				
				String answerStr=orderedRelevantAnswers.get(i);
				answers.put(answerStr, Answer.YES);
				}
			
		}
		return topic;
	}
	@Override
	public void setAnswer(String topic, Answer answer) {		
		answers.put(topic,answer);	
		}
		
	/*SPARQL QUERIES*/
	
	
	/*FILTERS*/
	private String getEXISTFilters() {
		String result="";
		
		for(String key: answers.keySet()) {
			Answer value=answers.get(key);
			
			if(key.contains("GUESS")) 
				continue;
			
			switch (value) {
			case NO:
				result+="FILTER NOT EXISTS { ?URI rdf:type <"+key+"> }\n";
				break;
			case YES:
				result+="FILTER EXISTS { ?URI rdf:type <"+key+"> }\n";
				break;
			default:
				break;			
			}
			
			
		}
		
		return result;
	}	
	private String getINFilters(){
		String filter="FILTER ( ?categoria IN ( ";
		boolean found=false;
		for(String key: answers.keySet())
		{
		Answer value=answers.get(key);
		if(value==Answer.YES)
			{
			if(!filter.equals("FILTER ( ?categoria IN ( ")){
				filter+=",";
			}
			filter+="<"+key+">";
			found=true;
			}
		}
		if(found)
			filter +="))\n";
		else
			filter= "";
		
		return filter;
	}
	
	private int getPersonCount() {
		
		String filters=getEXISTFilters();
		String queryStr =     		
					"select (count (?URI) as ?TOTAL) \n" + 
					"where {\n" + 
					"?URI rdf:type "+sparqlEndpoint.getPersonCategory()+" .\n" + 					
					filters +
					"\n}\n";
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());	
		System.out.println(queryStr);
		Query query = QueryFactory.create(sparqlEndpoint.getQueryPrefix()+queryStr);
        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpoint.getServerAddress(), query) )
        	{
        		//((QueryEngineHTTP)qexec).addParam("timeout", "1000000") ;
	            // Execute.
	            ResultSet rs = qexec.execSelect();
	            QuerySolution qs = rs.next();
	            int result=qs.get( "TOTAL" ).asLiteral().getInt();
	            System.out.println("query result="+result);
	            return result;
        	}
	   catch (Exception e) 	
        	{
		   	e.printStackTrace();
        	}		
		return 0;		
	}
	private String getNextTopic(int TOTAL) {
		
		String filters=getEXISTFilters();
		String queryStr = ""+				
				"select ?categoria (count(?categoria) as ?TOTAL) (abs(count(?categoria)/"+TOTAL+".0 - 0.5) as ?DISTANCE) \n" + 
				"where {\n" + 
				"?URI rdf:type "+sparqlEndpoint.getPersonCategory()+" .\n" + 
				"?URI rdf:type ?categoria .\n" + 
				filters + 
				"}\n" + 
				"GROUP BY ?categoria\n"+
				"HAVING (count(?categoria) < "+TOTAL+")\n" + 
				"ORDER BY (?DISTANCE)\n" + 				
				"\n" + 
				"\n" + 
				"LIMIT 100";
	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+queryStr);			
	Query query = QueryFactory.create(sparqlEndpoint.getQueryPrefix()+queryStr);
    try ( QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpoint.getServerAddress(), query) )
    	{    		
            ResultSet rs = qexec.execSelect();
            QuerySolution qs ;
            String categoria=null;
            Answer answer=Answer.MAYBE;
            //ResultSetFormatter.out(System.out, rs, query);         
          
            while(answer==Answer.MAYBE||sparqlEndpoint.isCategoryInvalid(categoria))
            {
            qs=rs.next();
            categoria=qs.get( "categoria" ).toString();            
            answer=answers.get(categoria);
            }
            
            return categoria;
    	}
   catch (Exception e) 	
    	{
	   	e.printStackTrace();
    	}
	return null;		
	}
	@Override
	public String getGuess() {
		String filters=getEXISTFilters();
		String queryStr=
				"select ?URI\n" + 				
				"where {\n" + 
				"?URI rdf:type "+sparqlEndpoint.getPersonCategory()+" . \n" + 
				filters+
				"}\n" + 
				"LIMIT 100";
		
		 System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+queryStr);
		
		Query query = QueryFactory.create(sparqlEndpoint.getQueryPrefix()+queryStr);
	    try ( QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpoint.getServerAddress(), query) )
	    	{
	    		//((QueryEngineHTTP)qexec).addParam("timeout", "1000000") ;
	            // Execute.
	            ResultSet rs = qexec.execSelect();
	            QuerySolution qs ;
	            String categoria=null;
	            
	            //ResultSetFormatter.out(System.out, rs, query);
	          
	            qs=rs.next();
	            categoria=qs.get( "URI" ).toString();            
	           
	           
	            
	            return categoria;
	    	}
	   catch (Exception e) 	
	    	{
		   	e.printStackTrace();
	    	}
		return null;
			
	}
	private List<String> getRelevantAnswers(String filters) {
	
	List<String> result= new ArrayList<String>();	
	String queryStr = ""+
			"select ?categoria (count(?URI) as ?TOTAL)\n" + 
			"where {\n" + 
			"?URI rdf:type ?categoria.\n" + 
			filters+
			"}\n" + 
			"GROUP BY ?categoria\n" + 
			"ORDER BY ( ?TOTAL )"
			+ "";    		
			
	
	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+queryStr);
	
	Query query = QueryFactory.create(sparqlEndpoint.getQueryPrefix()+queryStr);
    try ( QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpoint.getServerAddress(), query) )
    	{
    		ResultSet rs = qexec.execSelect();          
           
            while(rs.hasNext()) {
            	 QuerySolution qs = rs.next();
                 String categoria=qs.get( "categoria" ).toString(); 
                 result.add(categoria);
            }
            
            return result;
    	}
   catch (Exception e) 	
    	{
	   	e.printStackTrace();
    	}
	
	return null;
	}
	@Override
	public SparqlEndpoint getEndpoint() {
		return sparqlEndpoint;
	}
	
}