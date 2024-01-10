/*
 * Picture.java
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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

/**
 * Helper class for implementing Game.java
 */
public class Picture {
    Scanner sc;
    Vector<String> vecPicture;

    /**
     * Class Picture constructor
     */
    public Picture(String filePath) {
        Path path = Paths.get(filePath);
        try {
            sc = new Scanner(path.toAbsolutePath());
            this.vecPicture = readScannerFile(sc);
        } catch (IOException e) {
        }
    }

    /**
     * Reads the scanner file as a vector and returns a vector.
     *
     * @param sc scanner file containing picture as text
     * @return vecScannerFile  Vector containing the scanner file
     */
    public Vector<String> readScannerFile(Scanner sc) {
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
