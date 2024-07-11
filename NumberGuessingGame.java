import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {
        Random random = new Random();
        int secretNumber = random.nextInt(100) + 1;
        int attempts = 0;
        int max = 6;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Try to guess a number generated in between of 1 and 100.");
        System.out.println("You have a total of  " + max + " attempts.");

        Scanner k = new Scanner(System.in);

        while (attempts < max) {
            System.out.print("Enter your guess: ");
            int userGuess = k.nextInt();
            attempts++;

            if (userGuess == secretNumber) {
                System.out.println("HURRAY! You correctly guessed  the number!");
                score++;
                break;
            } else if (userGuess > secretNumber) {
                System.out.println("SORRY! Your guess is greater than the number. Try again.");
            } else {
                System.out.println("SORRY! Your guess is lower than the number. Try again");
            }
        }

        System.out.println("Your total score: " + score + " out of " + max);

        System.out.print("Would you like to play again? (y/n): ");
        String playAgain = k.next();
        if (playAgain.equalsIgnoreCase("y")) {
            playGame();
        } else {
            System.out.println("Thank you for playing! ");
        }
    }
}
