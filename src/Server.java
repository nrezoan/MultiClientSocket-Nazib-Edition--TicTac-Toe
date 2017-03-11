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
	private ServerSocket servSockRequestingPair;
	RequestingForPair forPair=null;
	private ArrayList<ClientThread> clientThreadList = new ArrayList<>();

	Server() {
		try {
			ServSock = new ServerSocket(33333);
			servSockClientName = new ServerSocket(44444);
			servSockRequestingPair=new ServerSocket(55555);
			//object for requesting pair client
			forPair=new RequestingForPair(servSockRequestingPair.accept(), clientThreadList);
			System.out.println("Server running at port 33333");
			ClientNameThread m2 = new ClientNameThread(clientThreadList);// ?????
			m2.start();
			while (true) {
				ClientThread clientThread = new ClientThread(ServSock.accept(), servSockClientName.accept(),
						clientThreadList,forPair);
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
	Socket clientNameSocket;
	
	private Thread thr;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	ArrayList<ClientThread> clientThreadList;
	ObjectOutputStream oosForClient;
	static int client_count = 0;
	boolean nameFlag = false;
	RequestingForPair requestingForPair=null;

	ClientThread(Socket client, Socket clientNameSocket, ArrayList<ClientThread> clientThreadList, RequestingForPair requestingForPair) {
		try {
			this.clientNameSocket = clientNameSocket;
			this.clientThreadList = clientThreadList;
			this.ClientSock = client;
			this.requestingForPair=requestingForPair;

			client_count++;
			oos = new ObjectOutputStream(ClientSock.getOutputStream());

			ois = new ObjectInputStream(ClientSock.getInputStream());

			oosForClient = new ObjectOutputStream(clientNameSocket.getOutputStream());
			String t = (String) ois.readObject();

			this.thr = new Thread(this, t);

			clientThreadList.add(this);
			/*
			 * block will decide who will chat with
			 * 
			 * 
			 */
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
						return;

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
