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
		UserRegistration userRegistration = null;
		// connecting to the server
		String serverAddress = "127.0.0.1";
		int serverPort = 33333;
		try {
			Socket client = new Socket(serverAddress, serverPort);
			Scanner input = new Scanner(System.in);
			// connections established
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			userRegistration = new UserRegistration(oos);
			userRegistration.setVisible(true);
			String getAllClients=null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ReadThread(ois, chatWindow);
	}

}
