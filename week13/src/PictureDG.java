/*
 * This program runs a 2 player game where each person has to guess the letters in
 * the word given by the opponent and the first person to guess all the letters wins
 *
 * @author Anirudh Narayanan
 * @author Bardh Rushiti
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PictureDG {
    private final int numPlayer = 2;
    private final Player[] players = new Player[numPlayer];
    private final Player.Picture[] pictures = new Player.Picture[numPlayer];
    private final Player.GuessWord[] guessWords = new Player.GuessWord[numPlayer];
    String host;
    int port;

    /**
     * Picture (Game) Constructor
     */
    protected PictureDG() {
    }

    /**
     * Sends filePath to server and `invites` server to send the fileContents (line by line).
     */
    public void receivePicVector() {
        try {
            DatagramSocket ds = new DatagramSocket();
            InetAddress ip = InetAddress.getLocalHost();
            byte[] data;
            for (int pictureId = 0; pictureId < players.length; pictureId++) {
                data = pictures[pictureId].filePath.getBytes();
                DatagramPacket dpSend = new DatagramPacket(data, data.length, ip, port);
                ds.send(dpSend);
                while (true) {
                    byte[] receive = new byte[65535];              // Clear the buffer after every message.
                    DatagramPacket dpReceive = new DatagramPacket(receive, receive.length);
                    ds.receive(dpReceive);
                    String line = new String(dpReceive.getData(), dpReceive.getOffset(), dpReceive.getLength());
                    pictures[pictureId].vecPicture.add(line);
                    if (line.contains("<EOF>")) // End Of File
                        break;
                }
            }
            data = "<EOT>".getBytes(); // End Of Transfer
            DatagramPacket dpSend = new DatagramPacket(data, data.length, ip, port);
            ds.send(dpSend);
            ds.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Game logic, where the players assume a letter (in each round) for opponents game word.
     */
    protected void run() {
        int playersGuessWord;
        int opponentsGuessWord;

        do {
            System.out.print("\nPlayer " + players[Player.playerId].playerName + " is playing now! ");
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

    /**
     * Checks the arguments from the user to make sure they are in the form
     * that the program can further process them adequately
     */
    private void parseArgs(String[] args) {
        if (args.length != 10) {
            System.err.println("The Picture.java needs to have five (5) arguments!");
            System.err.println("java Picture -me <mePicture> <meWord> " +
                    "-you <youWord> <youPicture> " +
                    "-server <server> -port <port>");
            System.exit(-1);
        }

        for (int index = 0; index < args.length; index += 3) {
            if (args[index].endsWith("-me")) {
                players[0] = new Player();
                pictures[0] = new Player.Picture(args[index + 1]);     // TODO: Is this the right way to initialize the inner class in Player
                guessWords[0] = new Player.GuessWord(args[index + 2]); // TODO: Is this the right way to initialize the inner class in Player
                guessWords[0].opponentsGuesses = "_".repeat(guessWords[0].playersWord.length());
            } else if (args[index].endsWith("-you")) {
                players[1] = new Player();
                pictures[1] = new Player.Picture(args[index + 1]);      // TODO: Is this the right way to initialize the inner class in Player
                guessWords[1] = new Player.GuessWord(args[index + 2]);  // TODO: Is this the right way to initialize the inner class in Player
                guessWords[1].opponentsGuesses = "_".repeat(guessWords[1].playersWord.length());

            }
        }

        host = args[7];
        port = Integer.parseInt(args[9]);
    }

    /**
     * Main function
     */
    public static void main(String[] args) {
        PictureDG newGame = new PictureDG();
        newGame.parseArgs(args);
        newGame.receivePicVector();
        newGame.run();
    }
}
