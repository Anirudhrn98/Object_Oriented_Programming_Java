/*
 * ManyQuestions.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/*
 * The program makes the code more optimized.
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

import java.util.Random;

public class ManyQuestions {
    /**
     * This method returns a string representation of numbers from lowest to largest
     *
     * @param a Integer value
     * @param b Integer value
     *          integer value
     */
    public static String sumUpFromLowerToLarger(int a, int b) {
        String rValue = "";
        if (a < b) {
            for (int index = a; index < b; index++)
                rValue += a + ": " + index + " ";
        } else {
            for (int index = b; index < a; index++)
                rValue += b + ": " + index + " ";
        }
        return rValue;
    }

    public static String methodCall(int a, int b) {
        String rValue = "";

        for (int index = b; index < a; index++)
            rValue += b + ": " + index + " ";

        return rValue;
    }

    /**
     * This method returns a string representation of numbers from lowest to largest (Optimized)
     *
     * @param a Integer value
     * @param b Integer value
     */
    public static String sumUpFromLowerToLargerBetter(int a, int b) {

        return a < b ? methodCall(b, a) : methodCall(a, b); // "you can create a more elegant solution";
    }

    /**
     * Finds maximum among 4 numbers
     *
     * @param a Integer value
     * @param b Integer value
     * @param c Integer value
     * @param d Integer value
     */
    public static int findMaximum(int a, int b, int c, int d) {
        int rValue;
        int maxAndB = 0;
        int maxCndD = 0;

        if (a > b)
            maxAndB = a;
        else
            maxAndB = b;
        if (c > d)
            maxCndD = c;
        else
            maxCndD = d;
        if (maxAndB > maxCndD)
            rValue = maxAndB;
        else
            rValue = maxCndD;
        return rValue;
    }

    /**
     * Finds maximum among 4 numbers (Optimized)
     *
     * @param a Integer value
     * @param b Integer value
     * @param c Integer value
     * @param d Integer value
     */
    public static int findMaximumBetter(int a, int b, int c, int d) {
        int maxAndB = a > b ? a : b;
        int maxCndD = c > d ? c : d;
        return maxAndB > maxCndD ? maxAndB : maxCndD;
        //"you can create a more elegant solution";
    }

    /**
     * Returns a string with bit representation of a number maximum
     *
     * @param a Integer value
     */
    public static String printBitRepresentation(int a) {
        String rValue = "";
        for (int index = 31; index >= 0; index--) {
            if (((a) & (1 << index)) == (1 << index))
                rValue += "1";
            else
                rValue += "0";
        }
        return rValue;
    }

    /**
     * Returns a string with bit representation of a number maximum (Optimized)
     *
     * @param a Integer value
     */
    public static String printBitRepresentationBetter(int a) {
        String rValue = "";
        int logBase2 = (int) (Math.log(a) / Math.log(2)) + 1;

        for (int index = 0; index < logBase2; index++) {
            if (((a) & (1 << index)) == (1 << index))
                rValue = "1" + rValue;
            else
                rValue = "0" + rValue;
        }
        return rValue;
    }

    /**
     * Reverses string
     *
     * @param original String to be reversed
     */
    public static String reverseString(String original) {
        String rValue = "";
        for (int index = original.length(); index > 0; index--)
            rValue += original.substring(index - 1, index);
        return rValue;
    }

    /**
     * Reverses string
     *
     * @param original String to be reversed
     */
    public static String reverseStringBetter(String original) {
        String rValue = "";
        for (int index = original.length() - 1; index >= 0; index--)
            rValue += original.charAt(index);
        return rValue;
    }

    /**
     * Reverses string
     *
     * @param original String to be reversed
     */
    public static String reverseStringR(String original) {
        if (original.length() == 1) {
            return original;
        }
        return reverseStringR(original.substring(1)) + original.substring(0, 1);
    }

    /**
     * The main program
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int a = 6;
        int b = 1;
        int c = 2;
        int d = 1;
        String aString = "abcde";
        long foundThisOneMissing;
        int soManyBits = 31;
        boolean useConstantAlgorithm = false;

        if (args.length > 0)
            soManyBits = Integer.parseInt(args[0]);
        useConstantAlgorithm = (args.length > 1);
        useConstantAlgorithm = true;

        long thisNumberIsMissing = (long) new Random().nextInt((int) Math.pow(2, soManyBits));
        thisNumberIsMissing += (thisNumberIsMissing == 0) ? 1 : 0;

        System.out.println("printFromLowerToLarger(" + a + "," + b + ") = " + sumUpFromLowerToLarger(a, b));
        System.out.println("sumUpFromLowerToLargerBetter(" + a + "," + b + ") = " + sumUpFromLowerToLargerBetter(a, b));
        System.out.println("printBitRepresentation(" + a + "):    " + printBitRepresentation(a));
        System.out.println("printBitRepresentationBetter(" + a + "):   " + printBitRepresentationBetter(a));
        System.out.println("findMaximum(" + a + ", " + b + ", " + c + ", " + d + " ) = " + findMaximum(a, b, c, d));
        System.out.println("findMaximumBetter(" + a + ", " + b + ", " + c + ", " + d + " ) = " + findMaximumBetter(a, b, c, d));
        System.out.println("reverseString(" + aString + ") =   " + reverseString(aString));
        System.out.println("reverseStringR(" + aString + ") =  " + reverseStringR(aString));
        System.out.println("reverseStringBetter(" + aString + ") = " + reverseStringBetter(aString));
    }
}