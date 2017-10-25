package folessator;

import java.util.HashMap;
import java.util.Map;


public class PartitaSPARQL implements Partita {
	
	

	private static Map<Object, Answer> stato = new HashMap<>();
	
	
	public Answer getRisposta(Object cluster) {
		 return stato.get(cluster);
		 }
	
	
//	public HashMap<Object, Answer> getStorico() {
//		return new HashMap<Object,Answer>(stato);
//	}
	@Override
	public String getNextTopic() {
		return "SCIAOBELLO!";
	}
	@Override
	public void setAnswer(String topic, Answer answer) {
		
		stato.put(topic,answer);
	}
	@Override
	public void changeAnswer(String topic, Answer answer) {
		stato.replace(topic, answer);

		
	}
	
	
}