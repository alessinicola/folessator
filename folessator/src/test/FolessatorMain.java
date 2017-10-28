package test;

import java.util.*;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import folessator.Answer;
import folessator.Partita;
import folessator.PartitaSPARQL;

//import org.apache.jena.query.*;

public class FolessatorMain {
	
	
	
    static public void main(String...argv) {
    Partita partita= new PartitaSPARQL();
    String topic;

    /*mtching obama*/
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_contestant_109613191", Answer.YES);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_intellectual_109621545", Answer.YES);
    partita.setAnswer("http://yago-knowledge.org/resource/wikicat_American_people_of_Irish_descent", Answer.YES);
    partita.setAnswer("http://yago-knowledge.org/resource/wikicat_American_people_of_Scottish_descent", Answer.YES);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_communicator_109610660", Answer.YES);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_worker_109632518", Answer.YES);
    partita.setAnswer("http://yago-knowledge.org/resource/wikicat_Living_people", Answer.YES);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_writer_110794014", Answer.YES);

    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_cricketer_109977326", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_medalist_110305062", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_entertainer_109616922", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_athlete_109820263", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_delegate_110000787", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_player_110439851", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_football_player_110101634", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_rival_110533013", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_journalist_110224578", Answer.NO);
    /**/
    
    //topic=partita.getGuess();
    
    //System.out.println("topic:"+ topic);
    
    List<String> list= new ArrayList<String>();
    if(list.size()>0)
    	list.get(0);
    
    
    String queryStr = ""+
			"select ?categoria (count(?URI) as ?TOTAL)\n" + 
			"where\n"+
			"{\n" + 
			"?URI rdf:type ?categoria.\n" + 
			//"FILTER ( ?categoria IN ( <http://yago-knowledge.org/resource/wikicat_Living_people>,<http://yago-knowledge.org/resource/wordnet_intellectual_109621545>,<http://yago-knowledge.org/resource/wordnet_communicator_109610660>,<http://yago-knowledge.org/resource/wordnet_writer_110794014>,<http://yago-knowledge.org/resource/wordnet_journalist_110224578>,<http://yago-knowledge.org/resource/wordnet_columnist_109939154>,<http://yago-knowledge.org/resource/wikicat_American_columnists>,<http://yago-knowledge.org/resource/wikicat_American_political_writers>,<http://yago-knowledge.org/resource/wikicat_American_political_pundits>,<http://yago-knowledge.org/resource/wikicat_American_political_journalists>))"+
			"}\n" + 
			"GROUP BY ?categoria\n" + 
			"ORDER BY (count(?URI))\n"+
			"LIMIT 100"
			+ "";    		
			
    queryStr = ""+
    		"select ?categoria (count(?uuuri) as ?TOTAL)\n" + 
			"where\n"+
			"{\n" + 
			"?uuuri rdf:type ?categoria.\n" + 
			"FILTER ( ?categoria IN ( <http://yago-knowledge.org/resource/wikicat_Living_people>,<http://yago-knowledge.org/resource/wordnet_intellectual_109621545>,<http://yago-knowledge.org/resource/wordnet_communicator_109610660>,<http://yago-knowledge.org/resource/wordnet_writer_110794014>,<http://yago-knowledge.org/resource/wordnet_journalist_110224578>,<http://yago-knowledge.org/resource/wordnet_columnist_109939154>,<http://yago-knowledge.org/resource/wikicat_American_columnists>,<http://yago-knowledge.org/resource/wikicat_American_political_writers>,<http://yago-knowledge.org/resource/wikicat_American_political_pundits>,<http://yago-knowledge.org/resource/wikicat_American_political_journalists>))"+
			"}\n" + 
			"GROUP BY ?categoria\n" + 
			"ORDER BY ( ?TOTAL ) \n"+
			"LIMIT 100"
			+ "";    	
System.out.println("GET_PERSON_COUNT:\n"+queryStr);

Query query = QueryFactory.create(QUERY_PREFIX+queryStr);
try ( QueryExecution qexec = QueryExecutionFactory.sparqlService(serverAddress, query) )
	{
		//((QueryEngineHTTP)qexec).addParam("timeout", "1000000") ;
        // Execute.
        ResultSet rs = qexec.execSelect();
        QuerySolution qs = rs.next();
        String categoria=qs.get( "categoria" ).toString(); 
       System.out.println(categoria);
	}
catch (Exception e) 	
	{
   	e.printStackTrace();
	}
    
    }
	private static final String serverAddress="https://linkeddata1.calcul.u-psud.fr/sparql";

    /*SPARQL QUERIES*/
	private static final String QUERY_PREFIX=""+
			"	PREFIX bif: <bif:>\n" + 
			"	PREFIX dawgt: <http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#>\n" + 
			"	PREFIX dbpedia: <http://dbpedia.org/resource/>\n" + 
			"	PREFIX dbpprop: <http://dbpedia.org/property/>\n" + 
			"	PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" + 
			"	PREFIX fn: <http://www.w3.org/2005/xpath-functions/#>\n" + 
			"	PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"	PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" + 
			"	PREFIX go: <http://purl.org/obo/owl/GO#>\n" + 
			"	PREFIX ldp: <http://www.w3.org/ns/ldp#>\n" + 
			"	PREFIX math: <http://www.w3.org/2000/10/swap/math#>\n" + 
			"	PREFIX mesh: <http://purl.org/commons/record/mesh/>\n" + 
			"	PREFIX mf: <http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#>\n" + 
			"	PREFIX nci: <http://ncicb.nci.nih.gov/xml/owl/EVS/Thesaurus.owl#>\n" + 
			"	PREFIX obo: <http://www.geneontology.org/formats/oboInOwl#>\n" + 
			"	PREFIX ogc: <http://www.opengis.net/>\n" + 
			"	PREFIX ogcgml: <http://www.opengis.net/ont/gml#>\n" + 
			"	PREFIX ogcgs: <http://www.opengis.net/ont/geosparql#>\n" + 
			"	PREFIX ogcgsf: <http://www.opengis.net/def/function/geosparql/>\n" + 
			"	PREFIX ogcgsr: <http://www.opengis.net/def/rule/geosparql/>\n" + 
			"	PREFIX ogcsf: <http://www.opengis.net/ont/sf#>\n" + 
			"	PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"	PREFIX product: <http://www.buy.com/rss/module/productV2/>\n" + 
			"	PREFIX protseq: <http://purl.org/science/protein/bysequence/>\n" + 
			"	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
			"	PREFIX rdfa: <http://www.w3.org/ns/rdfa#>\n" + 
			"	PREFIX rdfdf: <http://www.openlinksw.com/virtrdf-data-formats#>\n" + 
			"	PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"	PREFIX sc: <http://purl.org/science/owl/sciencecommons/>\n" + 
			"	PREFIX scovo: <http://purl.org/NET/scovo#>\n" + 
			"	PREFIX sd: <http://www.w3.org/ns/sparql-service-description#>\n" + 
			"	PREFIX sioc: <http://rdfs.org/sioc/ns#>\n" + 
			"	PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" + 
			"	PREFIX sql: <sql:>\n" + 
			"	PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#>\n" + 
			"	PREFIX vcard2006: <http://www.w3.org/2006/vcard/ns#>\n" + 
			"	PREFIX virtcxml: <http://www.openlinksw.com/schemas/virtcxml#>\n" + 
			"	PREFIX virtrdf: <http://www.openlinksw.com/schemas/virtrdf#>\n" + 
			"	PREFIX void: <http://rdfs.org/ns/void#>\n" + 
			"	PREFIX xf: <http://www.w3.org/2004/07/xpath-functions>\n" + 
			"	PREFIX xml: <http://www.w3.org/XML/1998/namespace>\n" + 
			"	PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
			"	PREFIX xsl10: <http://www.w3.org/XSL/Transform/1.0>\n" + 
			"	PREFIX xsl1999: <http://www.w3.org/1999/XSL/Transform>\n" + 
			"	PREFIX xslwd: <http://www.w3.org/TR/WD-xsl>\n" + 
			"	PREFIX yago: <http://yago-knowledge.org/resource/>\n"
			+ " ";
}