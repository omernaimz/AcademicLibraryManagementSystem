package view;



import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WelcomeJframe extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeJframe frame = new WelcomeJframe();
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
	public WelcomeJframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblWelcomeLabel = new JLabel("Welcome!");
		lblWelcomeLabel.setBounds(172, 10, 89, 25);
		lblWelcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblWelcomeLabel);
		
		JButton btnStartProgram = new JButton("Library Manager");
		btnStartProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UserActionFrame userAction = new UserActionFrame();
				userAction.UserActionScreen();
				try {
					model.Library.UpdateStudentList();
					model.Library.UpdateBookList();
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnStartProgram.setBounds(10, 59, 180, 25);
		contentPane.add(btnStartProgram);
		
		JButton btnDeveloperInfo = new JButton("Developers");
		btnDeveloperInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "Watermelone!");
			}
		});
		btnDeveloperInfo.setBounds(322, 227, 102, 23);
		contentPane.add(btnDeveloperInfo);
	}
}
