import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class RequestingForPair {

	Socket requestingPair;
	ObjectInputStream oisPair;
	String userName;
	ArrayList<ClientThread> clientThreadList;

	public RequestingForPair(Socket requestingPair,ArrayList<ClientThread> clientThreadList) {
		this.requestingPair = requestingPair;
		this.clientThreadList=clientThreadList;
		try {
			oisPair = new ObjectInputStream(requestingPair.getInputStream());
			userName = (String) oisPair.readObject();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param name the name to be searched
	 * @return the object of the client thread which will have the matched user name
	 * @throws NullPointerException when no name match with no name
	 */
	public ClientThread matchingPair (String name) throws NullPointerException{
		ClientThread clientThread=null;
		for (int i = 0; i < clientThreadList.size(); i++) {
			if(clientThreadList.get(i).getName().equals(name)){
				clientThread=clientThreadList.get(i);
			}
		}
		return clientThread;
	}

}
