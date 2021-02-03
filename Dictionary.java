/**
 * This class creates a Dictionary by taking a file of 200 randomly selected words,
 * reads the file, and stores the words in an array.
 * 
 * @author saaki
 * 
 */

import java.security.SecureRandom;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Dictionary 
{
	int currentCard;
	String [] wordList = new String[200];
	SecureRandom randomNumbers = new SecureRandom();
	
	/**
	 * Constructor Dictionary calls readFile method
	 * 
	 * @param fileName (Name of file that contains wordList)
	 */
	public Dictionary(String fileName)
	{
		readFile(fileName);
	}
	
	/**
	 * Reads the file and creates an array list of words
	 * 
	 * @param fileName Name of file that contains wordList
	 */
	public void readFile(String fileName)
	{
		try
		{
		File myFile = new File(fileName);
		Scanner scan = new Scanner(myFile);
		
		int i = 0;
		while(scan.hasNextLine())
		{
			String list = scan.nextLine();
			wordList[i] = list;
			i++;
		}
		scan.close();
		}
		catch (FileNotFoundException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
	
	/**
	 * Chooses a random word from the array and returns it
	 * 
	 * @return random word chosen from wordList
	 */
	public String chooseWord()
	{
		int random = randomNumbers.nextInt(wordList.length);
		return wordList[random];
	}
	
}
