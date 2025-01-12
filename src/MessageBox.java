import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Backend.EmployeeApp;

public class MessageBox extends JFrame {
	public MessageBox() {
	}

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public void delete(int id) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
		int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm", JOptionPane.YES_NO_OPTION);
				try {
//			        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			        frame.setVisible(true);

			        // Confirmation Dialog
			        if (response == JOptionPane.YES_OPTION) {
			            EmployeeApp emp = new EmployeeApp();
			            emp.removeEmployee(id);
			            showMessage("Deleted Succesfully");
			            return;
			        } else {
			            System.out.println("You chose NO");
			            return;
			        }
				} catch (Exception e) {
					e.printStackTrace();
				}
//			}
//		});
	}

	public void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
	}
}
