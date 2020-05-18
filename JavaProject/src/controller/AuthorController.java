package controller;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import model.*;


public class AuthorController
{
	public void AddAuthor(String firstAndLastName)
	{
		String firstName;
		String lastName;
		String[] firstAndLastNameArray = firstAndLastName.toString().split(" ");
		
		firstName = firstAndLastNameArray[0];
		lastName = firstAndLastNameArray[1];
		Author author = new Author(firstName, lastName);
		author.AddAuthorToDataBase();
		
		//author object automatically deleted after leaving method block
	}
	
	public static boolean CheckIfAuthorNameValid(String i_StrFromAuthorNameTB) //TODO fix this shit
	{
		boolean isTextValid = true;
		
		// Remove leading and trailing spaces
		i_StrFromAuthorNameTB.trim();
		
		/* Check if input is correct */
		// Check if name is empty
		boolean isNameEmpty = i_StrFromAuthorNameTB.isEmpty();
		
		// Check if name is all 
		boolean isAllSpaces = isStringAllSpaces(i_StrFromAuthorNameTB);
		
		// Check if name contains anything but letters or spaces
		Pattern patternAllowed = Pattern.compile("^[ A-Za-z]+$");
		Matcher matcher = patternAllowed.matcher(i_StrFromAuthorNameTB);
		boolean isContainsAnythingButLetters = matcher.matches();
	//	boolean isContainsAnythingButLetters = true;
		/*char[] stringNameArray = i_StrFromAuthorNameTB.toCharArray();
	
		for(int i=0 ; i<i_StrFromAuthorNameTB.length() ; i++)
		{
			if(!Character.isLetter(stringNameArray[i]))
			{
				
			}
		}*/
		
		// Check conditions
		if(isNameEmpty || !isContainsAnythingButLetters || isAllSpaces)
		{
			isTextValid = false;
		}
		
		return isTextValid;
	}
	
	public static boolean CheckIfAuthorPhoneValid(String i_StrFromAuthorPhoneTB)
	{
		boolean isTextValidTrue = true;
		i_StrFromAuthorPhoneTB.trim();
		char[] stringArray = i_StrFromAuthorPhoneTB.toCharArray();

		
		// Check if given text is digits only
		for(int i = 0 ; i < stringArray.length ; i++)
		{
			if(!Character.isDigit(stringArray[i]))
			{
				isTextValidTrue = false;
			}
		}
		
		boolean isNameEmpty = i_StrFromAuthorPhoneTB.isEmpty();
		boolean isAllSpaces = isStringAllSpaces(i_StrFromAuthorPhoneTB);
		
		if(isNameEmpty || isAllSpaces)
		{
			isTextValidTrue = false;
		}
		
		return isTextValidTrue;
	}
	
	private static boolean isStringAllSpaces(String i_Str)
	{
		return !(i_Str.trim().length() > 0);
	}
	
	
}
