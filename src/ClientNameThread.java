import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientNameThread extends Thread {

	
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

		while (true) {
			try {
				int clientNumberUpdated = clientThreadList.size();

				if (clientNumber != clientNumberUpdated) {
					sendingAllClients(appendString());

					clientNumber = clientNumberUpdated;
				}

				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void sendingAllClients(String str) {

		for (int i = 0; i < clientThreadList.size(); i++) {
			// sending client list to all clients
			try {
				 clientThreadList.get(i).oosForClient.writeObject(allClients);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

	public synchronized String appendString() {
		allClients = "";
		for (int i = 0; i < clientThreadList.size(); i++) {

			allClients = allClients + " " + clientThreadList.get(i).getName();
		}
		
		return allClients;
	}

}
