import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ReadThread implements Runnable {
	private Thread thr;
	ObjectInputStream ois;
	ChatWindow chatWindow;
	private ArrayList nameList;

	public ReadThread(ObjectInputStream ois, ChatWindow chatWindow) {
		this.ois = ois;
		this.thr = new Thread(this);
		this.chatWindow = chatWindow;
		thr.start();

	}

	public void run() {
		try {

			// String clientNameFirst = (String) ois.readObject();
			// nameList = new
			// ArrayList<String>(Arrays.asList(clientNameFirst.split(" ")));
			// chatWindow.setList(nameList);
			// if (clientNameFirst != null) {
			// System.out.println(clientNameFirst);
			// }
			while (true) {

				String clientName = (String) ois.readObject();
				System.out.println(clientName);
				nameList = new ArrayList<String>(Arrays.asList(clientName.split(" ")));
			//	chatWindow.clientListName=clientName;
				

				String senderName = (String) ois.readObject();
				String message = (String) ois.readObject();
				if (message != null) {
					// sending the text to chat window
					chatWindow.setText(senderName + ": " + message);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
