import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

	private ServerSocket ServSock;

	Server() {
		try {

			ServSock = new ServerSocket(33333);
			System.out.println("Server running at port 33333");

			// OnlineChatWindow onlineChatWindow = new OnlineChatWindow();
			// onlineChatWindow.setVisible(true);

			while (true) {
				ServerThread m = new ServerThread(ServSock.accept());
			}
		} catch (Exception e) {
			System.out.println("Server starts:" + e);
		}
	}

	public static void main(String args[]) throws UnknownHostException, IOException {
		Server objServer = new Server();
	}
}

class ServerThread implements Runnable {

	private Socket ClientSock;
	private Thread thr;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	static ArrayList<ServerThread> st = new ArrayList<>();
	static int client_count = 0;
	String allClients = "";

	ServerThread(Socket client) {
		try {
			this.ClientSock = client;
			st.add(this);
			client_count++;
			oos = new ObjectOutputStream(ClientSock.getOutputStream());
			ois = new ObjectInputStream(ClientSock.getInputStream());
			this.thr = new Thread(this);
			thr.start();

		} catch (Exception ex) {

		}
	}

	public void run() {

		try {
			// reading the name from UserRegistraion window
			String t = (String) ois.readObject();
			this.thr.setName(t);
			updateClientList();
			
		} catch (Exception e) {

		}
		// printing the user list in the begining of
		// creating a user thread in server
		// and sending the list to all clients

		while (true) {

			try {
				String t = (String) ois.readObject();
				if (t != null) {
					if (t.equals("exit")) {
						st.remove(this);
						System.out.println("Object is removed");
					}
					// sending the list of userName to the client
					// to let them make the window of active people

					updateClientList();

					// this thread has sent the message to
					// all client

					System.out.println("" + this.thr.getName() + ": " + t);
					for (int i = 0; i < st.size(); i++) {
						st.get(i).oos.writeObject(this.thr.getName());
						// sending one client name and
						// message to all other client
						st.get(i).oos.writeObject(t);
					}
				}

			} catch (Exception ex) {

			}

		}

	}

	public String toString() {
		return this.thr.getName();
	}

	public void updateClientList() {
		
		allClients="";
		for (int i = 0; i < st.size(); i++) {
			
			allClients = allClients + " " + st.get(i);
		}
		System.out.println("All clients are: " + allClients);

		for (int i = 0; i < st.size(); i++) {
			// sending client list to all clients
			try {
				st.get(i).oos.writeObject(allClients);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("Clients Updated");
	}
}
