package appVersion1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import information.DataProvider;
import information.PopularTag;

public class PopularTags extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopularTags frame = new PopularTags(0);
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
	public PopularTags(int user_id) {
		setTitle("Popular Tags");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 796, 684);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JLabel lblRecentCorkboardUpdates = new JLabel("Popular Tags");
		lblRecentCorkboardUpdates.setFont(new Font("Verdana", Font.BOLD, 18));
		lblRecentCorkboardUpdates.setBounds(53, 121, 287, 28);
		contentPane.add(lblRecentCorkboardUpdates);
		
		JLabel label = new JLabel("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label.setBounds(20, 98, 760, 14);
		contentPane.add(label);
		
		String template = "<HTML><U>%s</U></HTML>";
		JLabel lblTag = new JLabel(String.format(template, "Tag"));
		lblTag.setFont(new Font("Verdana", Font.BOLD, 18));
		lblTag.setBounds(53, 171, 144, 28);
		contentPane.add(lblTag);
		
		JLabel lblPushpins = new JLabel(String.format(template, "PushPins"));
		lblPushpins.setFont(new Font("Verdana", Font.BOLD, 18));
		lblPushpins.setBounds(255, 171, 144, 28);
		contentPane.add(lblPushpins);
		
		JLabel lblUniqueCorkboards = new JLabel(String.format(template, "Unique CorkBoards"));
		lblUniqueCorkboards.setFont(new Font("Verdana", Font.BOLD, 18));
		lblUniqueCorkboards.setBounds(487, 171, 205, 28);
		contentPane.add(lblUniqueCorkboards);
		
		int i = 0;
		List<PopularTag> popularTags = DataProvider.getPopularTag();
		
		for(PopularTag pTag : popularTags) {
			/* populate data */
			//JLabel lblTagName = new JLabel(String.format(template, "face"));
			JLabel lblTagName = new JLabel(String.format(template, pTag.getTag()));
			lblTagName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			lblTagName.setBounds(53, 211+i, 144, 28);			
			lblTagName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					PushPinSearchResults res = new PushPinSearchResults(pTag.getTag(), user_id);
					res.setVisible(true);
				}
			});			
			contentPane.add(lblTagName);
			
			//JLabel lblPushpinNum = new JLabel("55");
			JLabel lblPushpinNum = new JLabel(Integer.toString(pTag.getPushpin_num()));
			lblPushpinNum.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			lblPushpinNum.setBounds(255, 211+i, 144, 28);
			contentPane.add(lblPushpinNum);
			
			//JLabel lblUniqueCorkboardsNum = new JLabel("22");
			JLabel lblUniqueCorkboardsNum = new JLabel(Integer.toString(pTag.getUnique_board_num()));
			lblUniqueCorkboardsNum.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			lblUniqueCorkboardsNum.setBounds(487, 211+i, 205, 28);
			contentPane.add(lblUniqueCorkboardsNum);
			
			i += 40;
		}
	}
}
