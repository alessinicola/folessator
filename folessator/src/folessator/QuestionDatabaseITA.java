package folessator;

import java.io.Serializable;
import java.util.HashMap;

public class QuestionDatabaseITA implements Serializable, QuestionDatabase {

	private static final long serialVersionUID = 1L;
	private HashMap<String,String> questionMap;
	
	
	@Override
	public String getQuestion(String topic) {
		return topic+"trololol";
	}
	

}
