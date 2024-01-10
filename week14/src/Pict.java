/**
 * This class implements a Picture Game.
 */

import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.util.Random;


public class Pict {
    final static String DOT = ".";
    final static Random randomNumberGenerator = new Random();

    final static int ME = 0;
    final static int YOU = 1;
    final static String[] thePlayers = {"First", "Second"};
    final static Vector[] thePictures = new Vector[2];
    static String[][] yourWords = new String[2][2];
    static int[] correctGuessedInPrecentage = new int[2];

    private static void printThePicture(int id) {
        Vector<String> thePicture = thePictures[id];
        for (int index = 0; index < thePictures[id].size(); index++) {
            System.out.print("       ");
            for (int xOuter = 0; xOuter < thePicture.elementAt(index).length(); xOuter++) {
                if (randomNumberGenerator.nextInt(101) <= correctGuessedInPrecentage[id])
                    System.out.print(thePicture.elementAt(index).charAt(xOuter));
                else
                    System.out.print(DOT);
            }
            System.out.println();
        }
    }

    private static void fillThePicture(String fileName, int id) {
        thePictures[id] = new Vector();
        try {
            Scanner aScanner = new Scanner(new File(fileName));
            while (aScanner.hasNextLine()) {
                thePictures[id].add(aScanner.nextLine());
            }
        } catch (Exception e) {
        }
    }

    private static void parseArgs(String[] args) {
        for (int index = 0; index < args.length; index += 3) {
            int id = (args[index].equals("-me") ? ME : YOU);
            yourWords[id][0] = args[index + 2].replace(".txt", "");
            fillThePicture(args[index + 1], id);
        }
    }

    private static void calculateCorrecntessAndPrint(int id) {
        int soManyGuessed = yourWords[id][1].length();
        String tmpString = yourWords[id][1];
        for (int position = 0; position < yourWords[id][1].length(); position++) {
            if (("" + yourWords[id][1].charAt(position)).equals(DOT))
                soManyGuessed--;
        }
        correctGuessedInPrecentage[id] = (int) (100.0 *
                ((double) soManyGuessed / (double) yourWords[id][1].length()));
    }

    private static boolean guess(Scanner input, int id) {
        String theGuess;
        boolean rValue = false;
        int position = 0;
        System.out.print(thePlayers[id] + " your turn (" + yourWords[id][1] + "): ");
        if (input.hasNext()) {
            theGuess = input.next();
            if (rValue = ((position = yourWords[id][0].indexOf(theGuess)) >= 0)) {
                yourWords[id][1] = yourWords[id][1].substring(0, position) +
                        theGuess +
                        yourWords[id][1].substring(position + 1);
                System.out.println("	You guess was correct: " + yourWords[id][1]);
                calculateCorrecntessAndPrint(id);
                printThePicture(id);
            }
        }
        return rValue;
    }

    private static void initWords() {
        for (int id = ME; id <= YOU; id++) {
            yourWords[id][1] = yourWords[id][0].replaceAll(".", DOT);
        }
    }

    private static void playTheGame() {
        Scanner userGuessInput = new Scanner(System.in);
        boolean oneIsDone = false;
        initWords();
        do {
            for (int id = ME; id <= YOU; id++) {
                guess(userGuessInput, id);
                oneIsDone |= (yourWords[id][1].indexOf(DOT) < 0);
            }

        } while (!oneIsDone);
        for (int id = ME; id <= YOU; id++) {
            if (yourWords[id][1].indexOf(DOT) < 0) {
                System.out.println("This word guessed correctly was: " + yourWords[id][0]);
            }
        }
        userGuessInput.close();
    }

    private static void startTheGame(String[] args) {
        parseArgs(args);
        playTheGame();
    }

    public static void main(String[] args) {
        startTheGame(args);
    }
}
