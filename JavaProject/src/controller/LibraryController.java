package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Author;
import model.Student;

public class LibraryController 
{
	// Regex for checking input
	private static final String k_LEttersOnlyRegex = "^[A-Za-z]+$";
	private static final String k_DigitsOnlyRegex = "^[0-9]+$";
	
	// No books lent on register
	private static final int k_NoBooksIssued= 0;
	
	// gets StringBuilder type like "firstName, lastName, Phone"
	public static boolean ControllerRegisterStudent(StringBuilder i_StudentInfo)
	{
		try
		{
			// [0] = StudentID, [1] = StudentFirstName, [2] = StudentLastName, [3] = StudentPhoneNumber
			String[] studentInfo = i_StudentInfo.toString().split(",");

			// Split string into variables
			int studentID = Integer.parseInt(studentInfo[0]);
			String firstName = studentInfo[1];
			String lastName = studentInfo[2];
			int phoneNumber = Integer.parseInt(studentInfo[3]);
		
			// Check if register successful
			boolean registeredSuccessfully = false;
			boolean isValidInput;
			
			//TODO check if student already exists in database
			
			
			// Check for valid input
			isValidInput = checkIfNameAndPhoneNumberAreValid(firstName, lastName, phoneNumber);
		
			// Information is valid, registering student to database
			if(isValidInput)
			{
				model.Student newStudent = new model.Student(studentID, firstName, lastName, phoneNumber, k_NoBooksIssued);
				
				// Returns true or false
				registeredSuccessfully = model.Library.RegisterStudent(newStudent);
			}
			
			// Insert student to DB		
			return registeredSuccessfully;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	// Checks if first name, last name and phone number are valid.
	private static boolean checkIfNameAndPhoneNumberAreValid(String i_FirstName, String i_LastName, int i_PhoneNumber)
	{	

		boolean isFirstNameValid = checkInformationByRegex(i_FirstName, k_LEttersOnlyRegex);
		boolean isLastNameValid = checkInformationByRegex(i_LastName, k_LEttersOnlyRegex);
		boolean isPhoneNumberValid = checkInformationByRegex(Integer.toString(i_PhoneNumber), k_DigitsOnlyRegex);
		
		// True if all information is valid
		return (isFirstNameValid && isLastNameValid && isPhoneNumberValid);
	}
	
	// Checks the input by regex - letters only or numbers only
	private static boolean checkInformationByRegex(String i_String, String i_Regex)
	{
		
		Pattern patternAllowed;
		Matcher matcher;
		boolean isInformationValid;
		
		patternAllowed = Pattern.compile(i_Regex);
		matcher = patternAllowed.matcher(i_String);
		isInformationValid = matcher.matches();
		
		return isInformationValid;
	}
}
	
	
