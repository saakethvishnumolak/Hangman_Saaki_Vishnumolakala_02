import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Simple hangman game. It reads a word from a file, chooses one at random, 
 * and then the player can guess letters one at a time until they get the 
 * word right or run out of guesses. 
 * 
 * @author saaki
 *
 */

public class Hangman 
{
	int wins, losses;
	String currentWord;
	
	Dictionary dictionary;
	
	/**
	 * Object constructor
	 * Instantiates dictionary object and retrieves past win-loss record
	 * 
	 */
	public Hangman()
	{
		dictionary = new Dictionary("words.txt");
		loadWL();

	}
	
	/**
	 * Reads the wins and losses record
	 */
	public void loadWL()
	{
		File myFile = new File("winsAndLosses.txt");
		Scanner scan;
		try {
			scan = new Scanner(myFile);
			
			String record = scan.nextLine();
			String[] myArray = record.split(",");
			wins = Integer.parseInt(myArray[0]);
			losses = Integer.parseInt(myArray[1]);
			
			scan.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Records the total win loss record in a file
	 */
	public void writeWL()
	{
		try 
		{
			FileWriter myFile = new FileWriter("winsAndLosses.txt");
			myFile.write(wins + "," + losses);
			myFile.close();
		} 
		catch (IOException e) 
		{	
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * First asks the user if they would like to play the game. If they choose y 
	 * then the game chooses a random word and the user is ready to guess. If the 
	 * user chooses a letter that is not in the word then their guesses decrease
	 * by 1. The user has 5 incorrect guesses every round. If they have 0 guesses 
	 * left then the game ends with a loss. You get another option to play again 
	 * despite a win or loss. If you choose n and don't want to continue, the 
	 * program ends and returns your win-loss record for the round and then the 
	 * total for all the rounds. 
	 * 
	 */
	public void playGame()
	{
		int w = 0, l = 0;
		
		Scanner scan = new Scanner(System.in);		
		
		while(true)
		{
			System.out.println("Would you like to play a game y/n");
			String start = scan.nextLine();
		
			if(start.equals("y"))
			{
				currentWord = dictionary.chooseWord();
				StringBuilder guessWord = new StringBuilder();
				
				for(int j = 0; j < currentWord.length(); j++)
				{
					guessWord.append("_");
				
				}
			
				int tries = 5;
				while(true)
				{
					System.out.println("You have " + tries + " incorrect guess left.\n");
					System.out.println(guessWord.toString());

					System.out.println("What is your guess?");
					char guess = scan.nextLine().charAt(0);
				
					if(currentWord.indexOf(guess) != -1)
					{
						for(int k = 0; k < currentWord.length();k++)
						{
							if(currentWord.charAt(k) == guess)
							{
								guessWord.setCharAt(k, guess);
							}	
						}
						if(currentWord == guessWord.toString())
						{
							System.out.println("You Won!");
							w++;
							break;
						}
					}
					else
					{
						tries--;
					}

					if(tries == 0)
					{
						System.out.println("You are out of guesses. You Lost!");
						l++;
						break;
					}
				}
			
			}
		
		
		
			else  
			{
				System.out.println("You had " + w + " wins and " + l + " losses this round.\n");
				
				wins += w;
				losses += l;
				
				System.out.println("You have a total of " + wins + " and " + losses + ".");
				writeWL();
				
				break;
			}
		}
	}
}
