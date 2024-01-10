/*
 * Game.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/*
 * This program runs a 2 player game where each person has to guess the letters in
 * the word given by the opponent and the first person to guess all the letters wins
 *
 * @author Anirudh Narayanan
 * @author Bardh Rushiti
 */
public class Game {
    private final int numPlayer = 2;
    private Player[] players = new Player[numPlayer];
    private Picture[] pictures = new Picture[numPlayer];
    private GuessWord[] guessWords = new GuessWord[numPlayer];

    /**
     * Game Constructor
     *
     * @param args command line arguments
     */
    protected Game(String[] args) {
        // How to make sure these are actually string
        // Take the arguments and assign values for player 1 and 2
        // Creates strings containing player name, letters guessed, game word to guess,
        // counter to keep track of number of letters guessed, scanner to take text file
        // as input and vectors to store scanner file as vectors for both players.
        for (int index = 0; index < args.length; index += 2) {
            if (args[index].endsWith("-me")) {
                players[0] = new Player(args[index + 1]);
            } else if (args[index].endsWith("-meWords")) {
                guessWords[0] = new GuessWord(args[index + 1]);
            } else if (args[index].endsWith("-mePicture")) {
                pictures[0] = new Picture(args[index + 1]);
            } else if (args[index].endsWith("-you")) {
                players[1] = new Player(args[index + 1]);
            } else if (args[index].endsWith("-youWords")) {
                guessWords[1] = new GuessWord(args[index + 1]);
            } else if (args[index].endsWith("-youPicture")) {
                pictures[1] = new Picture(args[index + 1]);
            }
        }
    }

    /**
     * Game logic, where the players assume a letter (in each round) for opponents game word.
     */
    protected void run() {
        int playersGuessWord;
        int opponentsGuessWord;

        do {
            System.out.print("\n" + players[Player.playerId].playerName + " is playing now! ");
            System.out.println("Word guessed till now: " + guessWords[1 - Player.playerId].opponentsGuesses);

            String strNextGuess = guessWords[Player.playerId].nextGuess();
            String updatedGuess = guessWords[Player.playerId].updateGuesses(
                    strNextGuess,
                    guessWords[1 - Player.playerId].playersWord,
                    guessWords[1 - Player.playerId].opponentsGuesses);
            if (!updatedGuess.isEmpty()) {
                guessWords[1 - Player.playerId].opponentsGuesses = updatedGuess;
            }

            System.out.println("Word guessed till now: " + guessWords[1 - Player.playerId].opponentsGuesses);
            double perGuess = (double) guessWords[Player.playerId].guessedLetters / guessWords[1 - Player.playerId].opponentsGuesses.length();

            pictures[1 - Player.playerId].showPicture(perGuess);
            Player.switchPlayer();

            playersGuessWord = guessWords[1 - Player.playerId].guessedLetters;
            opponentsGuessWord = guessWords[Player.playerId].playersWord.length();
        } while (playersGuessWord != opponentsGuessWord);
        System.out.println("\n\nCongrats " + players[1 - Player.playerId].playerName + "! You have won the game :) ");
    }

    public static void main(String[] args) {
        Game newGame = new Game(args);
        newGame.run();
    }
}
