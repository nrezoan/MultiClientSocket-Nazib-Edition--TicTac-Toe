import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Color;
import javax.swing.SwingConstants;

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
	private JScrollPane scrollPane_1;
	private JList<String> list;
	DefaultListModel<String> model; 
	protected static ArrayList<String> clientList;

	public ChatWindow(final ObjectOutputStream oos) {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		msgText = new JTextField();
		msgText.setBounds(10, 290, 298, 35);
		contentPane.add(msgText);
		msgText.setColumns(10);

		msgSend = new JButton("SEND");
		msgSend.setBounds(318, 290, 106, 35);
		msgSend.addActionListener(this);
		this.oos=oos;

		contentPane.add(msgSend);
		setTitle("Tic Tac Toe- " + UserRegistration.getUserName());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 414, 228);
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
		
		
		
		JLabel lblNewLabel = new JLabel("Online Users");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(434, 21, 130, 19);
		contentPane.add(lblNewLabel);
		
		
		model = new DefaultListModel<String>();
	    list = new JList<String>(model);
	    
	    list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!e.getValueIsAdjusting()){
					String temp=list.getSelectedValue().toString();
					setText(temp);
				}
				
			}
		});
		//model element has been added to the refresh button below
	    //if button is pressed all online user is shown
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				for(int i=0;i<clientList.size();i++){
					System.out.println("line 120");
					model.addElement(clientList.get(i));
					
				}
				
			}
		});
		btnRefresh.setBounds(493, 290, 71, 35);
		contentPane.add(btnRefresh);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(434, 51, 130, 228);
		contentPane.add(scrollPane_1);
		
		
		
		scrollPane_1.setViewportView(list);
		
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
/**
 * This method will set Text to the main J Text Area
 * @param temp
 */
	public static void setText(String temp) {
		if(wholeText.equals("")){
			wholeText=temp;
		}else{
			wholeText += "\n" + temp;
		}
		msgArea.setText(wholeText);
	}

	/**
	 * 
	 * @param list ArrayList will bring the list to Chat Window
	 */
	public static void setList(ArrayList<String> list){
		clientList=list;
	}
	

	

	
}
