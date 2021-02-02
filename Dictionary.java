import java.security.SecureRandom;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Dictionary 
{
	int currentCard;
	String [] wordList = new String[200];
	SecureRandom randomNumbers = new SecureRandom();
	
	public Dictionary(String fileName)
	{
		readFile(fileName);
	}
	
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
	
	public String chooseWord()
	{
		int random = randomNumbers.nextInt(wordList.length);
		return wordList[random];
	}
	
}
