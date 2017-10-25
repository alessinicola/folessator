package folessator;

import java.util.HashMap;
import java.util.Map;


public class Partita {
	
	
	public enum Answer {
		YES, NO, MAYBE;
		@Override
		public String toString() {
			   return super.toString().toLowerCase();
		}
	}
	
	private static Map<Object, Answer> stato = new HashMap<>();
	
	public void addRisposta(Object cluster, Answer risposta) {
		stato.put(cluster, risposta);
		}
	public Answer getRisposta(Object cluster) {
		 return stato.get(cluster);
		 }
	public void changeAnswer(Object cluster, Answer risposta) {
		stato.replace(cluster, risposta);
	}
	public int numeroDomande() {
		return stato.size();
	}
	public HashMap<Object, Answer> getStorico() {
		return new HashMap<Object,Answer>(stato);
	}
	
	
}