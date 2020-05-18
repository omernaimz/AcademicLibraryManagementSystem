package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchStudentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearchByID;
	private JTextField textFieldSearchByFirstAndLastName;
	private final ButtonGroup SearchStudentOptions = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void SearchStudentScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchStudentFrame frame = new SearchStudentFrame();
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
	public SearchStudentFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JRadioButton rdbtnSearchByID = new JRadioButton("Search By ID:");
		rdbtnSearchByID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetTextFieldSearchByID(true);
				SetTextFieldSearchByName(false);
	//			textFieldSearchByFirstAndLastName.setText("");
			}
		});
		rdbtnSearchByID.setSelected(true);
		SearchStudentOptions.add(rdbtnSearchByID);
		rdbtnSearchByID.setBounds(6, 7, 109, 23);
		contentPane.add(rdbtnSearchByID);
		
		JRadioButton rdbtnSearchByFirstAndLastName = new JRadioButton("Search By First And Last Name:");
		rdbtnSearchByFirstAndLastName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetTextFieldSearchByName(true);
				SetTextFieldSearchByID(false);
	//			textFieldSearchByID.setText("");
			}
		});
		SearchStudentOptions.add(rdbtnSearchByFirstAndLastName);
		rdbtnSearchByFirstAndLastName.setBounds(6, 98, 177, 23);
		contentPane.add(rdbtnSearchByFirstAndLastName);
		
		textFieldSearchByID = new JTextField();
		textFieldSearchByID.setBounds(6, 37, 177, 20);
		contentPane.add(textFieldSearchByID);
		textFieldSearchByID.setColumns(10);
		
		textFieldSearchByFirstAndLastName = new JTextField();
		textFieldSearchByFirstAndLastName.setBounds(6, 128, 177, 20);
		contentPane.add(textFieldSearchByFirstAndLastName);
		textFieldSearchByFirstAndLastName.setColumns(10);
		textFieldSearchByFirstAndLastName.setEditable(false); 
		
		JButton btnSearchStudent = new JButton("Search Student");
		btnSearchStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String textFromTextField;
				
				if (textFieldSearchByID.isEditable())
				{
					textFromTextField = fetchTextFromIDTextField();
					
				}
				else // Search by first and last names
				{
					textFromTextField = fetchTextFromNamesTextField();
				}
				
				
			}
		});
		btnSearchStudent.setBounds(288, 227, 136, 23);
		contentPane.add(btnSearchStudent);
	}
	
	private void SetTextFieldSearchByID(boolean i_State)
	{
		textFieldSearchByID.setEditable(i_State);
	}
	
	private void SetTextFieldSearchByName(boolean i_State)
	{
		textFieldSearchByFirstAndLastName.setEditable(i_State);
	}
	
	private String fetchTextFromIDTextField()
	{
		String IDString;
		
		IDString = textFieldSearchByID.getText();
		
		return IDString;
	}
	
	private String fetchTextFromNamesTextField()
	{
		String FirstAndLastNameString;
		
		FirstAndLastNameString = textFieldSearchByID.getText();
		
		return FirstAndLastNameString;
	}

}
