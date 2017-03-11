import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;

public class Client {

	public static void main(String args[]) {
		ObjectOutputStream oos = null;
		ObjectOutputStream oosPairRequest=null;
		ObjectInputStream ois = null;
		ObjectInputStream ois2TakingUserName = null;
		ChatWindow chatWindow = null;
		UserRegistration userRegistration = null;
		// connecting to the server
		String serverAddress = "127.0.0.1";
		int serverPort1 = 33333;
		int serverPort2 = 44444;
		int serverPort3=55555;
		try {
			Socket client = new Socket(serverAddress, serverPort1);
			Socket clientNameSocket = new Socket(serverAddress, serverPort2);
			Socket clientPairRequestSocket=new Socket(serverAddress,serverPort3);
			Scanner input = new Scanner(System.in);
			// connections established
			oos = new ObjectOutputStream(client.getOutputStream());
			oosPairRequest=new ObjectOutputStream(clientPairRequestSocket.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			ois2TakingUserName = new ObjectInputStream(clientNameSocket.getInputStream());
			
			chatWindow=new ChatWindow(oos,oosPairRequest);
			userRegistration = new UserRegistration(chatWindow,oos);
			userRegistration.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("error at 32");
			e.printStackTrace();
		}
		new ReadThread(ois, chatWindow);
		new ReadClientNameThread(ois2TakingUserName, chatWindow).start();
	}

}
