package appVersion1;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.font.TextAttribute;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import information.BoardStat;
import information.DataProvider;
import net.proteanit.sql.DbUtils;

public class BoardStats extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardStats frame = new BoardStats(0);
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
	public BoardStats(int user_id) {
		setResizable(false);
		setTitle("CorkBoard Statistics");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1062, 517);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(SystemColor.controlShadow, 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 113, 1020, 2);
		contentPane.add(separator);
		
		JLabel label = new JLabel("New label");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/image/Corkboard2.png"));
		label.setIcon(img);
		label.setBounds(693, 13, 339, 91);
		contentPane.add(label);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.controlShadow));
		panel.setBounds(12, 189, 1020, 254);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//BoardStat rsBoardStat = DataProvider.getBoardStat();
		/*table.setBorder(null);
		table.setSelectionBackground(SystemColor.control);
		table.setEnabled(false);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Arial", Font.PLAIN, 25));
		table.setRowHeight(30);*/
		//ResultSet rs = rsBoardStat.getBoardStat();
		
		JLabel lblNewLabel = new JLabel("User");
		Font font = new Font("Arial", Font.BOLD, 25);
		//Font font = lblPushpinName1.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblNewLabel.setFont(font.deriveFont(attributes));
		lblNewLabel.setBounds(22, 44, 304, 29);
		panel.add(lblNewLabel);
		
		JLabel lblCorkboards = new JLabel("CorkBoards");
		lblCorkboards.setFont(new Font("Arial", Font.BOLD, 25));
		lblCorkboards.setFont(font.deriveFont(attributes));
		lblCorkboards.setBounds(327, 44, 161, 29);
		panel.add(lblCorkboards);
		
		JLabel lblPushpins = new JLabel("PushPins");
		lblPushpins.setFont(new Font("Arial", Font.BOLD, 25));
		lblPushpins.setFont(font.deriveFont(attributes));
		lblPushpins.setBounds(489, 44, 161, 29);
		panel.add(lblPushpins);
		
		JLabel label_2 = new JLabel("CorkBoards");
		label_2.setFont(new Font("Arial", Font.BOLD, 25));
		label_2.setFont(font.deriveFont(attributes));
		label_2.setBounds(653, 44, 161, 29);
		panel.add(label_2);
		
		JLabel lblPushpins_1 = new JLabel("PushPins");
		lblPushpins_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblPushpins_1.setFont(font.deriveFont(attributes));
		lblPushpins_1.setBounds(816, 44, 161, 29);
		panel.add(lblPushpins_1);
		
		JLabel lblPublic = new JLabel("Public");
		lblPublic.setFont(new Font("Arial", Font.BOLD, 25));
		lblPublic.setFont(font.deriveFont(attributes));
		lblPublic.setBounds(327, 0, 161, 42);
		panel.add(lblPublic);
		
		JLabel label_1 = new JLabel("Public");
		label_1.setFont(new Font("Arial", Font.BOLD, 25));
		label_1.setFont(font.deriveFont(attributes));
		label_1.setBounds(489, 0, 161, 42);
		panel.add(label_1);
		
		JLabel lblPrivate = new JLabel("Private");
		lblPrivate.setFont(new Font("Arial", Font.BOLD, 25));
		lblPrivate.setFont(font.deriveFont(attributes));
		lblPrivate.setBounds(653, 0, 161, 42);
		panel.add(lblPrivate);
		
		JLabel lblPrivate_1 = new JLabel("Private");
		lblPrivate_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblPrivate_1.setFont(font.deriveFont(attributes));
		lblPrivate_1.setBounds(816, 0, 161, 42);
		panel.add(lblPrivate_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 75, 996, 166);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.setTableHeader(null);
		table.setBorder(null);
		table.setBackground(SystemColor.control);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setRequestFocusEnabled(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setSelectionForeground(SystemColor.control);
		table.setSelectionBackground(SystemColor.control);
		table.setRowHeight(35);
		table.setFont(new Font("Arial", Font.PLAIN, 25));

		scrollPane.setViewportView(table);
		BoardStat rsBoardStat = DataProvider.getBoardStat();
		ResultSet rs = rsBoardStat.getBoardStat();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
		{
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		    {
		        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
		        int rowNm = 0;
				
				try {
					rs.beforeFirst();
					while(rs.next()) {
						if(user_id ==rs.getInt("user_id")) {
							
							break;
						}
						rowNm +=1;
						}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
				
		        c.setForeground(row == rowNm ? Color.RED: Color.BLACK);
		        
		        return c;
		    }
		});
		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		table.getColumnModel().getColumn(0).setPreferredWidth(235);
		
		
		JLabel lblCorkboardStatistics = new JLabel("CorkBoard Statistics");
		lblCorkboardStatistics.setFont(new Font("Arial", Font.BOLD, 30));
		lblCorkboardStatistics.setBounds(12, 128, 390, 33);
		contentPane.add(lblCorkboardStatistics);
	}
}
