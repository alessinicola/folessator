package folessator.sparql.endpoints;

public class YagoEndpoint implements SparqlEndpoint{
	private static final String serverAddress="https://linkeddata1.calcul.u-psud.fr/sparql";
	
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

	@Override
	public String getQueryPrefix() {
		return QUERY_PREFIX;
	}

	@Override
	public String getPersonTopic() {
		return "yago:wordnet_person_100007846";
	}

	@Override
	public String getServerAddress() {
		return  serverAddress;
	}

	@Override
	public boolean isTopicInvalid(String topic) {
		return true;
	}

	
}
