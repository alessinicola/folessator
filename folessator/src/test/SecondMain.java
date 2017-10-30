package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import folessator.yago.QuestionDatabaseITA;

public class SecondMain {
	
	static public void main(String...argv) {
	String category, question;
//	QuestionDatabaseITA db = new QuestionDatabaseITA();
	
	ObjectInputStream ois=null;
	try {
		ois=new ObjectInputStream(new FileInputStream("db.bat"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	QuestionDatabaseITA db= null;
	try {
		db=(QuestionDatabaseITA)ois.readObject();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
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
		output = new ObjectOutputStream(new FileOutputStream("C:/Users/Daniele/git/folessator/folessator/db.bat"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		output.writeObject(db);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//db.getLabel("http://yago-knowledge.org/resource/wordnet_defender_109614684");
	db.stampa();	
}
	
}

