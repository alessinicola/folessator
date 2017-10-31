package test;

import java.io.*;
//import java.util.Scanner;

import folessator.QuestionDatabase;
import folessator.yago.QuestionDatabaseENG;
import folessator.yago.QuestionDatabaseITA;

public class SecondMain {
	
	//private static final String QuestionDatabaseENG = null;

	static public void main(String...argv) {
	//String category, question;
	QuestionDatabaseITA db = new QuestionDatabaseITA();
	
	//QuestionDatabase db= QuestionDatabase.loadDatabaseFromFile("ENG");
	
	
//			
	
//	Scanner reader = new Scanner(System.in);
//	System.out.println("inserisci la categoria");
//	category=reader.nextLine();
//	do {
//	System.out.println("inserisci la domanda relativa alla categoria "+ category);
//	question=reader.nextLine();
//	db.putQuestion(category, question);
//	System.out.println("inserisci la categoria");
//	category=reader.nextLine();
//	} while(!category.equals("stop"));
//	reader.close();
	
	
	


	ObjectOutputStream output = null;
	
	try {
		//output = new ObjectOutputStream(new FileOutputStream("./src/folessator/yago/db.bat"));
		output = new ObjectOutputStream(new FileOutputStream("dbITA.bat"));
		output.writeObject(db);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//db.getLabel("http://yago-knowledge.org/resource/wordnet_defender_109614684");
	//((QuestionDatabaseENG)db).stampa();	
}

	
  

    
	
	
	
}

