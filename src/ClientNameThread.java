import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientNameThread extends Thread {

	ClientThread clientThread;
	String allClients = "";

	ArrayList<ClientThread> clientThreadList;

	public ClientNameThread(ArrayList<ClientThread> clientThreadList) {
		// TODO Auto-generated constructor stub

		this.clientThreadList = clientThreadList;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		int clientNumber = clientThreadList.size();
<<<<<<< HEAD

		while (true) {
			try {
=======
		while (true) {
			
>>>>>>> origin/master
			int clientNumberUpdated = clientThreadList.size();
			
//			System.out.println("the clientNumber is " + clientNumber);
//			System.out.println("the clientNumberUpdated is " + clientNumberUpdated);
			
			if (clientNumber != clientNumberUpdated) {
<<<<<<< HEAD
				System.out.println("clientNumber " + clientNumber + "clientNumberUpdated " + clientNumberUpdated);
				sendingAllClients(appendString());
				clientNumber = clientNumberUpdated;
			} 
		
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
=======
				System.out.println("I am here");
				sendingAllClients(appendString());
				System.out.println("clientNumber "+clientNumber+"clientNumberUpdated "+clientNumberUpdated);
			    clientNumber = clientNumberUpdated;
			    try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
>>>>>>> origin/master
			}
		}
	}

	public synchronized void sendingAllClients(String str) {

		for (int i = 0; i < clientThreadList.size(); i++) {
			// sending client list to all clients
			try {
				// clientThread.clientThreadList.get(i).oosForClient.writeObject(allClients);
				System.out.println(clientThreadList.get(i).getName());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		// System.out.println("Clients Updated");
	}

	public synchronized String appendString() {
		allClients = "";
		for (int i = 0; i < clientThreadList.size(); i++) {

			allClients = allClients + " " + clientThreadList.get(i).toString();
		}
		// System.out.println("All clients are: " + allClients);
		return allClients;
	}

}
