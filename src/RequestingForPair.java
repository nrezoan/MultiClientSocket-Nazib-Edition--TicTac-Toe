import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class RequestingForPair {

	String userName;
	ArrayList<ClientThread> clientThreadList;

	public RequestingForPair(ArrayList<ClientThread> clientThreadList) {
		this.clientThreadList = clientThreadList;
	}

	/**
	 * 
	 * @param name
	 *            the name to be searched
	 * @return the object of the client thread which will have the matched user
	 *         name
	 * @throws NullPointerException
	 *             when no name match with no name
	 * @throws IOException
	 *             from ObjectOutput stream
	 * @throws ClassNotFoundException
	 */
	public ClientThread matchingPair(ObjectInputStream oisPair) throws NullPointerException {
		String name = null;
		try {
			name = (String) oisPair.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("error at REQUESTING FOR PAIR LINE 39");
		} catch (IOException e) {
			System.err.println("error at REQUESTING FOR PAIR LINE 39");
			// TODO Auto-generated catch block

		}
		ClientThread clientThread = null;
		for (int i = 0; i < clientThreadList.size(); i++) {
			if (clientThreadList.get(i).getName().equals(name)) {
				clientThread = clientThreadList.get(i);
			}
		}
		return clientThread;
	}

	public void sendingRequestForPair(ClientThread clientThreadToSend, ClientThread clientThreadFromSend) {
		try {
			clientThreadToSend.oosRequestPair
					.writeObject("Recieved pair request from " + clientThreadFromSend.getName());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean recievingResponse(ClientThread clientThreadToSend) {
		String response = "";
		try {
			response = (String) clientThreadToSend.oisResponsePair.readObject();

		} catch (ClassNotFoundException e) {
			System.err.println("error at RequestingForPair at line 66");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("error at RequestingForPair at line 70");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response.equals("yes")) {
			return true;
		} else {
			return false;
		}
	}

}
