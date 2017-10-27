package folessator;

import java.io.*;
import java.net.*;

public class Client {

	private static BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	
	static public void main(String[] args) throws UnknownHostException, IOException {
		
		
		Socket clientSocket = new Socket("localhost", 6789);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		//BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());

		
		String serverLine;
		String question;
		String answer;
		
		while((serverLine=inFromServer.readUTF()).equals("ok"))
		{
			question=inFromServer.readUTF();
			
			print(question);
			answer=getAnswer();
			
			outToServer.writeUTF(answer);
		}
		
		print(serverLine);				
		clientSocket.close();
	}
	
	
	
	private static void print(String str) {
		System.out.println(str);
	}
	private static String getAnswer() throws IOException {
		String result=null;
		
		while((result=inFromUser.readLine())==null ||
							  !(result.equalsIgnoreCase("yes") ||
								result.equalsIgnoreCase("no")  ||
								result.equalsIgnoreCase("maybe") ||
								result.equalsIgnoreCase("abort") 								
								));
		
		
		return result; 
	}

}
