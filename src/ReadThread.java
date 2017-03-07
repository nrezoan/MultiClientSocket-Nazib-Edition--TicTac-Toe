import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadThread implements Runnable {
	private Thread thr;
	ObjectInputStream ois;
	ChatWindow chatWindow;

	public ReadThread(ObjectInputStream ois,ChatWindow chatWindow) {
		this.ois = ois;
		this.thr = new Thread(this);
		this.chatWindow=chatWindow;
		thr.start();

	}

	public void run() {
		try {
			ArrayList nameList;
			String clientNameFirst=(String)ois.readObject();
			 nameList = new ArrayList<String>(Arrays.asList(clientNameFirst.split(" ")));
			if(clientNameFirst!=null){
				System.out.println(clientNameFirst);
			}
			while (true) {
				String clientName=(String)ois.readObject();
				nameList = new ArrayList<String>(Arrays.asList(clientNameFirst.split(" ")));
				if(clientName!=null){
					System.out.println(clientName);
				}
				String senderName = (String) ois.readObject();
				String message=(String)ois.readObject();
				if (message != null) {			
					// sending the text to chatwindow
					chatWindow.setText(senderName+": "+message);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
}

