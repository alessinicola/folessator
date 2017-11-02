package folessator.network;

import java.io.*;
import java.net.*;

public class Client {

	private static BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	
	static public void main(String[] args) throws UnknownHostException, IOException {
		
		//porta 5829: "lucy" in T9
		Socket clientSocket = new Socket("localhost", 5829);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
		
		String serverLine;
		String question;
		String answer;
		String language;
		print("In che lingua vuoi giocare? Scegli tra ita e eng");
		language=getLanguage();
		
		NetworkTool.writeUTF8(language, outToServer);
		
		while(NetworkTool.readBoolean(inFromServer))
		{
			question=NetworkTool.readUTF8(inFromServer);
			
			print(question);
			answer=getAnswer();
			
			NetworkTool.writeUTF8(answer, outToServer);
		}
		serverLine=NetworkTool.readUTF8(inFromServer);
				
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
	
	private static String getLanguage() throws IOException {
		String result=null;
		
		while((result=inFromUser.readLine())==null ||
							  !(result.equalsIgnoreCase("ENG") ||
								result.equalsIgnoreCase("ITA") 
								));
		
		
		return result; 
	}

}
