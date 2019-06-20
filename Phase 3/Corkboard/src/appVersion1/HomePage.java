package appVersion1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.Box;
import javax.swing.event.AncestorListener;

import information.CorkBoard;
import information.DataProvider;
import information.User;

import javax.swing.event.AncestorEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.border.LineBorder;

import information.DataProvider;
import information.User;
import information.CorkBoard;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class HomePage extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearchDescriptionTags;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage(0); //null try
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
	public HomePage(int user_id) {
		setTitle("CorkBoardIt Home Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 684);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHomePageFor = new JLabel("Home Page for");
		lblHomePageFor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHomePageFor.setBounds(53, 34, 108, 26);
		contentPane.add(lblHomePageFor);
		
		JLabel lblCorkboardit = new JLabel("CorkBoard");
		lblCorkboardit.setForeground(Color.BLUE);
		lblCorkboardit.setFont(new Font("Verdana", Font.BOLD, 30));
		lblCorkboardit.setBounds(514, 34, 178, 37);
		contentPane.add(lblCorkboardit);
		
		JLabel lblIt = new JLabel("It");
		lblIt.setForeground(new Color(178, 34, 34));
		lblIt.setFont(new Font("Verdana", Font.BOLD, 30));
		lblIt.setBounds(693, 34, 30, 37);
		contentPane.add(lblIt);
		
		JLabel lblExploreShareInspire = new JLabel("Explore. Share. Inspire.");
		lblExploreShareInspire.setFont(new Font("Verdana", Font.BOLD, 13));
		lblExploreShareInspire.setForeground(new Color(244, 164, 96));
		lblExploreShareInspire.setBounds(524, 71, 178, 26);
		contentPane.add(lblExploreShareInspire);
		
		JLabel lblRecentCorkboardUpdates = new JLabel("Recent CorkBoard Updates");
		lblRecentCorkboardUpdates.setFont(new Font("Verdana", Font.BOLD, 18));
		lblRecentCorkboardUpdates.setBounds(53, 121, 287, 28);
		contentPane.add(lblRecentCorkboardUpdates);
		
		JLabel lblMyCorkboards = new JLabel("My CorkBoards");
		lblMyCorkboards.setFont(new Font("Verdana", Font.BOLD, 18));
		lblMyCorkboards.setBounds(54, 342, 178, 26);
		contentPane.add(lblMyCorkboards);
		
		JButton btnPopularTags = new JButton("Popular Tags");
		btnPopularTags.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPopularTags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopularTags popularTags = new PopularTags(user_id);
				popularTags.setVisible(true);
			}
		});
		btnPopularTags.setBounds(548, 123, 130, 26);
		contentPane.add(btnPopularTags);
		
		JButton btnAddCorkboard = new JButton("Add CorkBoard");
		btnAddCorkboard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddCorkboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddCorkBoard addCorkBoard = new AddCorkBoard(user_id);
				addCorkBoard.setVisible(true);
			}
		});
		btnAddCorkboard.setBounds(233, 346, 130, 26);
		contentPane.add(btnAddCorkboard);
		
		JLabel lblName = new JLabel();
		lblName.setFont(new Font("Verdana", Font.PLAIN, 18));
		String firstName = DataProvider.GetString("first_name", "user_id", user_id, "regularuser");
		String lastName = DataProvider.GetString("last_name", "user_id", user_id, "regularuser");
		String userName = firstName + " " + lastName;
		lblName.setText(userName);
		lblName.setBounds(54, 56, 342, 26);
		contentPane.add(lblName);
		
		txtSearchDescriptionTags = new JTextField();
	
		txtSearchDescriptionTags.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtSearchDescriptionTags.getText().trim().equals("Search description, tags and CorkBoard category")) {
				txtSearchDescriptionTags.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtSearchDescriptionTags.getText().trim().equals("") || txtSearchDescriptionTags.getText().trim().toLowerCase().equals("Search description, tags and CorkBoard category")) {
					txtSearchDescriptionTags.setText("Search description, tags and CorkBoard category");
				}
			}
		});
		txtSearchDescriptionTags.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSearchDescriptionTags.setForeground(new Color(119, 136, 153));
		txtSearchDescriptionTags.setText("Search description, tags and CorkBoard category");
		txtSearchDescriptionTags.setBounds(53, 553, 480, 26);
		contentPane.add(txtSearchDescriptionTags);
		txtSearchDescriptionTags.setColumns(10);
		
		JButton btnPushpinSearch = new JButton("PushPin Search");
		btnPushpinSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String PPsearch = txtSearchDescriptionTags.getText();
				PushPinSearchResults PPresult = new PushPinSearchResults(PPsearch, user_id);
				txtSearchDescriptionTags.setText("Search description, tags and CorkBoard category");
				PPresult.setVisible(true);
			}
		});
		btnPushpinSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnPushpinSearch.setBounds(570, 553, 132, 26);
		contentPane.add(btnPushpinSearch);
		
		JLabel label = new JLabel("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label.setBounds(20, 98, 760, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_1.setBounds(20, 528, 760, 14);
		contentPane.add(label_1);
		
		JButton btnPopularSites = new JButton("Popular Sites");
		btnPopularSites.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPopularSites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopularSites popularSite = new PopularSites();
				popularSite.getObj().setVisible(true);
			}
		});
		btnPopularSites.setBounds(396, 125, 130, 26);
		contentPane.add(btnPopularSites);
		
		JButton btnBoardStats = new JButton("CorkBoard Statistics");
		btnBoardStats.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBoardStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardStats ckStat = new BoardStats(user_id);
				ckStat.setVisible(true);
			}
		});
		btnBoardStats.setBounds(514, 346, 169, 26);
		contentPane.add(btnBoardStats);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomePage homePage = new HomePage(user_id);
				homePage.setVisible(true);
				dispose();
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRefresh.setBounds(406, 71, 89, 23);
		contentPane.add(btnRefresh);
		
		// TODO: communicate with database, get recent updated corkboards that user cares.
		List<CorkBoard> recentBoards = DataProvider.getRecentUpdatedBoards(user_id);
		int i = 0;
		
		for(CorkBoard c : recentBoards) {
			String corkBoardName1 = c.getTitle();
			String template = "<HTML><U>%s</U></HTML>";
			JLabel corkBoard1 = new JLabel(String.format(template, corkBoardName1));
			corkBoard1.setForeground(new Color(0, 0, 0));
			corkBoard1.setBackground(new Color(240, 240, 240));
			corkBoard1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (c.getVisibility().equals("private")) {
						PasswordProtected passwordProtected = new PasswordProtected(c, user_id);
						passwordProtected.setVisible(true);
					}
					else {
						ViewCorkBoard viewBoard;
						try {
							viewBoard = new ViewCorkBoard(c, user_id);
							viewBoard.setVisible(true);
							//dispose();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			corkBoard1.setFont(new Font("Verdana", Font.BOLD, 16));
			corkBoard1.setBounds(63, 160+i, 192, 26);
			corkBoard1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			contentPane.add(corkBoard1);
			
			if(c.getVisibility().equals("private")) {
				JLabel lblPrivate = new JLabel("(private)");
				lblPrivate.setForeground(Color.RED);
				lblPrivate.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblPrivate.setBounds(265, 167+i, 58, 14);
				contentPane.add(lblPrivate);
			}
			
			// TODO: get corkboard update information
			// String owner1 = getData("User", "FirstName", recentBoards[0].getEmail()) + getData("User", "LastName", recentBoards[0].getEmail())
			//String owner1 = "Owner1";
			String owner1 = c.getOwner();
			// TODO: get updated time.
			//String updateTime1 = "January 1, 2019 at 1:00 pm";
			String updateTime1 = c.getLast_datetime();
			JLabel lblUpdatedBySomeone = new JLabel("Updated by " + owner1 + " on " + updateTime1);
			lblUpdatedBySomeone.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblUpdatedBySomeone.setBounds(73, 186+i, 541, 14);
			contentPane.add(lblUpdatedBySomeone);
			
			i += 45;
		}
		
		
		List<CorkBoard> myCorkboards = DataProvider.getmyCorkboards(user_id);
		int j = 0;
		
		for(CorkBoard c : myCorkboards) {
			/***** my corkboard name ******/
			//String myCorkBoardName = "Cats and their Antics";
			String myCorkBoardName = c.getTitle();
			String template = "<HTML><U>%s</U></HTML>";
			JLabel myCorkBoard = new JLabel(String.format(template, myCorkBoardName));
			myCorkBoard.setForeground(new Color(0, 0, 0));
			myCorkBoard.setBackground(new Color(240, 240, 240));
			myCorkBoard.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//CorkBoard corkBoard = new CorkBoard("corkboard email", new Timestamp(System.currentTimeMillis()));
					if (c.getVisibility().equals("private")) {
						//TODO: password protected corkboard page
						PasswordProtected passwordProtected = new PasswordProtected(c, user_id);
						passwordProtected.setVisible(true);
					}
					else {
						ViewCorkBoard viewBoard;
						try {
							viewBoard = new ViewCorkBoard(c, user_id);
							viewBoard.setVisible(true);
							//dispose();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
					}
				}
			});
			myCorkBoard.setFont(new Font("Verdana", Font.BOLD, 16));
			myCorkBoard.setBounds(63, 381+j, 192, 26);
			myCorkBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			contentPane.add(myCorkBoard);
			
			if(c.getVisibility().equals("private")) {
				JLabel lblPrivate = new JLabel("(private)");
				lblPrivate.setForeground(Color.RED);
				lblPrivate.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblPrivate.setBounds(253, 388+j, 58, 14);
				contentPane.add(lblPrivate);
			}
			
			/***** count pushpin number ******/
			//String pushpin_num = "22";
			int board_id = c.getBoard_id();
			int pushpin_num = DataProvider.getPushpinNumberOnCorkboard(board_id);
			JLabel lblPushpinNum = new JLabel("with " + pushpin_num + " PushPins");
			lblPushpinNum.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPushpinNum.setBounds(312, 388+j, 541, 14);
			contentPane.add(lblPushpinNum);
			
			j += 40;
		}		
	}
}
