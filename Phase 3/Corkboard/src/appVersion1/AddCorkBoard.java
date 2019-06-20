package appVersion1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import information.CorkBoard;
import information.DataProvider;
import information.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class AddCorkBoard extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterPassword;
	private JTextField textField;
	private ButtonGroup group;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCorkBoard frame = new AddCorkBoard(0);
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
	public AddCorkBoard(int user_id) {
		setTitle("Add CorkBoard");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 545, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddCorkboard = new JLabel("Add CorkBoard");
		lblAddCorkboard.setFont(new Font("Verdana", Font.BOLD, 20));
		lblAddCorkboard.setBounds(44, 34, 234, 42);
		contentPane.add(lblAddCorkboard);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 15));
		lblTitle.setBounds(44, 101, 57, 20);
		contentPane.add(lblTitle);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Verdana", Font.BOLD, 15));
		lblCategory.setBounds(44, 144, 90, 20);
		contentPane.add(lblCategory);
		
		JLabel lblVisibility = new JLabel("Visibility");
		lblVisibility.setFont(new Font("Verdana", Font.BOLD, 15));
		lblVisibility.setBounds(44, 195, 90, 20);
		contentPane.add(lblVisibility);
		
		txtEnterPassword = new JTextField();
		txtEnterPassword.setForeground(new Color(119, 136, 153));
		txtEnterPassword.setText("Enter Password");
		txtEnterPassword.setBounds(290, 237, 140, 23);
		txtEnterPassword.setVisible(false);
		contentPane.add(txtEnterPassword);
		txtEnterPassword.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(184, 103, 246, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//String[] categories = {"Animal", "People", "Garden"};
		//JComboBox comboBox = new JComboBox(categories); //for UI test
		
		String[] categories = new String[DataProvider.getCategory().size()];
		DataProvider.getCategory().toArray(categories);		
		JComboBox<String> comboBox = new JComboBox<String>(categories);
		
		comboBox.setBounds(184, 146, 245, 20);
		contentPane.add(comboBox);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Verdana", Font.BOLD, 13));
		btnCancel.setBounds(92, 296, 89, 23);
		contentPane.add(btnCancel);
		
		JRadioButton rdbtnPublic = new JRadioButton("Public", true);
		rdbtnPublic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtEnterPassword.setVisible(false);
			}
		});
		rdbtnPublic.setFont(new Font("Verdana", Font.PLAIN, 15));
		rdbtnPublic.setBounds(184, 194, 90, 23);
		contentPane.add(rdbtnPublic);
		
		JRadioButton rdbtnPrivate = new JRadioButton("Private", false);
		rdbtnPrivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtEnterPassword.setVisible(true);
			}
		});
		rdbtnPrivate.setFont(new Font("Verdana", Font.PLAIN, 15));
		rdbtnPrivate.setBounds(184, 237, 90, 23);
		contentPane.add(rdbtnPrivate);
		
		group = new ButtonGroup();
		group.add(rdbtnPublic);
		group.add(rdbtnPrivate);
		
		User user = DataProvider.getUserNameFromID(user_id);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = textField.getText(); 
				String category = comboBox.getSelectedItem().toString();
				
				CorkBoard ret_c = new CorkBoard();
				String cat_name = String.valueOf(comboBox.getSelectedItem());
				
				String visibility = "private";
				if (rdbtnPublic.isSelected()) {
					visibility = "public";
					CorkBoard corkBoard = new CorkBoard(0, user_id, title, null, user.getFirst_name() + " " + user.getLast_name(), visibility, null);
					ret_c = DataProvider.addCorkboard(corkBoard, cat_name);
				}
				else if (rdbtnPrivate.isSelected()) {
					visibility = "private";
					CorkBoard corkBoard = new CorkBoard(0, user_id, title, null, user.getFirst_name() + " " + user.getLast_name(), visibility, txtEnterPassword.getText());
					ret_c = DataProvider.addCorkboard(corkBoard, cat_name);
				}							
				
				//CorkBoard corkBoard = new CorkBoard(1, user_id, "Pools", null, "shenry@gatech.edu", "public", null);
				
				ViewCorkBoard viewCorkBoard;
				try {
					viewCorkBoard = new ViewCorkBoard(ret_c, user_id);
					viewCorkBoard.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
				//TODO: need to validate parameter, if success dispose this frame.
				if (true) {
					dispose();
				}
			}
		});
		btnAdd.setFont(new Font("Verdana", Font.BOLD, 13));
		btnAdd.setBounds(341, 296, 89, 23);
		contentPane.add(btnAdd);		
	}
}
