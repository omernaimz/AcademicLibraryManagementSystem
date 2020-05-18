package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;

import javax.naming.ldap.Rdn;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;

public class UserActionFrame extends JFrame {

	private JPanel contentPanelMain;

	/**
	 * Launch the application.
	 */
	public static void UserActionScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserActionFrame frame = new UserActionFrame();
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
	public UserActionFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPanelMain = new JPanel();
		contentPanelMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanelMain);
		contentPanelMain.setLayout(null);
		setLocationRelativeTo(null);

		JButton btnNewAuthor = new JButton("add author");
		btnNewAuthor.setBounds(312, 227, 89, 23);
		contentPanelMain.add(btnNewAuthor);
		
		JButton btnRegisterStudent = new JButton("Register Student");
		btnRegisterStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterStudentFrame regStudentFrame = new RegisterStudentFrame();
				regStudentFrame.RegisterStudentScreen();	
			}
		});
		btnRegisterStudent.setBounds(10, 11, 134, 23);
		contentPanelMain.add(btnRegisterStudent);
		
		JButton btnSearchStudent = new JButton("Search Student");
		btnSearchStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchStudentFrame SearchStudent = new SearchStudentFrame();
		//		SearchStudent.SetTextFieldSearchByID(true);
		//		SearchStudent.SetTextFieldSearchByName(false);
				SearchStudent.SearchStudentScreen();
								
			}
		});
		btnSearchStudent.setBounds(10, 45, 134, 23);
		contentPanelMain.add(btnSearchStudent);
		
		JButton btnRemoveStudent = new JButton("Unregister Student");
		btnRemoveStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnRemoveStudent.setBounds(10, 79, 160, 23);
		contentPanelMain.add(btnRemoveStudent);
		btnNewAuthor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				AuthorFrame authorFrame = new AuthorFrame();
				authorFrame.AuthorScreen();
			}
		});
	}
}
