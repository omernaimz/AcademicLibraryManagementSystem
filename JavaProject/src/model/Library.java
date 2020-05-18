package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class Library 
{
	private static final String k_StudentDBFile = "StudentsDB.txt";
	private static final String k_BooksDBFile = "BooksDB.txt";
	private static final String k_LendingHistoryDB = "Lending_log.txt";
	private static final String k_AuthorDB = "AuthorDB.txt";
	private static final String k_PublisherDB = "PublisherDB.txt";
	
	// Fields
	private static ArrayList<Book> m_Books = new ArrayList<Book>();
	private static ArrayList<Student> m_Students = new ArrayList<Student>();

	// Constructor ??? static

	// Methods
	public static void UpdateStudentList() throws NumberFormatException, IOException 
	{
		/* Update list of students from DB to ArrayList */
		String sCurrentLine;

		BufferedReader br = new BufferedReader(new FileReader(k_StudentDBFile));

		while ((sCurrentLine = br.readLine()) != null)
		{
			String[] lineValues = sCurrentLine.split(" "); // Split the string on spaces into array
			Student studentToAdd = new Student(); //TODO id

			studentToAdd.SetStudentID(Integer.parseInt(lineValues[0].substring(0, lineValues[0].length() - 1)));
			studentToAdd.SetPhoneNumber(Integer.parseInt(lineValues[1].substring(0, lineValues[0].length() - 1)));
			studentToAdd.SetFirstName(lineValues[2].substring(0, lineValues.length-1));
			studentToAdd.SetCoursesTaken(stringCoursesTakenToArrayList(lineValues[2].substring(0, lineValues[0].length() - 1)));
			// studentToAdd.SetLendingHistory(/* TODO */);
			// studentToAdd.SetCurrentBooksLended(/* TODO */);
			studentToAdd.SetNumberOfCurrentLendedBooks(Integer.parseInt(lineValues[3].substring(0, lineValues[0].length() - 1)));	
			
			//TODO need to set name, maybe change values order in DB??

			m_Students.add(studentToAdd);
		}
		
		br.close();
	}	
	
	private static ArrayList<Course> stringCoursesTakenToArrayList(String i_Courses)
	{
		/* String looks like: CourseTaken^Cat|CourseTaken^Cat|CourseTaken^Cat */
		
		// Split string to each course
		String strSplitted[] = i_Courses.split("|");
		
		// Count how many courses
		int numOfCourses = strSplitted.length;
		
		// Array list to return
		ArrayList<Course> courseTakenList = new ArrayList<Course>();
		
		for (int i = 0; i < numOfCourses; i++)
		{
			// Course to add to list
			Course currentCourse = new Course();
			
			// [0] = CourseName, [1] = Category
			String[] splittedCourseNameAndCategory = splitCategoryString(strSplitted[i]);
			
			// Add to current course that will be added to array list
			currentCourse.SetCourseName(splittedCourseNameAndCategory[0]);
			currentCourse.SetCategory(splittedCourseNameAndCategory[1]);
			
			// Add to array list
			courseTakenList.add(currentCourse);
		}
		
		return courseTakenList;		
	}
	
	private static String[] splitCategoryString(String i_String)
	{
		// [0] = Course name, [1] = Category
		String strSplitted[] = i_String.split("^");
		
		return strSplitted;		
	}
	
	private static int countHowManyBooksLended(Student i_Student) throws IOException
	{
		String studentID = Integer.toString(i_Student.GetStudentID());
		String nullStr = ",NULL";
		
		return FileHandler.counStrAppearenceForID(k_LendingHistoryDB, studentID, nullStr);		
	}

	private static void addBookToBookList(Book i_Book) 
	{
		m_Books.add(i_Book);
	}

	private static void addStudentToStudentList(Student i_Student) 
	{
		m_Students.add(i_Student);
	}

	public static boolean RegisterStudent(Student i_Student) 
	{
		/*
		 * File looks like: ID, PhoneNumber, CourseTaken^Cat|CourseTaken^Cat|CourseTaken^Cat, 
		 * CurrentBookLended|CurrentBookLended|CurrentBookLended,
		 * NumberOfCurrentBooksLended, FirstName, LastName
		 */
		StringBuilder stringToAppendFile = new StringBuilder();

		// Add ID
		stringToAppendFile.append(i_Student.GetStudentID());
		addCommaAndSpaceToStringBuilder(stringToAppendFile);

		// Add phone number
		stringToAppendFile.append(i_Student.GetPhoneNumber());
		addCommaAndSpaceToStringBuilder(stringToAppendFile);

		// Add courses taken
		// TODO
		addCommaAndSpaceToStringBuilder(stringToAppendFile);

		// Add lending history
		//MAYBE NOT NEEDED IN THE BEGINING
		// TODO
		addCommaAndSpaceToStringBuilder(stringToAppendFile);

		// Add current books lended
		//MAYBE NOT NEEDED IN THE BEGINING
		// TODO
		addCommaAndSpaceToStringBuilder(stringToAppendFile);

		// Add number of current books lended
		//MAYBE NOT NEEDED IN THE BEGINING
		stringToAppendFile.append(i_Student.GetNumberOfCurrentLendedBooks());
		addCommaAndSpaceToStringBuilder(stringToAppendFile);
		
		// Add first name
		stringToAppendFile.append(i_Student.GetFirstName());
		addCommaAndSpaceToStringBuilder(stringToAppendFile);
		
		// Add last name
		stringToAppendFile.append(i_Student.GetLastName());
		//addCommaAndSpaceToStringBuilder(stringToAppendFile);

		// Append to file
		return FileHandler.WriteToFile(k_StudentDBFile, stringToAppendFile);
	}

	public static void RemoveStudent(Student i_Student) throws IOException 
	{
		// ID of student we want to remove
		String idToRemove = Integer.toString(i_Student.GetStudentID());

		// Remove line
		FileHandler.RemoveLineFromFile(k_StudentDBFile, idToRemove);

		// Update student list with new changes
		UpdateStudentList();
	}

	public static Student SearchStudentByID(String i_ID) 
	{
		// Calculate number of students in library
		int numberOfStudentsInLibrary = GetNumberOfStudentsInLibrary();

		// Assuming student will not be found. If he is, it will be updated to true
		Student studentToReturn = null;
		boolean isStudentFound = false;

		for (int i = 0; i < numberOfStudentsInLibrary; i++) 
		{
			Student currentStudent = m_Students.get(i);
			String currentStudentID = Integer.toString(currentStudent.GetStudentID());
			boolean isIDMatch = i_ID.intern() == currentStudentID.intern();

			if (isIDMatch) 
			{
				studentToReturn = currentStudent;
				isStudentFound = true;
				break;
			}
		}

		if (!isStudentFound) 
		{
			JOptionPane.showMessageDialog(null, "Not found!");
		}

		return studentToReturn;
	}

	public static ArrayList<Student> SearchStudentByFirstAndLastName(String i_FirstName, String i_LastName) 
	{
		// Calculate number of students in library
		int numberOfStudentsInLibrary = GetNumberOfStudentsInLibrary();

		// There can be a few students with the same first and last name!
		ArrayList<Student> studentsToReturn = new ArrayList<Student>();

		// Assuming student will not be found. If he is, it will be updated to true
		boolean isAtLeastOneStudentFound = false;

		for (int i = 0; i < numberOfStudentsInLibrary; i++) 
		{
			Student currentStudent = m_Students.get(i);
			boolean isFirstNameMatch = i_FirstName.intern() == currentStudent.GetFirstName().intern();
			boolean isLastNameMatch = i_LastName.intern() == currentStudent.GetLastName().intern();

			if (isFirstNameMatch && isLastNameMatch) 
			{
				studentsToReturn.add(currentStudent);
				isAtLeastOneStudentFound = true;
			}
		}

		if (!isAtLeastOneStudentFound) 
		{
			JOptionPane.showMessageDialog(null, "Not found!");
		}

		return studentsToReturn;
	}

	public static void UpdateBookList() throws NumberFormatException, IOException
	{
		/* Update list of books from DB to ArrayList */
		String sCurrentLine;

		BufferedReader br = new BufferedReader(new FileReader(k_BooksDBFile));		

		while ((sCurrentLine = br.readLine()) != null) 
		{
			/* File looks like: BookID, Harry Potter, AuthorID, value.... */
			String[] lineValues = sCurrentLine.split(","); // Split the string on spaces into array
			Book bookToAdd = Book.GetBookInstance();

			bookToAdd.SetID(Integer.parseInt(lineValues[0].substring(0, lineValues[0].length() - 1)));
			bookToAdd.SetTitle(lineValues[1].substring(0, lineValues[0].length() - 1));
			bookToAdd.SetAuthor(findAuthorByID(Integer.parseInt(lineValues[2].substring(0, lineValues[0].length() - 1))));
			bookToAdd.SetPublisher(findPublisherByID(Integer.parseInt(lineValues[3].substring(0, lineValues[0].length() - 1))));
			bookToAdd.SetCategory(lineValues[4].substring(0, lineValues[0].length() - 1));
			
			m_Books.add(bookToAdd);			
		}
	}
	
	public static Publisher findPublisherByID(int i_ID) throws IOException
	{
		/*File looks like: PublisherID, Name, Phone*/
		String lineInFile = FileHandler.FindRowByID(k_PublisherDB, Integer.toString(i_ID));
		
		// [0] = PublisherID, [1] = Name, [2] = Phone
		String[] splittedValues = lineInFile.split(",");
		
		// Publisher to return
		Publisher pubToReturn = new Publisher();
		
		// Calc and set ID
		int pubID = Integer.parseInt(splittedValues[0]);
		pubToReturn.SetID(pubID);
		
		// Calc and set Name
		String pubName = splittedValues[1];
		pubToReturn.SetName(pubName);
		
		// Calc and set PhoneNumber
		int pubPhoneNumber = Integer.parseInt(splittedValues[2]);
		pubToReturn.SetPhoneNumber(pubPhoneNumber);
		
		return pubToReturn;
	}
	
	public static Author findAuthorByID(int i_ID) throws IOException
	{
		/* File looks like: AuthorID, FirstName, LastName*/
		String lineInFile = FileHandler.FindRowByID(k_AuthorDB, Integer.toString(i_ID));
		
		// [0] = AuthorID, [1] = FirstName, [2] = LastName
		String[] splittedValues = lineInFile.split(",");
		
		// Author to return
		Author authorToReturn = new Author();
		
		// Calc and set ID
		int authorID = Integer.parseInt(splittedValues[0]);
		authorToReturn.SetID(authorID);
		
		// Calc and set FirstName
		String authorFirstName = splittedValues[1];
		authorToReturn.SetFirstName(authorFirstName);
		
		// Calc and set LastName
		String authorLastName = splittedValues[2];
		authorToReturn.SetLastName(authorLastName);
		
		return authorToReturn;		
	}	

	public static void InsertBook(Book i_Book)
    {
        /* File looks like: ID, Title, Author, Publisher, Category */
        StringBuilder stringToAppendFile = new StringBuilder();
        
        //Add comma and space after every value, except the last one (??)
        // Add ID
        stringToAppendFile.append(i_Book.GetID());
        addCommaAndSpaceToStringBuilder(stringToAppendFile);
        
        // Add title
        stringToAppendFile.append(i_Book.GetTitle());
        addCommaAndSpaceToStringBuilder(stringToAppendFile);
        
        // Add author
        // AuthorID|FirstName|LastName
        Author bookAuthor = i_Book.GetAuthor();
        stringToAppendFile.append(bookAuthor.GetID());
        stringToAppendFile.append("|");
        stringToAppendFile.append(bookAuthor.GetFirstName());
        stringToAppendFile.append("|");
        stringToAppendFile.append(bookAuthor.GetLastName());
        addCommaAndSpaceToStringBuilder(stringToAppendFile);
        
        // Add publisher
        // PublisherID|Name|PhoneNumber
        Publisher bookPublisher = i_Book.GetPublisher();
        stringToAppendFile.append(bookPublisher.GetID());
        stringToAppendFile.append("|");
        stringToAppendFile.append(bookPublisher.GetName());
        stringToAppendFile.append("|");
        stringToAppendFile.append(bookPublisher.GetPhoneNumber());
        addCommaAndSpaceToStringBuilder(stringToAppendFile);
        
        // Add category
        stringToAppendFile.append(i_Book.GetCategory());
        addCommaAndSpaceToStringBuilder(stringToAppendFile);        
        
        // Append to file
        FileHandler.WriteToFile(k_BooksDBFile, stringToAppendFile);
    }

	private static void addCommaAndSpaceToStringBuilder(StringBuilder i_String) 
	{
		String commaAndSpace = ", ";

		i_String.append(commaAndSpace);
	}

	public static void RemoveBook(Book i_Book) throws IOException
	{
		// Remove book by ID
		String bookID = Integer.toString(i_Book.GetID());
		FileHandler.RemoveLineFromFile(k_BooksDBFile, bookID);

		// Update book list with new changes
		UpdateBookList();
	}

	public static Book SearchBookByID(String i_ID) 
	{
		// Calculate number of books in library
		int numberOfBooksInLibrary = GetNumberOfBooksInLibrary();

		// Assuming book will not be found. If is is, it will be updated to true
		Book bookToReturn = null;
		boolean isBookFound = false;

		for (int i = 0; i < numberOfBooksInLibrary; i++) 
		{
			Book currentBook = m_Books.get(i);
			String bookID = Integer.toString(currentBook.GetID());
			
			boolean isIDMatch = i_ID.intern() == bookID.intern();

			if (isIDMatch) {
				bookToReturn = currentBook;
				isBookFound = true;
				break;
			}
		}

		if (!isBookFound) 
		{
			JOptionPane.showMessageDialog(null, "Not found!");
		}

		return bookToReturn;
	}

	public static ArrayList<Book> SearchBookByTitle(String i_Title) 
	{
		// Calculate number of books in library
		int numberOfBooksInLibrary = GetNumberOfBooksInLibrary();

		// Assuming no book will be found. If at least one is, it will be updated to
		// true
		ArrayList<Book> booksToReturn = new ArrayList<Book>();
		boolean isAtLeastOneBookFound = false;

		for (int i = 0; i < numberOfBooksInLibrary; i++) 
		{
			Book currentBook = m_Books.get(i);

			/*
			 * Match part of the title. For example, if book title is:
			 * "Harry Potter and the Chamber of Secrets", And the desired title is
			 * "Harry Potter", It is a match.
			 */
			boolean isTitleMatch = currentBook.GetTitle().toLowerCase().contains(i_Title.toLowerCase());

			if (isTitleMatch) {
				booksToReturn.add(currentBook);
				isAtLeastOneBookFound = true;
			}
		}

		if (!isAtLeastOneBookFound) 
		{
			JOptionPane.showMessageDialog(null, "Not found!");
		}

		return booksToReturn;
	}

	public static ArrayList<Book> SearchBookByAuthor(Author i_Author) 
	{
		// Calculate number of books in library
		int numberOfBooksInLibrary = GetNumberOfBooksInLibrary();

		// There can be more then one book of author
		ArrayList<Book> booksToReturn = new ArrayList<Book>();

		// Assuming no book will be found. If at least one is, it will be updated to
		// true
		boolean isAtLeastOneBookFound = false;

		for (int i = 0; i < numberOfBooksInLibrary; i++) {
			Book currentBook = m_Books.get(i);
			Author currentBookAuthor = currentBook.GetAuthor();

			// Match Author
			boolean isAuthorMatch = currentBookAuthor.IsEqual(i_Author);

			// Check for match
			if (isAuthorMatch) 
			{
				booksToReturn.add(currentBook);
				isAtLeastOneBookFound = true;
			}
		}

		if (!isAtLeastOneBookFound)
		{
			JOptionPane.showMessageDialog(null, "Not found!");
		}

		return booksToReturn;
	}

	public static ArrayList<Book> SearchBookByCategory(String i_Category)
	{
		// Calculate number of books in library
		int numberOfBooksInLibrary = GetNumberOfBooksInLibrary();

		// There can be more then one book in category
		ArrayList<Book> booksToReturn = new ArrayList<Book>();

		// Assuming no book will be found. If at least one is, it will be updated to
		// true
		boolean isAtLeastOneBookFound = false;

		for (int i = 0; i < numberOfBooksInLibrary; i++) 
		{
			Book currentBook = m_Books.get(i);

			// Match category
			boolean isCategoryMatch = currentBook.GetCategory().intern() == i_Category.intern();

			// Check for match
			if (isCategoryMatch) 
			{
				booksToReturn.add(currentBook);
				isAtLeastOneBookFound = true;
			}
		}

		if (!isAtLeastOneBookFound) 
		{
			JOptionPane.showMessageDialog(null, "Not found!");
		}

		return booksToReturn;
	}

	public static int NumberOfBookCopies(Book i_Book) 
	{
		// Counter of copies to return
		int counterNumberOfCopies = 0;

		// Number of books in library
		int numberOfBooksInLibrary = GetNumberOfBooksInLibrary();

		for (int i = 0; i < numberOfBooksInLibrary; i++)
		{
			/* If title and author match, it's the same copy */
			Book currentBook = m_Books.get(i);

			// Match title
			boolean isTitleMatch = i_Book.GetTitle() == currentBook.GetTitle();

			// Match Author
			boolean isAuthorMatch = i_Book.GetAuthor().IsEqual(currentBook.GetAuthor());

			// Check match of title and author
			if (isTitleMatch && isAuthorMatch) {
				counterNumberOfCopies++;
			}
		}

		return counterNumberOfCopies;
	}

	// Getters
	public static ArrayList<Book> GetBooks() throws NumberFormatException, IOException {
		UpdateBookList();

		return m_Books;
	}

	public static ArrayList<Student> GetStudents() throws NumberFormatException, IOException {
		UpdateStudentList();

		return m_Students;
	}

	public static int GetNumberOfBooksInLibrary() {
		return m_Books.size();
	}

	public static int GetNumberOfStudentsInLibrary() {
		return m_Students.size();
	}

	// Setters
	public static void SetBooks(ArrayList<Book> i_Books) {
		int numberOfBooks = i_Books.size();

		for (int i = 0; i < numberOfBooks; i++) {
			addBookToBookList(i_Books.get(i));
		}
	}

	public static void SetStudents(ArrayList<Student> i_Students) {
		int numberOfStudents = i_Students.size();

		for (int i = 0; i < numberOfStudents; i++) {
			addStudentToStudentList(i_Students.get(i));
		}
	}
}