/*
 * Picture.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * This program runs a 2 player game where each person has to guess the letters in
 * the word given by the opponent and the first person to guess all the letters wins
 *
 * @author Anirudh Narayanan
 * @author Bardh Rushiti
 */

import java.util.*;
import java.io.File;
import java.util.Vector;
import java.io.FileNotFoundException;

public class Picture {

    static int playerId = 0;

    static Scanner[] scs = new Scanner[2];
    static File[] files = new File[2];
    static int[] counters = new int[2];
    static String[] gameWords = new String[2];
    static String[] guessedGameWords = new String[2];
    static String[] playerStr = new String[2];
    static Vector<Vector<String>> vecScanners = new Vector<Vector<String>>(2);

    /**
     * Initializes the game and uses args to assign all the base values.
     *
     * @param    args   command line arguments
     *
     */
    public static void initGame(String[] args) {
        // How to make sure these are actually string

        // Take the arguments and assign values for player 1 and 2

        // Creates strings containing player name, letters guessed, game word to guess,
        // counter to keep track of number of letters guessed, scanner to take text file
        // as input and vectors to store scanner file as vectors for both players.
        for (int i = 0; i < 2; i++) {
            playerStr[i] = args[0 + i * 3];
            files[i] = new File(args[1 + i * 3]);
            try {
                scs[i] = new Scanner(files[i]);
            } catch (FileNotFoundException e) {
            }
            vecScanners.add(readScannerFile(scs[i]));
            gameWords[i] = args[2 + i * 3].toLowerCase();
            counters[i] = 0;

            guessedGameWords[i] = "_".repeat(gameWords[i].length());
        }
    }

    /**
     * Reads the scanner file as a vector and returns a vector.
     *
     * @param       sc          scanner file containing picture as text
     *
     * @return vector      Vector containing the scanner file
     */

    public static Vector<String> readScannerFile(Scanner sc) {
        String asd = sc.nextLine();
        Vector<String> vecScannerFile = new Vector<String>(asd.length());
        // while the scanner has a next line it will add the line to the string which
        // will then be added to the vector.
        while (sc.hasNextLine()) {
            asd = sc.nextLine();
            vecScannerFile.add(asd);
        }
        // returns the final vector containing the input text file
        return vecScannerFile;
    }

    /**
     * Prints all the smaller prime numbers than i
     *
     * @param    vec            Vector containing the image corresspoding to the word
     * @param    percGuess      Stores the percentage according to number of letters
     *                          guessed from the word.
     */
    public static void updatePicture(Vector<String> vec, double percGuess) {
        Vector<String> a = new Vector<String>(vec.get(0).length());
        // Create a string 'a' with '.' of size of the vector and used that to print out the
        // updated image based on the amount correct guesses.
        if (percGuess < 1) {
            for (int i = 0; i < vec.size(); i++) {
                String asd = vec.get(i);
                a.add(".".repeat(asd.length()));
                // Depending on the amount of letters guessed calculate total percentage of the word
                // guessed and print out the image and clears the vector containing the
                // image which was printed.
                // Split the string and use random indexes to randomize the output of the image.
                for (int j = 0; j < (int) (percGuess * asd.length()); j++) {
                    int index = (int) (Math.random() * (asd.length()));
                    String[] aasd = a.get(0).split("");
                    aasd[index] = asd.split("")[index];
                    a.set(0, String.join("", aasd));
                }
                System.out.println(a);
                a.clear();
            }

        } else {
            for (int j = 0; j < vec.size(); j++) {
                System.out.println(vec.get(j));
            }
        }
    }

    /**
     * Method is used toggle between player after each turn.
     *
     * @param
     */
    public static void switchPlayer() {

        playerId = 1 - playerId;
    }


    /**
     * Checks if the letter guessed is present in the word and updates the word guesses based on it.
     *
     * @param       nextGuess       the alphabet guessed by the player in that turn.
     */

    public static void updateGuesses(String nextGuess) {
        int foundLetter = -1;

        char[] newGuessWord = guessedGameWords[1 - playerId].toCharArray();
        // If the guessed letter is present in the word to be guessed then update the word guessed till now
        // and increment the counter to store the amount the words guessed till now.
        if ((gameWords[1 - playerId].contains(nextGuess)) && !(guessedGameWords[1 - playerId].contains(nextGuess))) {
            do {
                foundLetter = gameWords[1 - playerId].indexOf(nextGuess, foundLetter + 1);
                if (foundLetter >= 0) {
                    newGuessWord[foundLetter] = nextGuess.charAt(0);
                    counters[playerId]++;
                }
                guessedGameWords[1 - playerId] = String.valueOf(newGuessWord);

            } while (foundLetter >= 0);
        }
    }


    /**
     * Checks if the letter guessed is present in the word and updates the word guesses based on it.
     *
     * @param
     */
    public static void startGame() {
        // break when the player has guessed all the letters
        while (counters[1 - playerId] != gameWords[playerId].length()) {
            System.out.println(playerStr[playerId] + " is playing now!");

            System.out.println("Word guessed till now: " + guessedGameWords[1 - playerId]);

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
            //updates the word guessed till now and prints corresponding picture. switches the player after each guess.
            updateGuesses(strNextGuess);
            System.out.println("Word guessed till now: " + guessedGameWords[1 - playerId]);
            System.out.println(counters[playerId]);

            double perGuess = (double) counters[playerId] / gameWords[1 - playerId].length();
            updatePicture(vecScanners.get(1 - playerId), perGuess);

            switchPlayer();
        }
        System.out.println("Game over! " + playerStr[playerId] + " won this game!");
    }


    /**
     * The main program.
     *
     * @param    args    command line arguments (commented)
     */
    public static void main(String[] args) {
        initGame(args);

        startGame();
    }
}