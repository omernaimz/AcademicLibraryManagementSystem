package model;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Error {
	// Fields
	private static int m_ID; // Check if needed to implicitly initialize
	private String m_Date;
	private String m_Description;

	// Constructor
	public Error(String i_Description) {
		SetID();
		SetDate();
		SetDescription(i_Description);
	}

	// Methods
	public void NewError() // Default throw in i/o handling
	{
		// Local variables
		StringBuilder textToAppend = new StringBuilder();
		String errorLogFileName = "Error_log.txt";
		String newLine = System.lineSeparator();

		// Build the error text
		textToAppend.append(m_Date);
		textToAppend.append(newLine);
		textToAppend.append(m_Description);
		textToAppend.append(newLine);
		textToAppend.append("-----------------");
		textToAppend.append(newLine);

		// Write error to log file
		FileHandler.WriteToFile(errorLogFileName, textToAppend);
	}

	public int GetID() {
		return Error.m_ID;
	}

	public String GetDate() {
		return m_Date;
	}

	public String GetDescription() {
		return m_Description;
	}

	public void SetID() {
		Error.m_ID++;
	}

	public void SetDate() {
		m_Date = new SimpleDateFormat("dd-M-yyyy hh:mm:ss").format(Calendar.getInstance().getTime());
	}

	public void SetDescription(String i_Description) {
		m_Description = i_Description;
	}
}
