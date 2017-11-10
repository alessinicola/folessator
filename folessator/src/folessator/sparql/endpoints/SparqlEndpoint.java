package folessator.sparql.endpoints;

public interface SparqlEndpoint {

	public String getQueryPrefix();
	public String getPersonTopic();
	public String getServerAddress();
	public boolean isTopicInvalid(String topic);
}
