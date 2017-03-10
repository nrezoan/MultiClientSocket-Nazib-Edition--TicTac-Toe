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
	private ServerSocket servSockClientName;
	private ArrayList<ClientThread> clientThreadList = new ArrayList<>();

	Server() {
		try {
			ServSock = new ServerSocket(33333);
			servSockClientName = new ServerSocket(44444);
			System.out.println("Server running at port 33333");
			ClientNameThread m2 = new ClientNameThread(clientThreadList);// ?????
			m2.start();
			while (true) {
				ClientThread clientThread = new ClientThread(ServSock.accept(), servSockClientName.accept(),
						clientThreadList);
			}

		} catch (Exception e) {
			System.err.println("error at 31");
		}
	}

	public static void main(String args[]) throws UnknownHostException, IOException {
		Server objServer = new Server();
	}
}

class ClientThread implements Runnable {

	private Socket ClientSock;
	private Thread thr;
	Socket clientNameSocket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	ArrayList<ClientThread> clientThreadList;
	ObjectOutputStream oosForClient;
	static int client_count = 0;
	boolean nameFlag = false;

	ClientThread(Socket client, Socket clientNameSocket, ArrayList<ClientThread> clientThreadList) {
		try {
			this.clientNameSocket = clientNameSocket;
			this.clientThreadList = clientThreadList;
			this.ClientSock = client;

			client_count++;
			oos = new ObjectOutputStream(ClientSock.getOutputStream());

			ois = new ObjectInputStream(ClientSock.getInputStream());

			oosForClient = new ObjectOutputStream(clientNameSocket.getOutputStream());
			String t = (String) ois.readObject();
			this.thr = new Thread(this, t);

			clientThreadList.add(this);
			thr.start();
		} catch (Exception ex) {
			System.err.println("error at 32");
		}
	}

	public void run() {

		while (true) {

			try {

				String t = (String) ois.readObject();
				if (t != null) {
					if (t.equals("exit")) {
						clientThreadList.remove(this);
						System.out.println("Object is removed");
					}

					// this thread has sent the message to
					// all client
					else {
						for (int i = 0; i < clientThreadList.size(); i++) {
							clientThreadList.get(i).oos.writeObject(this.thr.getName());
							// sending one client name and
							// message to all other client
							clientThreadList.get(i).oos.writeObject(t);
						}

					}
				}

			}

			catch (Exception ex) {
				System.err.println("error at 103");
			}

		}
	}

	public String getName() {
		return this.thr.getName();
	}

	public String toString() {
		return this.thr.getName();
	}

	public boolean isNameSet() {
		return nameFlag;
	}

	public void setName(String name) {
		this.thr.setName(name);
	}

}
