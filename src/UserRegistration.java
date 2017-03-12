import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class UserRegistration extends JFrame {
	private ObjectOutputStream oos=null;
	private JTextField userNameTextField;
	private JPanel contentPane;
	private static String userName;
	public UserRegistration(  final ChatWindow chatWindow,  final ObjectOutputStream oos) {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.oos=oos;
		JLabel logIn = new JLabel("User Name");
		logIn.setToolTipText("");
		logIn.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		logIn.setHorizontalAlignment(SwingConstants.LEFT);
		logIn.setBounds(118, 61, 88, 30);
		contentPane.add(logIn);
		
		userNameTextField = new JTextField();
		userNameTextField.setToolTipText("Enter Your Online Name Here");
		userNameTextField.setBounds(118, 109, 141, 20);
		getContentPane().add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JButton btnLogin = new JButton("LOG IN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userName=userNameTextField.getText();
				try {
					oos.writeObject(userName);		
					chatWindow.setVisible(true);
					dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnLogin.setBounds(118, 158, 89, 23);
		contentPane.add(btnLogin);
		setSize(300, 150);
		setBounds(500, 300, 300, 150);
	}
	public static String getUserName() {
		return userName;
	}
}
