import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadClientNameThread extends Thread {
	ObjectInputStream ois2TakingUserName;
	ChatWindow chatWindow;

	public ReadClientNameThread(ObjectInputStream ois2TakingUserName, ChatWindow chatWindow) {
		// TODO Auto-generated constructor stub
		this.ois2TakingUserName = ois2TakingUserName;
		this.chatWindow = chatWindow;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		while (true) {
			try {
				System.out.println("i am here dying alone");
				String clientName = (String) ois2TakingUserName.readObject();
				System.out.println(clientName);
				ArrayList<String> nameList = new ArrayList<String>(Arrays.asList(clientName.split(" ")));
				chatWindow.setList(nameList);// this will set the online user to
											// the chat window
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
}
