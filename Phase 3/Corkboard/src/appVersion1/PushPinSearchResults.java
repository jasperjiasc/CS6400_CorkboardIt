package appVersion1;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import information.DataProvider;
import information.PushPin;
import information.PushPinSearchResult;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PushPinSearchResults extends JFrame {

	private JPanel contentPane;
	public JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PushPinSearchResults frame = new PushPinSearchResults(null, 0); ///
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
	public PushPinSearchResults(String PPsearch, int user_id) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 878, 532);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 113, 836, 2);
		contentPane.add(separator);
		
		JLabel lblIcon = new JLabel("icon");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/image/Corkboard2.png"));
		lblIcon.setIcon(img);
		lblIcon.setBounds(509, 13, 339, 91);
		contentPane.add(lblIcon);
		
		JLabel lblNewLabel = new JLabel("PushPin Search Results");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(22, 128, 293, 33);
		contentPane.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 174, 836, 274);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("PushPin Description");
		Font font1 = new Font("Arial", Font.BOLD, 25);
		Map<TextAttribute, Object> attributes1 = new HashMap<>(font1.getAttributes());
		attributes1.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblNewLabel_1.setFont(font1.deriveFont(attributes1));
		lblNewLabel_1.setBounds(12, 26, 310, 48);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblCorkboard = new JLabel("CorkBoard");
		lblCorkboard.setFont(new Font("Arial", Font.BOLD, 25));
		lblCorkboard.setFont(font1.deriveFont(attributes1));
		lblCorkboard.setBounds(348, 26, 246, 48);
		panel_1.add(lblCorkboard);
		
		JLabel lblOwner = new JLabel("Owner");
		lblOwner.setFont(new Font("Arial", Font.BOLD, 25));
		lblOwner.setFont(font1.deriveFont(attributes1));
		lblOwner.setBounds(617, 26, 207, 48);
		panel_1.add(lblOwner);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 87, 812, 174);
		panel_1.add(scrollPane);
		
		
		PushPinSearchResult PPrs = DataProvider.getSearchRt(PPsearch);
		
		
		table = new JTable();
		
		table.setRowHeight(44);
		table.setRowSelectionAllowed(false);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setSelectionBackground(SystemColor.control);
		table.setEnabled(false);
		table.setBorder(null);
		table.setBackground(SystemColor.control);
		table.setFont(new Font("Arial", Font.PLAIN, 20));
		
		ResultSet rs = PPrs.getPPresult();
		
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
		
		
		//cellrenderer
		DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
		    Font font = new Font("Arial", Font.BOLD, 20);
		    
		    @Override
		    public Component getTableCellRendererComponent(JTable table,
		            Object value, boolean isSelected, boolean hasFocus,
		            int row, int column) {
		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
		                row, column);
		        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				
				font = font.deriveFont(attributes);
				//setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        setFont(font);
		        return this;
		    }

		};
		
		table.getColumnModel().getColumn(1).setCellRenderer(r);
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				if(col == 0 ) {
					
					 int rowNm = 0;
						
						try {
							rs.beforeFirst();
							while(rs.next()) {
								
								if(row == rowNm) {
									int pp_id = Integer.parseInt(rs.getString("pushpin_id")); //
									PushPin pp = DataProvider.getPushPin(pp_id, DataProvider.GetUserNameFromPushpinID(pp_id), DataProvider.GetCorkboardNameFromCorkboardID(pp_id), 0);
									//JOptionPane.showMessageDialog(null, pp);
									ViewPushPin viewPP = new ViewPushPin(pp, user_id); 
									//JOptionPane.showMessageDialog(null, pp);
									viewPP.setVisible(true);
									//JOptionPane.showMessageDialog(null, "corkID"+ pp.getCorkboard_id());
									break;
								}
								rowNm +=1;
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
				}catch(Exception e1) {};
			}						
		});
		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(212);
		table.getColumnModel().getColumn(1).setPreferredWidth(161);
		table.getColumnModel().getColumn(2).setPreferredWidth(73);
		table.setTableHeader(null);
		
		scrollPane.setViewportView(table);
	}

}
