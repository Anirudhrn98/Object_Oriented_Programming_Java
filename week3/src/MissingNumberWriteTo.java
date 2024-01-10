/*
 * extract #bits from file name
 * open file
 * write but exclude one number
 * deterimine which number is missing
 */

import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class MissingNumberWriteTo {
    static int soManyBits;

    static String fileName;
    static Scanner input;
    static int nextNumberFromTheStack = 0;

    public static void parseArgs(String[] args) {
        for (int index = 0; index < args.length; index += 2) {
            if (args[index].equals("-n")) {
                soManyBits = Integer.parseInt(args[index + 1]);
            } else {
                System.out.println("Unrecognized argument -" + args[index] + "=");
                System.exit(1);
            }
        }
    }

    public static void createNumbers() {
        long maximumNumber = (long) Math.pow(2, soManyBits);
        long thisNumberIsMissing = (long) new Random().nextInt((int) Math.pow(2, soManyBits));
        thisNumberIsMissing = (thisNumberIsMissing == 0) ? 1 : thisNumberIsMissing;

        try {
            FileWriter myFile = new FileWriter(soManyBits + "_bits.txt");
            for (int index = 1; index < maximumNumber; index++) {
                if (index != thisNumberIsMissing) {
                    myFile.write(index + "\n");
                    System.out.println(index);
                } else {
                    System.out.println("MISSING NUMBER: " + index);
                }
            }
            myFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        parseArgs(args);
        createNumbers();
    }
}