package folessator;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Engine {

	private static int serverPort = 6789;
	private static ServerSocket serverSocket = null;

	public static void main(String[] args) throws IOException {

		serverSocket = new ServerSocket(serverPort);

		while (true) {
			try {
				Socket clientSocket = null;
				clientSocket = serverSocket.accept();
				new Thread(new GameThread(clientSocket)).start();
				}
			catch (IOException e) 
			{
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
				
				//TODO: introdurre pattern factory...
				Partita partita= new PartitaSPARQL();
				QuestionDatabase database= new QuestionDatabaseITA();
				//
				
				String action;
				boolean gameover=false;
				String question;
				String topic;
				while(!gameover) 
					{
					outToClient.writeUTF("ok");
					
					topic= partita.getNextTopic();
					question= database.getQuestion(topic);
					
					outToClient.writeUTF(question);					
					action=inFromClient.readUTF();
					
					if(action.equals("abort"))
						{
						gameover=true;						
						}
					
				}
				outToClient.writeUTF("finePartita");
				
				outToClient.close();
				output.close();
				input.close();
				
				} 
			catch (IOException e) 
				{
				// report exception somewhere.
				e.printStackTrace();
				}
	}
}
