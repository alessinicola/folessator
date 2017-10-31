package folessator.yago;


import java.io.Serializable;

import java.util.*;
import org.apache.jena.query.*;

import folessator.QuestionDatabase;

public class QuestionDatabaseENG extends QuestionDatabase implements Serializable {
	
	
	
	private static final long serialVersionUID = 1L;

	protected String getTag(String topic) {
		String result=topic;
		boolean haveGuess= topic.startsWith("GUESS");
		
		result=result.replaceAll("^.*\\/|[0-9]+$","");
		result=result.replaceAll("wordnet_", "");
		result=result.replaceAll("wikicat_", "");
		result=result.replaceAll("_", " ");
		result=result.trim();
		
		if(haveGuess)
			result="I got it! are you thinking of "+result+"?";
		else
			result="Does your character have something to do with " + result + "?";
		
		//System.out.println("result" + result +" topic" +topic);
		return result;
	}
	
	protected String getLabel(String topic) {
		String result=null;
		String queryForLabel = "" + 
		"select distinct ?LABEL "
				+"{ "
				+"<"+ topic +"> ?a ?LABEL ."
				+"FILTER (lang(?LABEL) = 'eng')"
				+ "}";
		System.out.println(queryForLabel);
		Query query = QueryFactory.create(PartitaSPARQL.QUERY_PREFIX+queryForLabel);
				
        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService(PartitaSPARQL.serverAddress, query) )
        	{
        		ResultSet rs = qexec.execSelect();
	            
	            List<String> queryResults=new ArrayList<String>();
	    		while (rs.hasNext()) {
	    			QuerySolution soln = rs.nextSolution();
	                String label=soln.get( "LABEL" ).toString().replaceAll("@eng$", "");            
	    			queryResults.add(label);
	                System.out.println(label);
	    		}
	    		
	    		if(queryResults.size()>=1)
	    			result=queryResults.get(0);
	    		if(queryResults.size()>=2)
	    			result=result+" or a " + queryResults.get(1);	            
	            
        	}
	   catch (Exception e) 	
        	{
		   	e.printStackTrace();
        	}		
		if (result!=null)
			result= "is your character a " + result + "?";
		
		return result;
	}

	public void stampa() {
		for (String topic : questionMap.keySet()) {
			System.out.println("" + topic  + " " + questionMap.get(topic));
			
		}
	}

	
	

}
