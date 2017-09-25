/**
 * This is a game where the user guesses at a number from 1 to 10
 * After each guess the results are printed to the console
 * The user will be notified whether their guess was correct, too high, too low, or not valid
 * The game continues to run until the user correctly guesses the number
 * Once the user enters the correct number, game is won and the program stops
 * @author Team 6 - CPSC 233 - Tutorial T02
 * @version 1.0
 * @since September 25, 2017
 */
import java.util.Scanner;

public class GuessingGameMain
{
	/**
	 * This is the main method that runs the GuessingGame class
	 * @param args Unused
	 */
	public static void main(String[] args)
	{
		GuessingGame game = new GuessingGame();
		Scanner keyboard = new Scanner(System.in);
		String guessAsString;
		int guess = 0;
		int numberToGuess = game.randomNum();
		
		do 
		{
			System.out.print("Enter your guess(1 to 10): ");
			guessAsString = keyboard.next();
			if (game.allDigits(guessAsString))
			{
				guess = Integer.parseInt(guessAsString);
				if (game.guessValid(guess))
				{
					game.compareGuessToAnswer(guess, numberToGuess);	
				}
				else
				{
					System.out.println("Not valid\n");
				}
			}
			else
			{
				System.out.println("Not valid\n");
			}
		}
		while (!game.guessCorrect(guess, numberToGuess));
		keyboard.close();
	}
}