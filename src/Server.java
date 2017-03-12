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
	ServerSocket servSockRecievingResponse;
	RequestingForPair forPair=null;
	private ArrayList<ClientThread> clientThreadList = new ArrayList<>();

	Server() {
		try {
			ServSock = new ServerSocket(33333);
			servSockClientName = new ServerSocket(44444);
			servSockRequestingPair=new ServerSocket(55555);
			System.out.println("i am at line 94 Server");
			servSockRecievingResponse = new ServerSocket(50000);
			//object for requesting pair client
			
			forPair=new RequestingForPair( clientThreadList);
			System.out.println("Server running at port 33333");
			ClientNameThread m2 = new ClientNameThread(clientThreadList);// ?????
			m2.start();
			while (true) {
				ClientThread clientThread = new ClientThread(ServSock.accept(), servSockClientName.accept(),servSockRequestingPair.accept(),servSockRecievingResponse.accept(),
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
	Socket SocketRequestingPair=null;
	ObjectOutputStream oosRequestPair=null;
	ObjectInputStream oisRequestPair=null;
	Socket SocketrecievingResponse=null;
	ObjectOutputStream oosResponsePair=null;
	ObjectInputStream oisResponsePair=null;
	ClientThread(Socket client, Socket clientNameSocket, Socket SocketRequestingPair , Socket SocketrecievingResponse, ArrayList<ClientThread> clientThreadList, RequestingForPair requestingForPair) {
		try {
			
			this.clientNameSocket = clientNameSocket;
			this.clientThreadList = clientThreadList;
			this.ClientSock = client;
			this.requestingForPair=requestingForPair;
			this.SocketRequestingPair=SocketRequestingPair;
			this.SocketrecievingResponse=SocketrecievingResponse;
			this.oosRequestPair=new ObjectOutputStream(SocketRequestingPair.getOutputStream());
			this.oisRequestPair = new ObjectInputStream(SocketRequestingPair.getInputStream());
			this.oosResponsePair=new ObjectOutputStream(SocketrecievingResponse.getOutputStream());
			this.oisResponsePair=new ObjectInputStream(SocketrecievingResponse.getInputStream());
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
		
			try{
				ClientThread clientThreadToPair=requestingForPair.matchingPair(oisRequestPair);
				requestingForPair.sendingRequestForPair(clientThreadToPair, this);
				System.out.println("Name send from client "+clientThreadToPair.getName());
				//receiving response from userClient 2
				requestingForPair.recievingResponse(clientThreadToPair);
			}
			catch(NullPointerException nl){
				System.err.println("NO NAME SEND error at SERVER LINe 109");
			}
		

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
							System.out.println(t);
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
