package folessator;

import java.util.HashMap;
import java.util.Map;

import folessator.Partita.Risposta;

public class Partita {
	
	
	public enum Risposta {
		SI, NO, FORSE;
		@Override
		public String toString() {
			   return super.toString().toLowerCase();
		}
	}
	
	private static Map<Object, Risposta> stato = new HashMap<>();
	
	public void addRisposta(Object cluster, Risposta risposta) {
		stato.put(cluster, risposta);
		}
	public Risposta getRisposta(Object cluster) {
		 return stato.get(cluster);
		 }
	public void changeRisposta(Object cluster, Risposta risposta) {
		stato.replace(cluster, risposta);
	}
	public int numeroDomande() {
		return stato.size();
	}
	public HashMap<Object, Risposta> getStorico() {
		return new HashMap<Object,Risposta>(stato);
	}
	
	
}