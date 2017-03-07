import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JScrollBar;

public class OnlineChatWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JList<ServerThread> onlineList ;
	
	/**
	 * constructor 
	 */
	public OnlineChatWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPlayersOnline = new JLabel("Players Online");
		lblPlayersOnline.setBounds(175, 11, 71, 23);
		contentPane.add(lblPlayersOnline);
		
//		DefaultListModel<ServerThread> model = new DefaultListModel<ServerThread>();
//		for(ServerThread s:ServerThread.st){
//		    model.addElement(s);
//		}
//	    onlineList = new JList<ServerThread>(model);
		
        onlineList= new JList(ServerThread.st.toArray());
		onlineList.setBounds(10, 42, 414, 208);
		contentPane.add(onlineList);

	
		
		
	}
}
