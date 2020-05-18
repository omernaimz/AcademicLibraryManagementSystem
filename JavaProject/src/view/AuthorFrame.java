package view;
import controller.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.sound.midi.ControllerEventListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorFrame extends JFrame {

	private JPanel contentPanelAddAuthor;
	private JTextField textFieldAuthorName;
	private JTextField textFieldAuthorPhone;
	private static final String k_EmptyString = "";

	/**
	 * Launch the application.
	 */
	public static void AuthorScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthorFrame frame = new AuthorFrame();
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
	public void AuthorFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) 
			{
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 331, 218);
		contentPanelAddAuthor = new JPanel();
		contentPanelAddAuthor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanelAddAuthor);
		contentPanelAddAuthor.setLayout(null);

		JLabel lblAuthorName = new JLabel("Name:");
		lblAuthorName.setBounds(10, 11, 55, 14);
		contentPanelAddAuthor.add(lblAuthorName);

		textFieldAuthorName = new JTextField();
		textFieldAuthorName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textFieldAuthorName.setBounds(75, 8, 200, 20);
		contentPanelAddAuthor.add(textFieldAuthorName);
		textFieldAuthorName.setColumns(10);

		// Onclick button add author to DB
		JButton btnAddAuthor = new JButton("Add author");
		btnAddAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//TODO move to controller
				
				// This will be added to DB
				StringBuilder strToAddToFile;
				
				// This will contain text from TextField of Author Name
				String strFromAuthorNameTB = textFieldAuthorName.getText();
				
				// Remove leading and trailing spaces
				strFromAuthorNameTB.trim();
			
				/* Check if name is only letters and spaces */
				boolean isAuthorNameValid = AuthorController.CheckIfAuthorNameValid(strFromAuthorNameTB);				
				
				// Check conditions
				if(!isAuthorNameValid)
				{
					// Popup a message that input is invalid
					JOptionPane.showMessageDialog(null, "Name invalid!");
					
					// Empty the textbox
					textFieldAuthorName.setText(k_EmptyString);
					
					// Stop the method
					return;
				}
				
				// This will contain text from TextField of Author Phone
				String strFromAuthorPhoneTB = textFieldAuthorPhone.getText();	
				
				/* Check if phone is only numbers*/
				boolean isAuthorPhoneValid = AuthorController.CheckIfAuthorPhoneValid(strFromAuthorPhoneTB);
				
				if(!isAuthorPhoneValid)
				{
					// Popup a message that input is invalid
					JOptionPane.showMessageDialog(null, "Phone invalid!");
					
					// Empty the textbox
					textFieldAuthorName.setText(k_EmptyString);
					
					// Stop the method
					return;
				}
				
				// Empty the textbox
				textFieldAuthorName.setText(k_EmptyString);
			}
			
			
		});		
		btnAddAuthor.setBounds(105, 116, 115, 23);
		contentPanelAddAuthor.add(btnAddAuthor);
		
		JLabel lblAuthorPhone = new JLabel("Phone:");
		lblAuthorPhone.setBounds(10, 55, 46, 14);
		contentPanelAddAuthor.add(lblAuthorPhone);
		
		textFieldAuthorPhone = new JTextField();
		textFieldAuthorPhone.setBounds(75, 52, 200, 20);
		contentPanelAddAuthor.add(textFieldAuthorPhone);
		textFieldAuthorPhone.setColumns(10);
	}
}
