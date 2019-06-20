package appVersion1;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.*;
import java.awt.font.*;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import information.DataProvider;
import information.PopularSite;
import net.proteanit.sql.DbUtils;
import javax.swing.table.DefaultTableModel;

public class PopularSites extends JFrame {
	
	private static PopularSites obj = null;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopularSites frame = new PopularSites();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static PopularSites getObj() {
		if(obj == null) {
			obj = new PopularSites();
		}
		return obj;
	}
	
	/**
	 * Create the frame.
	 */
	public PopularSites() {
		setAlwaysOnTop(true);
		setResizable(false);
		
		setTitle("Popular Sites");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 805, 729);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/image/Corkboard2.png"));
		label.setIcon(img);
		label.setBounds(412, 13, 339, 91);
		contentPane.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setSize(new Dimension(2, 0));
		separator.setForeground(Color.BLACK);
		separator.setBounds(12, 117, 755, 2);
		contentPane.add(separator);
		
		JLabel lblPopularSites = new JLabel("Popular Sites");
		lblPopularSites.setFont(new Font("Arial", Font.BOLD, 30));
		lblPopularSites.setBounds(33, 160, 390, 33);
		contentPane.add(lblPopularSites);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(SystemColor.controlShadow));
		panel.setBounds(34, 206, 726, 394);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(38, 91, 643, 256);
		panel.add(scrollPane);
		
		PopularSite rsSiteStat = DataProvider.getPopularSite();
		
		table = new JTable();
		table.setBorder(null);
		table.setBackground(SystemColor.control);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setRequestFocusEnabled(false);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setShowGrid(false);
		table.setSelectionForeground(SystemColor.control);
		table.setSelectionBackground(SystemColor.control);
		table.setRowHeight(50);
		table.setFont(new Font("Arial", Font.PLAIN, 30));
		
		table.setModel(DbUtils.resultSetToTableModel(rsSiteStat.getSiteStat()));
		
		table.setTableHeader(null);
		table.getColumnModel().getColumn(0).setPreferredWidth(355);
		table.getColumnModel().getColumn(1).setPreferredWidth(53);
		scrollPane.setViewportView(table);
		
		JLabel lblSite = new JLabel("Site");
		Font font = new Font ("Arial", Font.BOLD, 27);
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblSite.setFont(font.deriveFont(attributes));
		lblSite.setBounds(45, 35, 185, 29);
		panel.add(lblSite);
		
		JLabel label_4 = new JLabel("PushPins");
		label_4.setFont(new Font("Arial", Font.BOLD, 27));
		label_4.setFont(font.deriveFont(attributes));
		label_4.setBounds(506, 35, 164, 29);
		panel.add(label_4);
	}
}
