package folessator;

import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

public class FolessatorMain {
    static public void main(String...argv) {
        String queryStr = "PREFIX foaf:  <http://xmlns.com/foaf/0.1/>"
        		+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
        		+ "select ?URI ?categoria "
        		+ "where {"
        		+ "?URI rdf:type foaf:Person."
        		+ "?URI rdf:type ?categoria. }"
        		+ "";
        Query query = QueryFactory.create(queryStr);
        // Remote execution.
        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
            // Set the DBpedia specific timeout.
           	((QueryEngineHTTP)qexec).addParam("timeout", "1000000") ;


            // Execute.
            ResultSet rs = qexec.execSelect();
            ResultSetFormatter.out(System.out, rs, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}