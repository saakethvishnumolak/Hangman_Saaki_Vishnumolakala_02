import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Hangman 
{
	int wins, losses;
	String currentWord;
	
	Dictionary dictionary;
	
	public Hangman()
	{
		dictionary = new Dictionary("words.txt");
		loadWL();

	}
	
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
