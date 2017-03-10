import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;

public class Client {

	public static void main(String args[]) {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		ChatWindow chatWindow = null;
		ObjectInputStream ois2TakingUserName = null;
		UserRegistration userRegistration = null;
		// connecting to the server
		String serverAddress = "127.0.0.1";
		int serverPort1 = 33333;
		int serverPort2 = 44444;
		try {
			Socket client = new Socket(serverAddress, serverPort1);
			Socket clientNameSocket = new Socket(serverAddress, serverPort2);
			Scanner input = new Scanner(System.in);
			// connections established
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			ois2TakingUserName = new ObjectInputStream(clientNameSocket.getInputStream());
			userRegistration = new UserRegistration(oos);
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
