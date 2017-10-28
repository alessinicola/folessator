package folessator;

import java.io.Serializable;
import java.util.HashMap;

public class QuestionDatabaseITA implements Serializable, QuestionDatabase {

	private static final long serialVersionUID = 1L;
	private HashMap<String,String> questionMap = new HashMap<String, String>();
	
	
	@Override
	public  String getQuestion(String topic) {
		//return questionMap.get(topic);
		return topic+" QUESTIONDB";
	}
	
	public void stampa() {
		for (String topic : questionMap.keySet()) {
			System.out.println("" + topic  + " " + questionMap.get(topic));
			
		}
	}

	public void putQuestion(String topic, String question) {
		questionMap.put(topic, question);
	}
	

}
