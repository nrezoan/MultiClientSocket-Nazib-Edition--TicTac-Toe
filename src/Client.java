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
	//		while (t!=null) {  ///ekhane ki korbo??? coz ei block only once print hocche jokhon new client banano hocche
			                    // er por client update holeo r print hobe na, loop dile shob ois ekhane kaj kortese
			
			//recieves the clients list and print it
				try {
					getAllClients = (String) ois.readObject();
					System.out.println(getAllClients + "-from server to client");
				} catch (Exception e) {
					// TODO: handle exception
				}
	//		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ReadThread(ois, chatWindow);
	}

}
