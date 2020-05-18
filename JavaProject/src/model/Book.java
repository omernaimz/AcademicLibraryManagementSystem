package model;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Book {
	private static final String k_LendingHistoryLog = "Lending_log.txt";

	// Fields
	private static Book m_BookInstance;
	private static int m_ObjCount = 0;
	private static int m_IDGenerator = 100000;
	private int m_ID;
	private String m_Title;
	private Author m_Author;
	private Publisher m_Publisher;
	private boolean m_IsLended;
	private String m_Category;

	// Constructor
	private Book()
	{
		m_ObjCount++;
	}
	
	private Book(String i_Title, Author i_Author, Publisher i_Publisher, String i_Category) {
		m_ID = ++m_IDGenerator;
		m_Title = i_Title;
		m_Author = i_Author;
		m_Publisher = i_Publisher;
		m_IsLended = false;
		m_Category = i_Category;
		
		m_ObjCount++;
	}

	public static synchronized Book GetBookInstance(String i_Title, Author i_Author, Publisher i_Publisher, String i_Category)
	{
		if(m_ObjCount < 500)
		{
			m_BookInstance = new Book(i_Title, i_Author, i_Publisher, i_Category);
		}
		
		return m_BookInstance;
	}
	
	public static synchronized Book GetBookInstance()
	{
		if(m_ObjCount < 500)
		{
			m_BookInstance = new Book();
		}
		
		return m_BookInstance;
	}
	
	
	// Methods
	public void Lend(Student i_Student) {
		String newLine = System.lineSeparator();
		String fileName = "Lending_log.txt";

		// This string to append to file
		StringBuilder strToAppendFile = new StringBuilder();

		// Add new line before the input line, because of data aggregation
		strToAppendFile.append(newLine);

		// This for id of student
		String IDOfStudent = String.valueOf(i_Student.GetStudentID());

		// Set book status to lent
		SetIsLended(true);

		/* File looks like: BookID, Lending date, Return date, StudentID */

		// Set Book ID
		strToAppendFile.append(m_ID);
		addCommaAndSpaceToStringBuilder(strToAppendFile);

		// Set lending date
		String currentDate = new SimpleDateFormat("dd-M-yyyy hh:mm:ss").format(Calendar.getInstance().getTime());
		strToAppendFile.append(currentDate);
		addCommaAndSpaceToStringBuilder(strToAppendFile);

		// Set return date to NULL
		// In return date we will replace NULL with return date
		strToAppendFile.append("NULL");
		addCommaAndSpaceToStringBuilder(strToAppendFile);

		// Set Student ID
		strToAppendFile.append(IDOfStudent);

		// Add string to log file
		FileHandler.WriteToFile(fileName, strToAppendFile);
	}

	private void addCommaAndSpaceToStringBuilder(StringBuilder i_String) {
		String commaAndSpace = ", ";

		i_String.append(commaAndSpace);
	}

	public void ReturnFromLend() {
		SetIsLended(false);
		// TODO add to lending history return today date
	}

	public int NumberOfPeopleLended() throws IOException {
		// Go thru lended history, and count number of "lending date"
		String idInString = Integer.toString(m_ID);
		int appearanceNumber = 0;

		appearanceNumber = FileHandler.FindAppearanceCountByID(k_LendingHistoryLog, idInString);

		return appearanceNumber;
	}

	public Date LastLendedDate() throws IOException, ParseException {
		// Book ID in string format
		String stringID = Integer.toString(m_ID);

		// This will store the last line appearance
		String[] lastAppearanceLine = FileHandler.FindLastAppearanceLineByID(k_LendingHistoryLog, stringID);

		// This will store the last lended date, the second value in the array
		String lastLendedDateInString = lastAppearanceLine[1];

		// Convert string to date
		DateFormat df = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Date lastLendedDate = df.parse(lastLendedDateInString);

		// Return result
		return lastLendedDate;
	}

	public Student LastStudentLended() throws IOException {
		// Book ID in string format
		String stringID = Integer.toString(m_ID);

		// This will store the last line appearance
		String[] lastAppearanceLine = FileHandler.FindLastAppearanceLineByID(k_LendingHistoryLog, stringID);

		// This will store the last student id, the fourth value in the array
		String lastLendedStudentID = lastAppearanceLine[3];

		// Convert ID to student object
		Student studentToReturn;
		studentToReturn = Library.SearchStudentByID(lastLendedStudentID);

		// Return the result
		return studentToReturn;
	}

	// Getters
	public int GetID() {
		return m_ID;
	}

	public String GetTitle() {
		return m_Title;
	}

	public Author GetAuthor() {
		return m_Author;
	}

	public Publisher GetPublisher() {
		return m_Publisher;
	}

	public String GetCategory() {
		return m_Category;
	}

	/*public LendingHistory GetLendingHistory() {
		return m_LendingHistory;
	}*/

	public boolean GetIsLended() {
		return m_IsLended;
	}

	// Setters
	public void SetID(int i_ID) {
		m_ID = i_ID;
	}

	public void SetTitle(String i_Title) {
		m_Title = i_Title;
	}

	public void SetAuthor(Author i_Author) {
		m_Author = i_Author;
	}

	public void SetPublisher(Publisher i_Publisher) {
		m_Publisher = i_Publisher;
	}

	/*public void SetLendingHistory(LendingHistory i_LendingHistory) {
		// TODO
		m_LendingHistory = i_LendingHistory;
	}*/

	public void SetIsLended(boolean i_IsLended) {
		m_IsLended = i_IsLended;
	}
	
	public void SetCategory(String i_Category)
	{
		m_Category = i_Category;
	}
}