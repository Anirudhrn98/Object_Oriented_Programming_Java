/*
 * MissingNumberReadFromFile.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/*
 * The program finds out the missing number from the text file and prints out the missing number.
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class MissingNumberReadFromFile {
    static Scanner sc;
    static int bitLength;

    /**
     * Converts the integer value passed into bit representation and returns bit value
     *
     * @param a Integer value to be converted to bit form
     * @return rValue     String containing bit representation of the
     * integer value
     */
    public static String printBitRepresentation(int a) {
        String rValue = "";
        // checks if condition is true or false and adds one if true and zero if false
        // to rValue which returns as the binary representation.
        for (int index = bitLength - 1; index >= 0; index--) {
            if (((a) & (1 << index)) == (1 << index))
                rValue += "1";
            else
                rValue += "0";
        }
        return rValue;
    }

    /**
     * Initializes the game and uses args to assign all the base values.
     *
     * @param binaryRepresentationOfNum String containing the binary representation of the integer
     * @return rValue                      Decimal form of the missing number
     */

    public static int findMissingNumber(String[] binaryRepresentationOfNum) {
        //Initialize integer array to store sum of zeroes and ones of each bit row.
        int zeroOneCounter[][] = new int[bitLength][2];
        // Adds all the zeroes and ones present from the input text file and increments
        // in the zeroOneCounter accordingly.
        for (int col = 0; col < bitLength; col++) {
            for (int row = 0; row < binaryRepresentationOfNum.length; row++) {
                if (binaryRepresentationOfNum[row].charAt(col) == '0') {
                    zeroOneCounter[col][0] += 1;
                } else {
                    zeroOneCounter[col][1] += 1;
                }
            }
        }
        // use ternary operator to check whether to print zero or one and creates binary
        // form of the missing number
        String rVal = "";
        for (int num = 0; num < bitLength; num++) {
            rVal += zeroOneCounter[num][0] + 1 < zeroOneCounter[num][1] ? 0 : 1;
        }
        // Converts the missing number from binary to decimal
        return Integer.parseInt(rVal, 2);

    }

    /**
     * Initializes the game and uses args to assign all the base values.
     *
     * @param args Command line arguments
     * @return sc          scanner file containing data from text file which has a
     * missing number.
     */
    public static Scanner readFile(String[] args) {
        String fileToRead;
        //find bitLength using the input and store for further use.
        if (args[0].equals("-n")) {
            bitLength = Integer.parseInt(args[1]);
            fileToRead = args[3];
        } else {
            fileToRead = args[1];
            bitLength = Integer.parseInt(args[3]);
        }
        // Create file containing the text file created in MissingNumberWriteTo.java
        File file = new File("C:\\Users\\aniru\\IdeaProjects\\board\\" + fileToRead);
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
        }

        return sc;
    }

    /**
     * The main program
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        readFile(args);
        //Create a string of length 2((power,n-)-2)
        String[] str = new String[(int) Math.pow(2, bitLength) - 2];

        int cntr = 0;
        //While scanner has next line pass current line as integer and increment
        //counter.
        while (sc.hasNextLine()) {
            int temp = Integer.parseInt(sc.nextLine());
            str[cntr] = printBitRepresentation(temp);
            cntr += 1;
        }

        System.out.println("The missing number is: " + findMissingNumber(str));

    }
}