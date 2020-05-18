package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterStudentFrame extends JFrame
{

	private JPanel contentPane;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldPhoneNumber;
	private JLabel lblID;
	private JTextField textFieldID;

	/**
	 * Launch the application.
	 */
	public static void RegisterStudentScreen()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					RegisterStudentFrame frame = new RegisterStudentFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterStudentFrame()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 44, 74, 14);
		contentPane.add(lblFirstName);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(117, 41, 200, 20);
		contentPane.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(10, 75, 74, 14);
		contentPane.add(lblLastName);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(117, 72, 200, 20);
		contentPane.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(10, 106, 97, 14);
		contentPane.add(lblPhoneNumber);
		
		textFieldPhoneNumber = new JTextField();
		textFieldPhoneNumber.setBounds(117, 103, 200, 20);
		contentPane.add(textFieldPhoneNumber);
		textFieldPhoneNumber.setColumns(10);
		
		JButton btnNewButton = new JButton("Register Student");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
			StringBuilder studentInformation = new StringBuilder();
			boolean isSuccessRegisterStudent;
			
			// Getting information from text fields
			studentInformation = fetchStudentInfoFromFrame();
			
			// Sending information to Library Controller
			isSuccessRegisterStudent = controller.LibraryController.ControllerRegisterStudent(studentInformation);
			
			// Clear all text fields
			clearTextFields();

			// Pop up the result to user
			JOptionPane.showMessageDialog(null, textToPopUp(isSuccessRegisterStudent));
			}
		});
		btnNewButton.setBounds(167, 175, 113, 23);
		contentPane.add(btnNewButton);
		
		lblID = new JLabel("ID:");
		lblID.setBounds(10, 11, 74, 14);
		contentPane.add(lblID);
		
		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(117, 8, 200, 20);
		contentPane.add(textFieldID);
	}
	
	// Building student information
	private StringBuilder fetchStudentInfoFromFrame()
	{
		StringBuilder strStudentInfo = new StringBuilder();
		
		// Insert student id
		strStudentInfo.append(textFieldID.getText());
		strStudentInfo.append(",");
		
		// Insert student first name
		strStudentInfo.append(textFieldFirstName.getText());
		strStudentInfo.append(",");
		
		// Insert student last name
		strStudentInfo.append(textFieldLastName.getText());
		strStudentInfo.append(",");
		
		// Insert student phone number
		strStudentInfo.append(textFieldPhoneNumber.getText());
		
		return strStudentInfo;
	}
	
	// Clear firstName, lastName, phoneNumber fields
	private void clearTextFields()

	{
		textFieldID.setText("");
		textFieldFirstName.setText("");
		textFieldLastName.setText("");
		textFieldPhoneNumber.setText("");
	}
	
	// Prompting user whether register is successful or not
	private String textToPopUp(boolean i_RegisterState)
	{
		String textToShow;
		
		if(i_RegisterState)
		{
			textToShow = "Register successful";
		}
		else
		{
			textToShow = "Register unsuccessful";
		}
		
		return textToShow;
	}
}


