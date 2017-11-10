package folessator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;

import folessator.sparql.endpoints.SparqlEndpoint;


public abstract class QuestionDatabase implements Serializable{

	
	private static final long serialVersionUID = 1L;
	protected HashMap<String,String> questionMap = new HashMap<String, String>();

	public  String getQuestion(String topic,SparqlEndpoint sparqlEndpoint) {
		String label;
		if (questionMap.containsKey(topic))
			return questionMap.get(topic);
		
		if ((label=getLabel(topic,sparqlEndpoint))!=null)
			return label;
		
		return getTag(topic);
		}
	
	public static QuestionDatabase loadDatabaseFromFile(String language) {
		try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("db"+language.toUpperCase()+".bat"));
			QuestionDatabase db=(QuestionDatabase)ois.readObject();
			ois.close();
			return db;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void putQuestion(String topic, String question) {
		questionMap.put(topic, question);
	}
	
	protected abstract String getLabel(String topic,SparqlEndpoint sparqlEndpoint);
	protected abstract String getTag(String topic);
	
}
