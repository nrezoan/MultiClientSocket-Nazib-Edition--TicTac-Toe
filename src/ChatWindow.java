import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Color;

public class ChatWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static private JTextField msgText; // the text field to type message
	static private JTextArea msgArea; // displays entire chat
	static private JButton msgSend;
	static private JButton btnLogout;
	static private String wholeText = "";
	static private String mgsOut = "";
	boolean actionPerformed = false;
	private ObjectOutputStream oos=null;
	private JButton btnViewThreads;

	public ChatWindow(final ObjectOutputStream oos) {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		msgText = new JTextField();
		msgText.setBounds(10, 215, 298, 35);
		contentPane.add(msgText);
		msgText.setColumns(10);

		msgSend = new JButton("SEND");
		msgSend.setBounds(318, 215, 106, 35);
		msgSend.addActionListener(this);
		this.oos=oos;

		contentPane.add(msgSend);
		setTitle("Tic Tac Toe- " + UserRegistration.getUserName());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 414, 153);
		contentPane.add(scrollPane);
		
		msgArea = new JTextArea();
		scrollPane.setViewportView(msgArea);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s ="exit";
				try {
					oos.writeObject(s);
				} catch (Exception e2) {
					
				}
				System.exit(0);
                              
			}
		});
		btnLogout.setBackground(Color.LIGHT_GRAY);
		btnLogout.setBounds(339, 17, 85, 23);
		contentPane.add(btnLogout);
		
		JLabel name = new JLabel();
		name.setText(UserRegistration.getUserName());
		name.setBounds(297, 21, 46, 14);
		contentPane.add(name);
		
		btnViewThreads = new JButton("View Threads");
		btnViewThreads.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				DefaultListModel<ServerThread> model = new DefaultListModel<ServerThread>();
//				for(ServerThread s:ServerThread.st){
//				    model.addElement(s);
//				}
//				OnlineChatWindow.onlineList = new JList<ServerThread>(model);
				
				 
				 
			}
		});
		
		btnViewThreads.setBounds(10, 17, 89, 23);
		contentPane.add(btnViewThreads);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.actionPerformed=true;
		this.mgsOut =(String) msgText.getText();
		try {
			oos.writeObject(mgsOut);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		msgText.setText(null);//emptying the typing area		
		// TODO Auto-generated method stub
	}

	public static void setText(String temp) {
		if(wholeText.equals("")){
			wholeText=temp;
		}else{
			wholeText += "\n" + temp;
		}
		msgArea.setText(wholeText);
	}

	public String getText() {
		return mgsOut;
	}

	public boolean isActionPerformed() {
		return actionPerformed;
	}

	public void setActionPerformed(boolean bal) {
		actionPerformed = bal;
	}
}
