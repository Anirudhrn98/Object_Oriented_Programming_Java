/*
 * GuessWord.java
 *
 * Version:
 *      $Id$
 *
 * Revision:
 *      $Log$
 *
 */

/*
 * This is a part of the implementation for the guessing game implemented for HW4.1
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

import java.util.Scanner;

/**
 * Helper class for implementing Game.java
 */
public class GuessWord {
    protected String playersWord;
    protected String opponentsGuesses;
    protected int guessedLetters;

    /**
     * Class GuessWord constructor
     */
    protected GuessWord(String playersWord) {
        this.playersWord = playersWord.toLowerCase();
        this.opponentsGuesses = "_";
    }

    /**
     * Promps the user for the next guess, and double checks that it is in the right format
     * for the program to be able to process it further
     */
    protected String nextGuess() {
        System.out.print("Enter your Guess: ");
        Scanner nextGuess = new Scanner(System.in);
        String strNextGuess = nextGuess.nextLine().toLowerCase();
        // If the player enters an invalid input then break and ask for another input and convert any
        // to lower case.
        while (strNextGuess.length() != 1) {
            System.out.print("Please enter only one letter: ");
            nextGuess = new Scanner(System.in);
            strNextGuess = nextGuess.nextLine().toLowerCase();
        }
        return strNextGuess;
    }

    /**
     * Checks if the letter guessed is present in the word and updates the word guesses based on it.
     *
     * @param nextGuess the letter guessed by the player in that turn.
     */
    protected String updateGuesses(String nextGuess, String opponentsWord, String foundOpponentsLetter) {
        int foundLetter = -1;
        char[] newOpponentsGuessWord = foundOpponentsLetter.toCharArray();

        if ((opponentsWord.contains(nextGuess)) && !(foundOpponentsLetter.contains(nextGuess))) {
            do {
                foundLetter = opponentsWord.indexOf(nextGuess, foundLetter + 1);
                if (foundLetter >= 0) {
                    newOpponentsGuessWord[foundLetter] = nextGuess.charAt(0);
                    guessedLetters++;
                }
                foundOpponentsLetter = String.valueOf(newOpponentsGuessWord);
            } while (foundLetter >= 0);
            return foundOpponentsLetter;
        } else {
            return "";
        }
    }
}
