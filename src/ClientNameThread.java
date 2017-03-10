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
		System.out.println();
		while (true) {
			int clientNumberUpdated = clientThreadList.size();
			
			if (clientNumber != clientNumberUpdated) {
				System.out.println("clientNumber "+clientNumber+"clientNumberUpdated "+clientNumberUpdated);
				sendingAllClients(appendString());
				clientNumber = clientNumberUpdated;
			}

		}
	}

	public void sendingAllClients(String str) {

		for (int i = 0; i < clientThreadList.size(); i++) {
			// sending client list to all clients
			try {
				 //clientThread.clientThreadList.get(i).oosForClient.writeObject(allClients);
				System.out.println(clientThreadList.get(i).getName());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		//System.out.println("Clients Updated");
	}

	public String appendString() {
		allClients = "";
		for (int i = 0; i < clientThreadList.size(); i++) {

			allClients = allClients + " " + clientThreadList.get(i).toString();
		}
		//System.out.println("All clients are: " + allClients);
		return allClients;
	}

}
