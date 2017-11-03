package folessator.yago;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.*;
import folessator.QuestionDatabase;

public class QuestionDatabaseITA extends QuestionDatabase {

	
	private static final long serialVersionUID = 1L;

	
	@Override
	protected String getTag(String topic) {
		String result=topic;
		boolean haveGuess= topic.startsWith("GUESS");
		
		result=result.replaceAll("^.*\\/|[0-9]+$","");
		result=result.replaceAll("wordnet_", "");
		result=result.replaceAll("wikicat_", "");
		result=result.replaceAll("_", " ");
		result=result.trim();
		
		if(haveGuess)
			result="Stai pensando a "+result+"?";
		else
			result="Il tuo personaggio ha a che fare con " + result + "?";
		
		//System.out.println("result" + result +" topic" +topic);
		return result;
	}

	
	
	@Override
	protected String getLabel(String topic) {
		String result=null;
		String queryForLabel = "" + 
		"select distinct ?LABEL "
				+"{ "
				+"<"+ topic +"> ?a ?LABEL ."
				+"FILTER (lang(?LABEL) = 'ita')"
				+ "}";
		System.out.println(queryForLabel);
		Query query = QueryFactory.create(PartitaSPARQL.QUERY_PREFIX+queryForLabel);
				
        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService(PartitaSPARQL.serverAddress, query) )
        	{
        		ResultSet rs = qexec.execSelect();
	            
	            List<String> queryResults=new ArrayList<String>();
	    		while (rs.hasNext()) {
	    			QuerySolution soln = rs.nextSolution();
	                String label=soln.get( "LABEL" ).toString().replaceAll("@ita$", "");            
	    			queryResults.add(label);
	                System.out.println(label);
	    		}
	    		
	    		if(queryResults.size()>=1)
	    			result=queryResults.get(0);
	    		if(queryResults.size()>=2)
	    			result=result+" oppure " + queryResults.get(1);	            
	            
        	}
	   catch (Exception e) 	
        	{
		   	e.printStackTrace();
        	}		
		if (result!=null)
			result= "il tuo personaggio e' un " + result + "?";
		
		return result;
	}

	
	
	
}
