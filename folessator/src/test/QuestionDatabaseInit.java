package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import folessator.sparql.QuestionDatabaseENG;
import folessator.sparql.QuestionDatabaseITA;

public class QuestionDatabaseInit {

	public static void main(String[] args) {
		QuestionDatabaseITA db = new QuestionDatabaseITA();
		ObjectOutputStream output = null;
		
		try {
			output = new ObjectOutputStream(new FileOutputStream("dbITA.bat"));
			output.writeObject(db);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		QuestionDatabaseENG dbEng = new QuestionDatabaseENG();
		
		
		try {
			output = new ObjectOutputStream(new FileOutputStream("dbENG.bat"));
			output.writeObject(dbEng);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
