/**
 * 
 * @author saaki
 *
 * The Application Class is the main entry point to play the hangman game.
 * New Hangman object is created.
 * 
 */
public class Application 
{
	public static void main(String[] args) 
	{
		Hangman hangman = new Hangman();
		hangman.playGame();
	}

}
