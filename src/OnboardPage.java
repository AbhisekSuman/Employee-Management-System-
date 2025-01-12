import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Backend.EmployeeApp;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class OnboardPage extends JFrame {

	private JTextField nameField;
	private JTextField deptField;
	private JTextField salField;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public OnboardPage(JFrame frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name *");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		lblNewLabel.setBounds(49, 60, 89, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblDepartment = new JLabel("Department *");
		lblDepartment.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		lblDepartment.setBounds(49, 186, 169, 28);
		contentPane.add(lblDepartment);
		
		JLabel lblNewLabel_2 = new JLabel("Salary *");
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		lblNewLabel_2.setBounds(49, 301, 89, 28);
		contentPane.add(lblNewLabel_2);
		
		nameField = new JTextField();
		nameField.setText("");
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameField.setBounds(42, 102, 364, 39);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		deptField = new JTextField();
		deptField.setText("");
		deptField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deptField.setColumns(10);
		deptField.setBounds(42, 237, 364, 39);
		contentPane.add(deptField);
		
		salField = new JTextField();
		salField.setText("");
		salField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		salField.setColumns(10);
		salField.setBounds(42, 351, 364, 39);
		contentPane.add(salField);
		
		
		JButton addButton = new JButton("ONBOARD");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String dept = deptField.getText();
				int sal = Integer.parseInt(salField.getText());
				EmployeeApp emp = new EmployeeApp();
				
				emp.addEmployee(name, dept, sal);
				MessageBox message = new MessageBox();
				message.showMessage("Employee Onboarded SuccessFully");
				frame.setVisible(true);
				setVisible(false);
			}
		});
		addButton.setBackground(new Color(255, 255, 255));
		addButton.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		addButton.setForeground(new Color(128, 0, 255));
		addButton.setBounds(574, 476, 121, 47);
		contentPane.add(addButton);
		
		JLabel lblNewLabel_1 = new JLabel("ONBOARD PAGE");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(128, 0, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(167, 10, 406, 39);
		contentPane.add(lblNewLabel_1);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setIcon(new ImageIcon(OnboardPage.class.getResource("/images/icons8-back-50.png")));
		btnBack.setForeground(new Color(128, 0, 255));
		btnBack.setFont(new Font("STKaiti", Font.BOLD, 12));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(431, 476, 121, 50);
		contentPane.add(btnBack);
		setVisible(true);
	}
}
