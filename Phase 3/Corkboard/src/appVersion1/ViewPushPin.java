package appVersion1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import information.DataProvider;
import information.PushPin;
import information.User;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

public class ViewPushPin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Image cat = new ImageIcon(this.getClass().getResource("/cat.jpg")).getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT);
					ViewPushPin frame = new ViewPushPin(null, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public ViewPushPin(PushPin p, int user_id) throws IOException {
		setTitle("View PushPin");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 673, 784);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCorkboardit = new JLabel("CorkBoard");
		lblCorkboardit.setForeground(Color.BLUE);
		lblCorkboardit.setFont(new Font("Verdana", Font.BOLD, 30));
		lblCorkboardit.setBounds(424, 34, 178, 37);
		contentPane.add(lblCorkboardit);
		
		JLabel lblIt = new JLabel("It");
		lblIt.setForeground(new Color(178, 34, 34));
		lblIt.setFont(new Font("Verdana", Font.BOLD, 30));
		lblIt.setBounds(601, 34, 30, 37);
		contentPane.add(lblIt);
		
		JLabel lblExploreShareInspire = new JLabel("Explore. Share. Inspire.");
		lblExploreShareInspire.setFont(new Font("Verdana", Font.BOLD, 13));
		lblExploreShareInspire.setForeground(new Color(244, 164, 96));
		lblExploreShareInspire.setBounds(434, 70, 178, 26);
		contentPane.add(lblExploreShareInspire);
		
		JLabel label = new JLabel("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label.setBounds(10, 96, 650, 14);
		contentPane.add(label);
		
		JLabel lblUserName = new JLabel();
		lblUserName.setFont(new Font("Verdana", Font.BOLD, 18));
		lblUserName.setBounds(31, 106, 146, 29);
		lblUserName.setText(p.getOwner_name());
		contentPane.add(lblUserName);
		
		JButton btnFollow = new JButton("Follow");
		btnFollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnFollow.setText("Followed");
				btnFollow.setEnabled(false);
			}
		});
		btnFollow.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnFollow.setBounds(204, 110, 86, 26);
		if(p.getUser_id() == user_id) {
			btnFollow.setEnabled(false);
		}
		if(DataProvider.Followed(user_id, p.getUser_id())) {
			btnFollow.setText("Followed");
			btnFollow.setEnabled(false);
		}
		contentPane.add(btnFollow);
		
		JLabel label_1 = new JLabel("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_1.setBounds(10, 596, 650, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_2.setBounds(10, 552, 650, 14);
		contentPane.add(label_2);
		
		JButton btnLike = new JButton("Like!");
		btnLike.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLike.setBounds(542, 574, 89, 23);
		if(p.getUser_id() == user_id) {
			btnLike.setEnabled(false);
		}
		if(DataProvider.Liked(user_id, p.getPushpin_id())) {
			btnLike.setText("Unlike!");
		}
		btnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(DataProvider.Liked(user_id, p.getPushpin_id())) {
					btnLike.setText("Like!");
					try {
						DataProvider.Unlike(user_id, p.getPushpin_id());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						ViewPushPin vpp = new ViewPushPin(p, user_id);
						vpp.setVisible(true);
						dispose();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					btnLike.setText("Unlike!");
					try {
						DataProvider.Like(user_id, p.getPushpin_id());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						ViewPushPin vpp = new ViewPushPin(p, user_id);
						vpp.setVisible(true);
						dispose();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		contentPane.add(btnLike);
		
		String commentLines = "Post\nComment";
		JButton btnPostComment = new JButton("<html>" + commentLines.replaceAll("\\n", "<br>") + "</html>");
		btnPostComment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPostComment.setBounds(542, 621, 89, 37);
		contentPane.add(btnPostComment);
		
		JLabel label_3 = new JLabel("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_3.setBounds(10, 158, 650, 14);
		contentPane.add(label_3);
		
		JLabel lblPinned = new JLabel("Pinned " + p.getPp_time() + " on " + p.getCorkboard_name());
		lblPinned.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPinned.setBounds(31, 140, 469, 20);
		lblPinned.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				ViewCorkBoard viewBoard = new ViewCorkBoard(null);
//				viewBoard.setVisible(true);
				dispose();
			}
		});
		lblPinned.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblPinned);
		
		int imageSizeLimit = 300;
		String path = p.getUrl();
		try {
			URL url = new URL(path);
			Image image = ImageIO.read(url.openStream());
			int height = image.getHeight(null);
			int width = image.getWidth(null);
			System.out.println(height + " " + width);
			if(height > imageSizeLimit) {
				width = width * imageSizeLimit / height;
				height = imageSizeLimit;
			}
			if(width > imageSizeLimit) {
				height = imageSizeLimit * height / width;
				width = imageSizeLimit;
			}
			System.out.println(height + " " + width);
			Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			JLabel lblD = new JLabel(new ImageIcon(scaledImage));
			lblD.setBounds(76, 183, 462, 315);
			lblD.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					URI uri = null;
					try {
						uri = new URI(path);
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Desktop desktop = java.awt.Desktop.getDesktop();
			        try {
						desktop.browse(uri);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			lblD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			contentPane.add(lblD);
		}
		catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JTextArea txtrWw = new JTextArea();
		txtrWw.setBackground(new Color(255, 255, 255));
		txtrWw.setLineWrap(true);
		txtrWw.setBounds(20, 621, 501, 37);
		contentPane.add(txtrWw);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBackground(new Color(255, 255, 255));
		textArea.setBounds(20, 669, 611, 65);
		textArea.setEditable(false);
		//JScrollPane scroll = new JScrollPane(textArea);
		//scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(textArea);
		
		String comments = DataProvider.GetComments(p.getPushpin_id());
		textArea.setText(comments);
		
		JLabel description = new JLabel(p.getDescription());
		description.setFont(new Font("Tahoma", Font.PLAIN, 14));
		description.setBounds(31, 499, 300, 26);
		contentPane.add(description);
		
		String tags = DataProvider.GetTags(p.getPushpin_id());
		JLabel Tags = new JLabel("Tags: " + tags);
		Tags.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Tags.setBounds(28, 527, 557, 26);
		contentPane.add(Tags);
		
		JLabel lblNewLabel = new JLabel("Pushpin liked by " + DataProvider.GetLikedUserName(p.getPushpin_id()));
		lblNewLabel.setBounds(31, 570, 440, 26);
		contentPane.add(lblNewLabel);
		
		User user = DataProvider.getUserNameFromID(user_id);
		
		btnPostComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DataProvider.AddComment(user_id, p.getPushpin_id(), txtrWw.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textArea.setText(textArea.getText() + "\n" + user.getFirst_name() + user.getLast_name() + ":   " + txtrWw.getText());
				txtrWw.setText("");
			}
		});
	}
}
