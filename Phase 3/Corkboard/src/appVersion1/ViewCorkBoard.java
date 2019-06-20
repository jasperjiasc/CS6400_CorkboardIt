package appVersion1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import information.CorkBoard;
import information.DataProvider;
import information.PushPin;
import information.User;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ViewCorkBoard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCorkBoard frame = new ViewCorkBoard(null, 0);
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
	public ViewCorkBoard(CorkBoard corkBoard, int user_id) throws IOException {
		setTitle("View CorkBoard");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 740);
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
		label.setBounds(10, 98, 650, 14);
		contentPane.add(label);
		
		// Done: get user name from User table using corkBoard.getEmail();
		User user = DataProvider.getUserNameFromID(corkBoard.getUser_id());
		//String userName = "Jon Doe";
		String userName = user.getFirst_name() + " " + user.getLast_name(); 
		JLabel lblUserName = new JLabel();
		lblUserName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				HomePage homePage = new HomePage(1);
				homePage.setVisible(true);
				dispose();
			}
		});
		lblUserName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblUserName.setFont(new Font("Verdana", Font.BOLD, 18));
		lblUserName.setBounds(30, 110, 146, 29);
		lblUserName.setText(userName);
		contentPane.add(lblUserName);
		
		JButton btnFollow = new JButton("Follow");
		btnFollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DataProvider.AddFollow(user_id, corkBoard.getUser_id());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				btnFollow.setText("Followed");
				btnFollow.setEnabled(false);
			}
		});
		btnFollow.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnFollow.setBounds(204, 110, 86, 26);
		if(corkBoard.getUser_id() == user_id) {
			btnFollow.setEnabled(false);
		}
		if(DataProvider.Followed(user_id, corkBoard.getUser_id())) {
			btnFollow.setText("Followed");
			btnFollow.setEnabled(false);
		}
		contentPane.add(btnFollow);
		
		// Done: get corkboard name from CorkBoard table using corkBoard.getEmail() and corkBoard.getCorkBoardUpdatedTime()
		//String cbame = "CorkBoard Name";
		String cbame = corkBoard.getTitle();
		JLabel corkBoardName = new JLabel();
		corkBoardName.setText(cbame);
		corkBoardName.setFont(new Font("Verdana", Font.PLAIN, 18));
		corkBoardName.setBounds(30, 140, 219, 26);
		contentPane.add(corkBoardName);
		
		JButton btnAddPushpin = new JButton("Add PushPin");
		btnAddPushpin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddPushPin addPP = new AddPushPin(corkBoard);
				addPP.setVisible(true);
			}
		});
		btnAddPushpin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddPushpin.setBounds(511, 160, 120, 30);
		if(corkBoard.getUser_id() != user_id) {
			btnAddPushpin.setEnabled(false);
		}
		contentPane.add(btnAddPushpin);
		
		String category = DataProvider.GetCategoryFromCorkBoardID(corkBoard.getBoard_id());
		JLabel lblCategory = new JLabel(category);
		lblCategory.setForeground(new Color(0, 0, 139));
		lblCategory.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategory.setBounds(460, 107, 161, 37);
		contentPane.add(lblCategory);
		
		// Done: get updated time from CorkBoard table using corkBoard.getEmail() and corkBoard.getCorkBoardUpdatedTime()
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//Calendar cal = Calendar.getInstance();
		JLabel lblLastUpdated = new JLabel("Last Updated " + corkBoard.getLast_datetime());
		lblLastUpdated.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblLastUpdated.setBounds(30, 177, 419, 26);
		contentPane.add(lblLastUpdated);
		
		JLabel label_1 = new JLabel("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_1.setBounds(10, 201, 650, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_2.setBounds(10, 638, 650, 14);
		contentPane.add(label_2);
		
		JButton btnWatch = new JButton("Watch");
		btnWatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DataProvider.AddWatch(user_id, corkBoard.getBoard_id());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				btnWatch.setText("Watched");
				btnWatch.setEnabled(false);
			}
		});
		if(corkBoard.getUser_id() == user_id) {
			btnWatch.setEnabled(false);
		}
		if(DataProvider.Watched(user_id, corkBoard.getBoard_id())) {
			btnWatch.setText("Watched");
			btnWatch.setEnabled(false);
		}
		btnWatch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnWatch.setBounds(545, 663, 86, 26);
		contentPane.add(btnWatch);
		
		List<PushPin> pushPins = DataProvider.getPushPins(corkBoard.getBoard_id(), userName, cbame, corkBoard.getUser_id());
		int i = 0;
		int imageHeightLimit = 200;
		int imageWidthLimit = 150;
		int index = 0;
		int j = 0;
		for(PushPin p : pushPins) {
			String path = p.getUrl();
			if(index == 3) {
				j = 210;
				i = 0;
			}
			try {
				URL url = new URL(path);
				Image image = ImageIO.read(url.openStream());
				int height = image.getHeight(null);
				int width = image.getWidth(null);
				if(height > imageHeightLimit) {
					width = width * imageHeightLimit / height;
					height = imageHeightLimit;
				}
				if(width > imageWidthLimit) {
					height = imageWidthLimit * height / width;
					width = imageWidthLimit;
				}
				Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
				JLabel lblDd = new JLabel(new ImageIcon(scaledImage));
				lblDd.setBounds(60 + i, 252 + j, 178, 180);
				lblDd.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
					ViewPushPin viewPP;
					try {
						viewPP = new ViewPushPin(p, user_id);
						System.out.println("Register pushpin :" + p.getDescription());
						viewPP.setVisible(true);
						//dispose();
						} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
					}
				});
				lblDd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				contentPane.add(lblDd);
				i += 160;
				index ++;
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
