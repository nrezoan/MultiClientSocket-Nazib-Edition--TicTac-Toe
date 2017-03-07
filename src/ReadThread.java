//import java.io.ObjectInputStream;
//
//public class ReadThread implements Runnable {
//	private Thread thr;
//	ObjectInputStream ois;
//	ChatWindow chatWindow;
//
//	public ReadThread(ObjectInputStream ois,ChatWindow chatWindow) {
//		this.ois = ois;
//		this.thr = new Thread(this);
//		this.chatWindow=chatWindow;
//		thr.start();
//
//	}
//
//	public void run() {
//		try {
//
//			while (true) {
//				String senderName = (String) ois.readObject();
//				String message=(String)ois.readObject();
//				if (message != null) {			
//					// sending the text to chatwindow
//					chatWindow.setText(senderName+": "+message);
//				}
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//
//	}
//}
//
