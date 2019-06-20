package appVersion1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import information.CorkBoard;
import information.DataProvider;
import information.PushPin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class AddPushPin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPushPin frame = new AddPushPin(null);
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
	public AddPushPin(CorkBoard c) {
		setTitle("Add PushPin to CorkBoard");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 551, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddPushpin = new JLabel("Add PushPin");
		lblAddPushpin.setFont(new Font("Verdana", Font.BOLD, 22));
		lblAddPushpin.setBounds(36, 28, 192, 39);
		contentPane.add(lblAddPushpin);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTo.setBounds(36, 70, 25, 19);
		contentPane.add(lblTo);
		
		JLabel lblCorkboardName = new JLabel(c.getTitle());
		lblCorkboardName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				ViewCorkBoard viewBoard = new ViewCorkBoard(null);
//				viewBoard.setVisible(true);
//				dispose();
			}
		});
		lblCorkboardName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCorkboardName.setBounds(65, 67, 235, 25);
		contentPane.add(lblCorkboardName);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUrl.setBounds(36, 151, 46, 14);
		contentPane.add(lblUrl);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescription.setBounds(36, 209, 104, 25);
		contentPane.add(lblDescription);
		
		JLabel lblTag = new JLabel("Tag");
		lblTag.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTag.setBounds(36, 302, 104, 19);
		contentPane.add(lblTag);
		
		textField = new JTextField();
		textField.setBounds(140, 150, 352, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(140, 303, 352, 25);
		contentPane.add(textField_2);
		
		JTextArea txtrA = new JTextArea();
		txtrA.setLineWrap(true);
		txtrA.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtrA.setTabSize(14);
		txtrA.setBounds(140, 198, 352, 84);
		contentPane.add(txtrA);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(79, 362, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				URL url = null;
				Image image = null;
				try {
					url = new URL(textField.getText());
				} catch (MalformedURLException e) {
					JOptionPane.showMessageDialog(contentPane, "Url is not valid.");
				}
				try {
					image = ImageIO.read(url.openStream()).getScaledInstance(150, 150, Image.SCALE_DEFAULT);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(contentPane, "Url is not valid.");
				}
				if(txtrA.getText().length() == 0) {
					JOptionPane.showMessageDialog(contentPane, "Description is required.");
				}
				else if(txtrA.getText().length() > 200) {
					JOptionPane.showMessageDialog(contentPane, "Description should not exceed 200 characters.");
				}
				else if(image != null) {
					PushPin pushPin = new PushPin(0, c.getBoard_id(), null, txtrA.getText(), textField.getText(), c.getOwner(), c.getTitle(), c.getUser_id());
					String corkboard_last_update = DataProvider.addPushpin(pushPin, textField_2.getText());
					c.setLast_datetime(corkboard_last_update);
					dispose();
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(375, 364, 89, 23);
		contentPane.add(btnAdd);
	}

}
