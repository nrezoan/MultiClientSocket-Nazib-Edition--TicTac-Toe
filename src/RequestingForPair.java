import java.io.IOException;
import java.io.ObjectInputStream;
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
	public ClientThread matchingPair(Socket requestingPair) throws NullPointerException {
		String name=null;
		ObjectInputStream oisPair=null;
		try {
			oisPair = new ObjectInputStream(requestingPair.getInputStream());
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

}
