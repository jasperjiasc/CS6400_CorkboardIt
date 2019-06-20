package appVersion1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import information.DataProvider;
import information.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class LogIn extends JFrame{

	private JFrame frmLogin;
	private JTextField email;
	private JTextField pin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setResizable(false);
		frmLogin.setBounds(100, 100, 685, 524);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblCorkboardit = new JLabel("CorkBoard");
		lblCorkboardit.setForeground(Color.BLUE);
		lblCorkboardit.setFont(new Font("Verdana", Font.BOLD, 30));
		lblCorkboardit.setBounds(214, 49, 178, 37);
		frmLogin.getContentPane().add(lblCorkboardit);
		
		JLabel lblIt = new JLabel("It");
		lblIt.setForeground(new Color(178, 34, 34));
		lblIt.setFont(new Font("Verdana", Font.BOLD, 30));
		lblIt.setBounds(393, 49, 30, 37);
		frmLogin.getContentPane().add(lblIt);
		
		JLabel lblUserName = new JLabel("Email:");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUserName.setBounds(167, 267, 68, 27);
		frmLogin.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("PIN:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(167, 305, 60, 27);
		frmLogin.getContentPane().add(lblPassword);
		
		email = new JTextField();
		email.setBounds(240, 271, 205, 27);
		frmLogin.getContentPane().add(email);
		email.setColumns(10);
		
		pin = new JTextField();
		pin.setColumns(10);
		pin.setBounds(240, 309, 205, 27);
		frmLogin.getContentPane().add(pin);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(DataProvider.LogInValid(email.getText(), pin.getText())) {
					int userID = DataProvider.GetInteger("user_id", "email", email.getText(), "regularuser");
					HomePage homePage = new HomePage(userID);
					homePage.setVisible(true);
					frmLogin.dispose();
				}
				else {
					JOptionPane.showMessageDialog(frmLogin, "Email or pin is not valid.");
				};
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(280, 373, 91, 27);
		frmLogin.getContentPane().add(btnLogin);
	}
}
