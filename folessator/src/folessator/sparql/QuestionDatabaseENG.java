package folessator.sparql;


import java.io.Serializable;

import java.util.*;
import org.apache.jena.query.*;

import folessator.QuestionDatabase;
import folessator.sparql.endpoints.SparqlEndpoint;

public class QuestionDatabaseENG extends QuestionDatabase implements Serializable {
	
	
	
	private static final long serialVersionUID = 1L;

	protected String getTag(String topic) {
		String result=topic;
		boolean haveGuess= topic.startsWith("GUESS");
		
		result=result.replaceAll("^.*\\/|[0-9]+$","");
		result=result.replaceAll("wordnet_", "");
		result=result.replaceAll("wikicat_", "");
		result=result.replaceAll("Wikicat", "");
		result=result.replaceAll("_", " ");
		result=splitCamelCase(result);
		result=result.trim();
		
		if(haveGuess)
			result="I got it! are you thinking of "+result+"?";
		else
			result="Does your character have something to do with " + result + "?";
		
		return result;
	}
	
	protected String getLabel(String topic,SparqlEndpoint sparqlEndpoint) {
		String result=null;
		String queryForLabel = "" + 
		"select distinct ?LABEL \n"
				+"where\n"
				+"{ \n"
				+"<"+ topic +"> ?a ?LABEL .\n"
				+"FILTER (lang(?LABEL) = 'eng')\n"
				+ "}";
		System.out.println(queryForLabel);
		Query query = QueryFactory.create(sparqlEndpoint.getQueryPrefix()+queryForLabel);
				
        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpoint.getServerAddress(), query) )
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

	private static String splitCamelCase(String s) {
		   return s.replaceAll(
		      String.format("%s|%s|%s",
		         "(?<=[A-Z])(?=[A-Z][a-z])",
		         "(?<=[^A-Z])(?=[A-Z])",
		         "(?<=[A-Za-z])(?=[^A-Za-z])"
		      ),
		      " "
		   );
		}
	

}
