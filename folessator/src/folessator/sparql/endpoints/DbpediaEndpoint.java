package folessator.sparql.endpoints;

public class DbpediaEndpoint implements SparqlEndpoint {
	private static final String serverAddress="https://dbpedia.org/sparql";
	private static final String QUERY_PREFIX=""+
			"	PREFIX yago: <http://dbpedia.org/class/yago/>\n"+
			"	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" ;
			

	@Override
	public String getQueryPrefix() {
		return QUERY_PREFIX;
	}

	@Override
	public String getPersonTopic() {
		return "yago:Person100007846";
	}

	@Override
	public String getServerAddress() {
		return serverAddress;
	}

	@Override
	public boolean isTopicInvalid(String topic) {
		return !topic.contains("yago");
	}

}
