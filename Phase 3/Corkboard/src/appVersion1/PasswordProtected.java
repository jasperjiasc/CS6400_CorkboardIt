package appVersion1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import information.CorkBoard;
import information.DataProvider;
import information.PushPin;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;

public class PasswordProtected extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordProtected frame = new PasswordProtected(null, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PasswordProtected(CorkBoard corkBoard, int user_id) {
		setTitle("Password Protected");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 551, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIcon = new JLabel("icon");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/image/password1.png"));
		lblIcon.setIcon(img);
		lblIcon.setBounds(22, 65, 106, 91);
		contentPane.add(lblIcon);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(49, 224, 89, 34);
		contentPane.add(lblPassword);
		
		
//		passwordField = new JPasswordField();
//		passwordField.setBounds(238, 233, 200, 20);
//		contentPane.add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(148, 229, 307, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(248, 315, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnAdd = new JButton("OK");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String password = passwordField.getPassword().toString();
				String password = textField.getText();
				System.out.println("password entered:" +  password);
				System.out.println("Corkboard id:" +  corkBoard.getBoard_id());
				System.out.println("password in database:" +  corkBoard.getPassword());
				
				if (password.equals(corkBoard.getPassword())) {					
					ViewCorkBoard viewBoard;
					try {
						viewBoard = new ViewCorkBoard(corkBoard, user_id);
						viewBoard.setVisible(true);
						//dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Password is not correct.");
					//dispose();
				}
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(347, 315, 89, 23);
		contentPane.add(btnAdd);
		
		JTextArea txtrTheCorkboardYou = new JTextArea();
		txtrTheCorkboardYou.setFont(new Font("Arial", Font.PLAIN, 25));
		txtrTheCorkboardYou.setForeground(Color.RED);
		txtrTheCorkboardYou.setText("The corkBoard you are \r\ntrying to view is private. \r\nPlease enter the CorkBoard's \r\npassword to continue.");
		txtrTheCorkboardYou.setBounds(152, 52, 352, 137);
		txtrTheCorkboardYou.setLineWrap(true);
		txtrTheCorkboardYou.setBackground(SystemColor.control);
		contentPane.add(txtrTheCorkboardYou);

	}
}
