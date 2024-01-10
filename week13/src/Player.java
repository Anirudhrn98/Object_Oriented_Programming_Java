/*
 * Player.java
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

/**
 * Helper class for implementing Picture.java
 */
public class Player {
    protected String playerName = "0";
    protected static int playerId = 0;

    /**
     * Class Player constructor
     */
    public Player() {
        this.playerName = Integer.parseInt(this.playerName) + 1 + "";
    }

    /**
     * Method is used toggle between player after each turn.
     */
    public static void switchPlayer() {
        playerId = 1 - playerId;
    }

    static class GuessWord {
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

    /**
     * Helper class for implementing Picture.java
     */
    static class Picture {
        String filePath;
        Vector<String> vecPicture = new Vector<>();

        /**
         * Class Picture constructor
         */
        public Picture(String filePath) {
            this.filePath = filePath;
        }

        /**
         * Reads the scanner file as a vector and returns a vector.
         *
         * @param sc scanner file containing picture as text
         * @return vecScannerFile  Vector containing the scanner file
         */
        public Vector<String> readScannerFile(Scanner sc) { // Client when recieving data
            String asd = sc.nextLine();
            Vector<String> vecScannerFile = new Vector<String>(asd.length());
            // while the scanner has a next line it will add the line to the string which
            // will then be added to the vector.
            while (sc.hasNextLine()) {
                vecScannerFile.add(sc.nextLine());
            }
            // returns the final vector containing the input text file
            return vecScannerFile;
        }

        /**
         * Prints the picture of the opponents .txt file
         *
         * @param percGuess Stores the percentage according to number of letters
         *                  guessed from the word.
         */
        public void showPicture(double percGuess) {

            if (percGuess < 1) {
                for (int i = 0; i < vecPicture.size(); i++) {
                    // Create a string 'temp' with '.' of size of the vector and used that to print out the
                    // updated image based on the amount correct guesses.
                    String vecRow = vecPicture.get(i);
                    String temp = ".".repeat(vecRow.length());
                    char[] tempChar = temp.toCharArray();
                    int index = 0;

                    // Depending on the amount of letters guessed calculate total percentage of the word
                    // guessed and print out the image and clears the vector containing the
                    // image which was printed.
                    // Split the string and use random indexes to randomize the output of the image.
                    Random r = new Random();
                    for (int j = 0; j < (int) (percGuess * vecRow.length()); j++) {
                        index = r.nextInt(vecRow.length());
                        tempChar[index] = vecRow.toCharArray()[index];
                    }
                    System.out.println(new String(tempChar));
                }
            } else {
                for (int j = 0; j < vecPicture.size(); j++) {
                    System.out.println(vecPicture.get(j));
                }
            }
        }
    }

}
