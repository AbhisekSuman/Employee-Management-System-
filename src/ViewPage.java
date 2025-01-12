import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Backend.EmployeeApp;

import javax.swing.JLabel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel salaryField;
	private String empName;
	private String department;
	private String salary;

	/**
	 * Create the frame.
	 */
	public ViewPage(int id, JFrame callerFrame) {
		

		
		ImageIcon img = new ImageIcon("E:\\Kodenest\\EmployeeManagementSystem\\src\\images\\profileicon.png");
		// Scale the image to desired size (e.g., 100x100)
        Image scaledImage = img.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH); // Change 100x100 to desired size
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 654);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel profileicon = new JLabel();
		profileicon.setIcon(scaledIcon);
		 // Set size to the image's natural size for better display
        profileicon.setBounds(139, 10, 250, 260);
//		profileicon.setBounds(465, 51, 45, 13);
		contentPane.add(profileicon);
		
		EmployeeApp emp = new EmployeeApp();
		ResultSet res = emp.getEmployee(id);
		try {
			res.next();
			empName = res.getString(2);
			department = res.getString(3);
			salary = String.valueOf(res.getInt(4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel name_field = new JLabel(empName);
		name_field.setForeground(new Color(0, 0, 255));
		name_field.setHorizontalAlignment(SwingConstants.CENTER);
		name_field.setFont(new Font("Bernard MT Condensed", Font.BOLD, 26));
		name_field.setBounds(101, 282, 358, 50);
		contentPane.add(name_field);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_1.setBounds(25, 396, 70, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("DEPARTMENT:");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(25, 456, 142, 35);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("SALARY:");
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(25, 516, 98, 35);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel id_field = new JLabel(String.valueOf(id));
		id_field.setFont(new Font("Modern No. 20", Font.PLAIN, 16));
		id_field.setHorizontalAlignment(SwingConstants.CENTER);
		id_field.setBounds(58, 398, 90, 29);
		contentPane.add(id_field);
		
		JLabel dept = new JLabel(department);
		dept.setHorizontalAlignment(SwingConstants.CENTER);
		dept.setFont(new Font("Modern No. 20", Font.PLAIN, 16));
		dept.setBounds(139, 458, 171, 29);
		contentPane.add(dept);
		
		salaryField = new JLabel(salary);
		salaryField.setHorizontalAlignment(SwingConstants.CENTER);
		salaryField.setFont(new Font("Modern No. 20", Font.PLAIN, 16));
		salaryField.setBounds(101, 518, 81, 29);
		contentPane.add(salaryField);
		
		JButton btnNewButton = new JButton("EDIT");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.setFont(new Font("STKaiti", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdatePage(id, empName, department, salary, callerFrame);
				setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ViewPage.class.getResource("/images/icons8-edit-30.png")));
		btnNewButton.setBounds(279, 569, 121, 35);
		contentPane.add(btnNewButton);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageBox deleteBox = new MessageBox();
				deleteBox.delete(id);
			}
		});
		btnDelete.setBackground(new Color(255, 255, 255));
		btnDelete.setIcon(new ImageIcon(ViewPage.class.getResource("/images/icons8-delete-24.png")));
		btnDelete.setForeground(new Color(255, 0, 0));
		btnDelete.setFont(new Font("Sitka Text", Font.BOLD, 14));
		btnDelete.setBounds(410, 569, 134, 35);
		contentPane.add(btnDelete);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callerFrame.setVisible(true);
			}	
		});
		btnBack.setIcon(new ImageIcon(ViewPage.class.getResource("/images/icons8-back-50.png")));
		btnBack.setForeground(new Color(0, 128, 255));
		btnBack.setFont(new Font("STKaiti", Font.BOLD, 12));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(10, 10, 121, 50);
		contentPane.add(btnBack);
		
		setVisible(true);
	}
}
