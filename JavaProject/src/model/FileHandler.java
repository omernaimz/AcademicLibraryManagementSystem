package model;
import java.io.*;

import javax.swing.JOptionPane;

public class FileHandler {
	public static boolean WriteToFile(String i_FileName, StringBuilder i_String) {
		String newLine = System.lineSeparator();
		FileWriter writer;
		boolean isAppend = true;
		boolean isWriteSuccess = true;
		i_String.append(newLine);

		try {
			writer = new FileWriter(i_FileName, isAppend); // True to append file
			writer.write(i_String.toString());
			writer.close();
		} catch (IOException e) {
			isWriteSuccess = false; // TODO: Do normal exception
		}
		
		return isWriteSuccess;

		/*
		 * BookID, LendingDate, ReturnDate, StudentID 344, date, date, 787 123, date,
		 * date, 656 456, date, date, 567 344, date, NULL, 666 111, date, NULL, 888
		 */
	}

	public static void UpdateLineInFileByID(String i_FileName, String i_ID, StringBuilder i_StringToReplace,
			StringBuilder i_StringToReplaceWith) {
		// TODO
		// For sausege eyes only: Find last appearance line of i_ID, replace
		// i_StringToReplace with i_StringToReplaceWith
	}

	public static int FindAppearanceCountByID(String i_FileName, String i_ID) throws IOException {
		String sCurrentLine;
		int appearanceCounter = 0;
		BufferedReader br = new BufferedReader(new FileReader(i_FileName));

		while ((sCurrentLine = br.readLine()) != null) {
			String[] lineValues = sCurrentLine.split(","); // Split the string on commas into array
			String currentID;
			boolean isMatchID;

			currentID = lineValues[0].substring(0, lineValues[0].length() - 1);
			isMatchID = (currentID == i_ID);

			if (isMatchID) {
				appearanceCounter++;
			}
		}

		return appearanceCounter;
	}

	public static String[] FindLastAppearanceLineByID(String i_FileName, String i_ID) throws IOException {
		String sCurrentLine;
		BufferedReader br = new BufferedReader(new FileReader(i_FileName));

		// This will store the last appearance and will be returned
		String[] currentLine = { "" };

		while ((sCurrentLine = br.readLine()) != null) {
			String[] lineValues = sCurrentLine.split(","); // Split the string on commas into array
			String currentID;
			boolean isMatchID;

			currentID = lineValues[0].substring(0, lineValues[0].length() - 1);
			isMatchID = (currentID == i_ID);

			if (isMatchID) {
				currentLine = lineValues;
			}
		}

		return currentLine;
	}

	public static void RemoveLineFromFile(String i_FileName, String i_ID) throws IOException {
		File inputFile = new File(i_FileName);
		File tempFile = new File("Temp.txt");

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		// Remove line starts with this ID
		String iDidentifierLineToRemove = i_ID;
		String currentLine;

		boolean successfullyRemoved;
		String textToPopUp;

		while ((currentLine = reader.readLine()) != null) {
			/* Add all lines to temporary files, except line we want to remove */
			// Trim newline when comparing with lineToRemove
			String trimmedLine = currentLine.trim();

			// Match between ID's
			boolean isIDMatch = trimmedLine.substring(0, 9).equals(iDidentifierLineToRemove);

			// Check match between ID's
			if (isIDMatch)
				continue;

			// Add correct line to temporary file
			writer.write(currentLine + System.getProperty("line.separator"));
		}

		writer.close();
		reader.close();
		successfullyRemoved = tempFile.renameTo(inputFile);

		if (successfullyRemoved) {
			textToPopUp = "Success!";
		} else {
			textToPopUp = "Failed!";
		}

		// Inform user if success or fail
		JOptionPane.showMessageDialog(null, textToPopUp);
	}
	
	public static int counStrAppearenceForID(String i_FileName, String i_ID, String i_StrToFind) throws IOException
	{
		String sCurrentLine;
		int appearanceCounter = 0;
		BufferedReader br = new BufferedReader(new FileReader(i_FileName));

		while ((sCurrentLine = br.readLine()) != null) {
			String[] lineValues = sCurrentLine.split(","); // Split the string on commas into array
			String currentID;
			boolean isMatchID;

			// Match ID
			currentID = lineValues[0].substring(0, lineValues[0].length() - 1);
			isMatchID = (currentID == i_ID);
			
			// Match NULL
			boolean isNullAppears = sCurrentLine.contains(i_StrToFind);

			// Check for match of ID and NULL
			if (isMatchID && isNullAppears) {
				appearanceCounter++;
			}
		}

		return appearanceCounter;
	}
	
	public static String FindRowByID(String i_FileName, String i_ID) throws IOException
	{
		String sCurrentLine;
		BufferedReader br = new BufferedReader(new FileReader(i_FileName));
		
		while ((sCurrentLine = br.readLine()) != null)
		{
			String[] lineValues = sCurrentLine.split(",");
			String currentID;
			boolean isMatchID;
			
			// Match ID
			currentID = lineValues[0].substring(0, lineValues[0].length() - 1);
			isMatchID = (currentID == i_ID);
			
			if (isMatchID)
			{
				return sCurrentLine;
			}
		}
		
		JOptionPane.showMessageDialog(null, "Not found!");
		
		return "";
	}

	
}
