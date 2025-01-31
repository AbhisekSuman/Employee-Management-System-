import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import Backend.EmployeeApp;

import javax.swing.ListSelectionModel;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class HomePage {

	protected static JFrame frame;
	private JTextField searchTxt;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
 	 */
	public HomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1197, 706);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 40));
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBounds(266, 10, 669, 36);
		frame.getContentPane().add(lblNewLabel);
		
//		SEARCH FUNCTIONALIY
		searchTxt = new JTextField();
		searchTxt.setFont(new Font("Arial", Font.BOLD, 16));
		searchTxt.setText("SEARCH");
		searchTxt.setBounds(41, 101, 238, 36);
		frame.getContentPane().add(searchTxt);
		searchTxt.setColumns(1);
		
		JButton searchBtn = new JButton("🔍");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (searchTxt.getText().isBlank() || searchTxt.getText().isEmpty() || !searchTxt.getText().matches("[0-9]*"))
				{
					new MessageBox().showMessage("Enter a Valid ID");
					return;
				}
				int id = Integer.parseInt(searchTxt.getText());
				
				EmployeeApp emp = new EmployeeApp();
				ResultSet res = emp.getEmployee(id);
				
				tableStructure(res);
			}
		});
		searchBtn.setBounds(283, 101, 51, 38);
		frame.getContentPane().add(searchBtn);
		
		JButton addBtn = new JButton("NEW +");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new OnboardPage(frame);
			}
		});
		addBtn.setFont(new Font("Tahoma", Font.BOLD, 19));
		addBtn.setBackground(new Color(255, 255, 255));
		addBtn.setForeground(new Color(0, 0, 255));
		addBtn.setBounds(1013, 101, 135, 57);
		frame.getContentPane().add(addBtn);
	
		
		// Create the JTable with data and column names
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		EmployeeApp emp = new EmployeeApp();
		ResultSet res = emp.getEmployees();
		tableStructure(res);
		
		  // Styling
        table.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        table.setFont(new Font("Tahoma", Font.PLAIN, 19));
        table.setForeground(Color.BLACK);
        table.setBackground(Color.WHITE);
        table.setRowHeight(40);

        // Table header styling
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(32, 136, 203));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Castellar", Font.BOLD, 24));
//        table.getTableHeader().
		
		// Wrap the JTable in a JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 164, 1163, 360);

		// Add the JScrollPane to the frame, not the JTable directly
		frame.getContentPane().add(scrollPane);
		
		
//		REFRESH FUNCTIONALITY
		JButton refershBtn = new JButton("");
		refershBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        EmployeeApp emp = new EmployeeApp();
		        ResultSet res = emp.getEmployees();
		        tableStructure(res);
		    }
		});

		refershBtn.setBackground(new Color(255, 255, 255));
		refershBtn.setIcon(new ImageIcon(HomePage.class.getResource("/images/icons8-refresh-30.png")));
		refershBtn.setBounds(918, 110, 65, 43);
		frame.getContentPane().add(refershBtn);
		
		JLabel lblNewLabel_1 = new JLabel("©️KODNEST | ALL RIGHTS RESERVED");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(409, 646, 381, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("EMPLOYEES");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(569, 145, 146, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
	}
	
	void tableStructure(ResultSet res) {
		 // Create Table Model
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only make the "Actions" column editable
                return column == getColumnCount() - 1;
            }
        };

		try {
		    if (res != null) {
		        ResultSetMetaData metaData = res.getMetaData();

		        // Prepare data
		        int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    model.addColumn(metaData.getColumnName(i));
                }
                model.addColumn("Actions"); // Add the Actions column
		        
		        while (res.next()) {
		            Object[] row = new Object[columnCount + 1];
		            for (int i = 1; i <= columnCount; i++) {
		                row[i - 1] = res.getObject(i);
		            }
                    row[columnCount] = "Actions"; // Placeholder for the Actions column
		            model.addRow(row);
		        }

		        // Set model to JTable
		        table.setModel(model);
		        
		     // Add custom renderer and editor for the Actions column
	            table.getColumnModel().getColumn(model.getColumnCount() - 1).setCellRenderer(new ActionRenderer());
	            table.getColumnModel().getColumn(model.getColumnCount() - 1).setCellEditor(new ActionEditor(table));
	        // Center renderer
		        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		        for (int i = 0; i < table.getColumnCount(); i++) {
		            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		        }
		    }
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}
	}
	
	// Custom Renderer for the Actions column
    static class ActionRenderer extends JPanel implements TableCellRenderer {

		private static final long serialVersionUID = 1L;

		public ActionRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            this.removeAll(); // Clear previous buttons
            add(createButton(row, "/images/icons8-view-24.png"));
            add(createButton(row, "/images/icons8-edit-30.png"));
            add(createButton(row, "/images/icons8-delete-24.png"));
            return this;
        }

        private JButton createButton(int row, String path) {
            JButton button = new JButton("");
            button.setFont(new Font("Tahoma", Font.PLAIN, 14));
            button.setFocusable(false);
            button.setBackground(new Color(255, 255, 255));
            button.setBounds(885, 563, 57, 28);
            button.setIcon(new ImageIcon(HomePage.class.getResource(path)));
            return button;
        }
    }
	
	// Custom Editor for the Actions column
    static class ActionEditor extends AbstractCellEditor implements TableCellEditor {
        private JPanel panel;
        private JButton viewButton, editButton, deleteButton;
        private JTable table;

        public ActionEditor(JTable table) {
            this.table = table;
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));

            viewButton = new JButton("");
            editButton = new JButton("");
            deleteButton = new JButton("");
            
            viewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
            viewButton.setFocusable(false);
            viewButton.setBackground(new Color(255, 255, 255));
            viewButton.setBounds(885, 563, 57, 28);
            viewButton.setIcon(new ImageIcon(HomePage.class.getResource("/images/icons8-view-24.png")));

            editButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
            editButton.setFocusable(false);
            editButton.setBackground(new Color(255, 255, 255));
            editButton.setBounds(885, 563, 57, 28);
            editButton.setIcon(new ImageIcon(HomePage.class.getResource("/images/icons8-edit-30.png")));
            
            deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
            deleteButton.setFocusable(false);
            deleteButton.setBackground(new Color(255, 255, 255));
            deleteButton.setBounds(885, 563, 57, 28);
            deleteButton.setIcon(new ImageIcon(HomePage.class.getResource("/images/icons8-delete-24.png")));
            
            // Add Action Listeners for buttons
            viewButton.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					 int row = table.getSelectedRow(); // Get the selected row
			            if (row != -1) {
			                // Extract data from the row
			                Object idObject = table.getValueAt(row, 0); // Assuming the first column is "id"
			                int id = Integer.parseInt(idObject.toString());
			                frame.setVisible(false);
			                new ViewPage(id, frame);

			            stopCellEditing();
				}
				}
			});
         
            editButton.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow(); // Get the selected row
		            if (row != -1) {
		                // Extract data from the row
		                Object idObject = table.getValueAt(row, 0); // Assuming the first column is "id"
		                Object nameObject = table.getValueAt(row, 1);
		                Object deptObject = table.getValueAt(row, 2);
		                Object salObject = table.getValueAt(row, 3);
		                int id = Integer.parseInt(idObject.toString());
		                String name = nameObject.toString();
		                String dept = deptObject.toString();
		                String sal = salObject.toString();
		                
		                frame.setVisible(false);
		                new UpdatePage(id, name, dept, sal, frame);
		                
		            stopCellEditing();
			} 
				}
			});
            
            deleteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow(); // Get the selected row
		            if (row != -1) {
		                // Extract data from the row
		                Object idObject = table.getValueAt(row, 0); // Assuming the first column is "id"
		                int id = Integer.parseInt(idObject.toString());
		                
		               MessageBox deleteBox = new MessageBox();
		               deleteBox.delete(id);

		            stopCellEditing();
			}         
				}
			});

            panel.add(viewButton);
            panel.add(editButton);
            panel.add(deleteButton);
        }


        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return null; // No specific value for the Actions column
        }
    }
}