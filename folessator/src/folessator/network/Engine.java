package folessator.network;

import java.net.ServerSocket;
import java.net.Socket;

import folessator.Answer;
import folessator.Partita;
import folessator.QuestionDatabase;
import folessator.yago.PartitaSPARQL;
import folessator.yago.QuestionDatabaseENG;

import java.io.*;

public class Engine {

	private static int serverPort = 5829;
	private static ServerSocket serverSocket = null;

	public static void main(String[] args) throws IOException {

		serverSocket = new ServerSocket(serverPort);

		while (true) 
			{
				try 
				{
					Socket clientSocket = null;
					clientSocket = serverSocket.accept();
					new Thread(new GameThread(clientSocket)).start();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
					break;
				}
	
			}
		System.out.println("Server Stopped.");
	}

	
}
/*
 *  THREADS
 * */
class GameThread implements Runnable {

	protected Socket clientSocket = null;

	public GameThread(Socket clientSocket)
		{
		this.clientSocket = clientSocket;		
		}
	
	public void run() {		
		System.out.println("CREATO THREAD");		
			try {
				
				InputStream input = clientSocket.getInputStream();
				OutputStream output = clientSocket.getOutputStream();
				DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
				DataInputStream inFromClient = new DataInputStream(clientSocket.getInputStream());
				String answerStr;
				String question = null;
				String topic = "";
				
				String language=NetworkTool.readUTF8(inFromClient);
				System.out.println("lingua:"+language);
				
				//TODO: introdurre pattern factory...
				Partita partita= new PartitaSPARQL();
				//
				
				QuestionDatabase database= QuestionDatabaseENG.loadDatabaseFromFile(language);
				
				Answer answer= Answer.UNKNOWN;
				while(  (answer!= Answer.ABORT && !topic.contains("GUESS")) || 
						(topic.contains("GUESS") && answer== Answer.NO) ) 
					{					
					NetworkTool.writeBoolean(true, outToClient);
					
					topic= partita.getNextTopic();
					question= database.getQuestion(topic);						
					
					NetworkTool.writeUTF8(question, outToClient);
					
					answerStr=NetworkTool.readUTF8(inFromClient);
					answer=Answer.convert(answerStr);
					
					partita.setAnswer(topic, answer);
					}
				NetworkTool.writeBoolean(false, outToClient);
				NetworkTool.writeUTF8("gameover", outToClient);
				outToClient.close();
				output.close();
				input.close();				
				} 
			catch (IOException e) 
				{
				// report exception somewhere.
				e.printStackTrace();
				}
			System.out.println("closing thread...");
	}




}
