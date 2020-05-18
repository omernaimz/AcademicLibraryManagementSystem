package model;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Author extends Person {
	private static final String EMPTY_STRING = "";
	private static final String AUTHOR_DB = "AuthorDB.txt"; // TODO: rename to AuthorDB instead of FILE_NAME

	// Fields
	ArrayList<Book> m_BooksWritten = new ArrayList<Book>();
	private int m_AuthorID;
	
	//private static int m_IDGenerator = getLastIdGenerated(); //TODO
	private static int m_IDGenerator = 10000;

	// Constructor	
	public Author()
	{
		super("", "");
	}
	
	public Author(String i_FirstName, String i_LastName) {
		super(i_FirstName, i_LastName);
		m_AuthorID = ++m_IDGenerator;
	}	
	
	
	// Methods
	private static int getLastIdGenerated()
	{
		//TODO
		return 0;
		
	}	
	
	public void AddAuthorToDataBase()
	{
		StringBuilder authorDescription = createDescription(this);
		
		// writing to file
		FileHandler.WriteToFile(AUTHOR_DB, authorDescription);
	}
	
	private static StringBuilder createDescription(Author author)
	{
		
		String newLine = System.lineSeparator();
		StringBuilder description = new StringBuilder();
	//	description.append("---------------------");
		description.append(author.GetFirstName());
		description.append(",");
		description.append(author.GetLastName());
		description.append(",");
		description.append(String.valueOf(author.GetID()));
		description.append(newLine);
		
		return description;
		
		/*
		 * ---------------------
		 * ID, FirstNAme, lastName, 
		 */
	}
	
	// Getters
	public StringBuilder GetBooksWritten() throws NumberFormatException, IOException {
		StringBuilder booksWrittenByAuthor = new StringBuilder();
		String newLine = System.lineSeparator();
		int numberOfBooksInLibrary = Library.GetNumberOfBooksInLibrary();
		
		ArrayList<Book> libraryBooks = Library.GetBooks();

		for (int i = 0; i < numberOfBooksInLibrary; i++) {
			Book libraryBook = libraryBooks.get(i);
			int libraryBookAuthorID = libraryBook.GetAuthor().GetID();

			if (libraryBookAuthorID == m_AuthorID) {
				booksWrittenByAuthor.append(libraryBook.GetTitle());
				booksWrittenByAuthor.append(newLine);
			}
		}

		return booksWrittenByAuthor;
	}

	public int GetID() {
		return m_AuthorID;
	}

	// Setters
	public void SetBooksWritten(ArrayList<Book> i_BooksWritten) {
		m_BooksWritten = i_BooksWritten;
	}

	public boolean IsEqual(Author i_Author) {
		return m_AuthorID == i_Author.GetID();
	}
	
	public void SetID(int i_ID)
	{
		m_AuthorID = i_ID;
	}
}
