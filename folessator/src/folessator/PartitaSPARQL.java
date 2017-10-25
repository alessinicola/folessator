package folessator;

import java.util.HashMap;
import java.util.Map;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;


public class PartitaSPARQL implements Partita {
	
	

	private static Map<Object, Answer> stato = new HashMap<>();
	private static final String serverAddress="localhost";
	
	private static final String QUERY_PREFIX=	"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
									    		"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
									    		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
									    		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
									    		"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
									    		"PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" + 
									    		"PREFIX : <http://dbpedia.org/resource/>\n" + 
									    		"PREFIX dbpedia2: <http://dbpedia.org/property/>\n" + 
									    		"PREFIX dbpedia: <http://dbpedia.org/>\n" + 
									    		"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>";
	
	
	public Answer getRisposta(Object cluster) {
		 return stato.get(cluster);
		 }
	

	@Override
	public String getNextTopic() {
		int personCount= getPersonCount("");
		
		getAlternatives("", personCount);
		
		return "SCIAOBELLO! person Count="+personCount;
	}
	@Override
	public void setAnswer(String topic, Answer answer) {
		
		stato.put(topic,answer);
	}
	@Override
	public void changeAnswer(String topic, Answer answer) {
		stato.replace(topic, answer);

		
	}
	
	
	
	/*SPARQL QUERIES*/
	private int getPersonCount(String filters) {
		String queryStr = QUERY_PREFIX +    		
					"select (count (distinct ?URI) as ?TOTAL) \n" + 
					"where {\n" + 
					"?URI rdf:type foaf:Person.\n" + 
					"?URI rdf:type ?categoria.\n" +
					filters +
					"\n}\n"+  
					"LIMIT 100"+
					"";
		
		Query query = QueryFactory.create(queryStr);
        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) )
        	{
	            // Set the DBpedia specific timeout.
        		((QueryEngineHTTP)qexec).addParam("timeout", "1000000") ;
	            // Execute.
	            ResultSet rs = qexec.execSelect();
	            QuerySolution qs = rs.next();
	            return qs.get( "TOTAL" ).asLiteral().getInt();
        	}
	   catch (Exception e) 	
        	{
		   	e.printStackTrace();
        	}
		
		return 0;
	}
	
	private void getAlternatives(String filters,int TOTAL) {
		
		
		
		String queryStr = QUERY_PREFIX +""
				+ "" +
				"select ?categoria (replace( str(?categoria) , \"^.*/|[0-9]+$\", \"\") as ?TOPIC) (count(?categoria) as ?TOTAL) (abs(count(?categoria)/"+TOTAL+".0 - 0.5) as ?DISTANCE) \n" + 
				"\n" + 
				"where {\n" + 
				"?URI rdf:type foaf:Person.\n" + 
				"?URI rdf:type ?categoria.\n" + 
				filters + 
				"}\n" + 
				"GROUP BY ?categoria\n" + 
				"ORDER BY (?DISTANCE)\n" + 
				"#ORDER BY DESC (?TOTAL)\n" + 
				"\n" + 
				"\n" + 
				"LIMIT 100"
				+ "";
	
	Query query = QueryFactory.create(queryStr);
    try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) )
    	{
            // Set the DBpedia specific timeout.
    		((QueryEngineHTTP)qexec).addParam("timeout", "1000000") ;
            // Execute.
            ResultSet rs = qexec.execSelect();
            //QuerySolution qs = rs.next();
            ResultSetFormatter.out(System.out, rs, query);
           // return qs.get( "TOTAL" ).asLiteral().getInt();
    	}
   catch (Exception e) 	
    	{
	   	e.printStackTrace();
    	}
		
		
		
	}
	
}