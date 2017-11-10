package folessator.sparql.endpoints;

public interface SparqlEndpoint {

	public String getQueryPrefix();
	public String getPersonCategory();
	public String getServerAddress();
	public boolean isCategoryInvalid(String category);
}
