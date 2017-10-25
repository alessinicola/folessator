package folessator;

import java.util.ArrayList;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

public class FolessatorMain {
    static public void main(String...argv) {
        String queryStr = ""
        		+ ""
        		+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
        		"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
        		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
        		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
        		"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
        		"PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" + 
        		"PREFIX : <http://dbpedia.org/resource/>\n" + 
        		"PREFIX dbpedia2: <http://dbpedia.org/property/>\n" + 
        		"PREFIX dbpedia: <http://dbpedia.org/>\n" + 
        		"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"
        		+ ""
        		+ ""
        		+ ""
        		+ "select ?categoria (replace( str(?categoria) , \"^.*/|[0-9]+$\", \"\") as ?Cluster) (count(?categoria) as ?TOTAL) (abs(count(?categoria)/1243399.0 - 0.5) as ?DISTANCE) \n" + 
        		"\n" + 
        		"where {\n" + 
        		"?URI rdf:type foaf:Person.\n" + 
        		"?URI rdf:type ?categoria.\n" + 
        		"\n" + 
        		"\n" + 
        		"\n" + 
        		"}\n" + 
        		"GROUP BY ?categoria\n" + 
        		"ORDER BY (?DISTANCE)\n" + 
        		"#ORDER BY DESC (?TOTAL)\n" + 
        		"\n" + 
        		"\n" + 
        		"LIMIT 100"
        		+ ""
        		+ ""
        		+ ""
        		
        		;
        
        
        
        queryStr =""				
        		+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
        		"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
        		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
        		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
        		"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
        		"PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" + 
        		"PREFIX : <http://dbpedia.org/resource/>\n" + 
        		"PREFIX dbpedia2: <http://dbpedia.org/property/>\n" + 
        		"PREFIX dbpedia: <http://dbpedia.org/>\n" + 
        		"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"
        		+ ""
				+ "select (count (distinct ?URI) as ?TOTAL) \n" + 
				"where {\n" + 
				"?URI rdf:type foaf:Person.\n" + 
				"?URI rdf:type ?categoria.\n" + 
				"}\n"  
				+ "LIMIT 100"
				+ "";
        
        Query query = QueryFactory.create(queryStr);
        // Remote execution.
        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
            // Set the DBpedia specific timeout.
           	((QueryEngineHTTP)qexec).addParam("timeout", "1000000") ;


            // Execute.
            ResultSet rs = qexec.execSelect();
           // ResultSetFormatter.out(System.out, rs, query);
            
            
          System.out.println(rs.getResultVars());
          
          System.out.println(rs.hasNext());
            
          
          
          while ( rs.hasNext() ) {
              final QuerySolution qs = rs.next();
              System.out.println( qs.get( "TOTAL" ).asLiteral().getInt());
             
          }
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}