package experimental;

import java.util.*;

public class Topic {

	String name;
	List<String> relatedURIs;
	boolean guess;
	
	public Topic(String name,boolean isGuess)
		{
		this.name=name;
		this.guess=isGuess;
		relatedURIs= new ArrayList<String>();
		}
	
	public void addURI(String URI) {
		relatedURIs.add(URI);
	}
	
	public boolean isGuess() {
		return guess;		
	}
	
	public List<String> getRelatedURIs() {
		return new ArrayList<String>(relatedURIs);
	}
	
}
