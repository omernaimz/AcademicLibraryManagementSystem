package model;
import java.awt.print.Book;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LateStudent extends Student {
	// Fields
	private int m_TotalDaysPastReturn;

	// Constructor
	LateStudent(int i_StudentID, String i_FirstName, String i_LastName, int i_PhoneNumber, ArrayList<Course> i_Courses,
			ArrayList<Book> i_CurrentBooksLended, int i_NumOfCurrentLendedBooks) {
		
		super(i_StudentID, i_FirstName, i_LastName, i_PhoneNumber, i_NumOfCurrentLendedBooks);
	}

	// Methods
	public int GetTotalDaysPastReturn() {
		return m_TotalDaysPastReturn;
	}

	public void SetTotalDaysPastReturn(int i_TotalDaysPastReturn) {
		m_TotalDaysPastReturn = i_TotalDaysPastReturn;
	}

	// TODO Controller
	public void BlockPersonalPortal() {
		String messageToPopUp;

		messageToPopUp = "Portal blocked!";
		JOptionPane.showMessageDialog(null, messageToPopUp);
	}

	public void SendAlertSMS() {
		// TODO
	}
}