package folessator;

import org.apache.jena.query.*;

public class FolessatorMain {
	
	
	
    static public void main(String...argv) {
    Partita partita= new PartitaSPARQL();
    String topic;

    /*mtching obama*/
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_contestant_109613191", Answer.YES);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_intellectual_109621545", Answer.YES);
    partita.setAnswer("http://yago-knowledge.org/resource/wikicat_American_people_of_Irish_descent", Answer.YES);
    partita.setAnswer("http://yago-knowledge.org/resource/wikicat_American_people_of_Scottish_descent", Answer.YES);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_communicator_109610660", Answer.YES);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_worker_109632518", Answer.YES);
    partita.setAnswer("http://yago-knowledge.org/resource/wikicat_Living_people", Answer.YES);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_writer_110794014", Answer.YES);

    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_cricketer_109977326", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_medalist_110305062", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_entertainer_109616922", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_athlete_109820263", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_delegate_110000787", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_player_110439851", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_football_player_110101634", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_rival_110533013", Answer.NO);
    partita.setAnswer("http://yago-knowledge.org/resource/wordnet_journalist_110224578", Answer.NO);
    /**/
    
    topic=partita.getGuessedThing();
    
    System.out.println("topic:"+ topic);
    
    }
}