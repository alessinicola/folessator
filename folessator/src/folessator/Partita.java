package folessator;

import folessator.sparql.endpoints.SparqlEndpoint;

public interface Partita {

	public String getNextTopic();
	public void setAnswer(String topic,Answer answer);	
	public String getGuess();
	public SparqlEndpoint getEndpoint();
	
}
