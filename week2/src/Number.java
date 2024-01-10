/*
 * Number.java
 *
 * Version:
 *      $Id$
 *
 * Revision:
 *      $Log$
 *
 */

/*
 * This program finds numbers with the property that if you sum their individual elements, recursively, they come back
 * to number one.
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

import static java.lang.Math.pow;

public class Number {
    static String details = "";
    static final int MAX_ITER = 1000;

    static String combos = new String("");
    static String counts = "";

    /**
     * Separates, squares and sums the value of the numbers.
     *
     * @param index number which has the intrinsic property of returning to 1
     * @param sum   the previous sum of individual elements of the 'index' param (starts with 0)
     * @return sum       the sum of individual elements of the 'number' param after calculating
     */
    public static int[] separateSquareSumAndStore(int num) {
        String strReprNum = "" + num;
        int[] digitsInNum = new int[strReprNum.length()];
        for (int index = 0; index < strReprNum.length(); index++) {
            digitsInNum[index] = Integer.parseInt(""+strReprNum.charAt(index));
        }
        return digitsInNum;
    }

    /**
     * Checks for the particular intrisinc number property.
     *
     * @param index number which has the intrinsic property of returning to 1
     * @param sum   the previous sum of individual elements of the 'index' param (starts with 0)
     * @param flag  changes the flow of the program after the first iteration
     */
    public static boolean hasNumProp(int num) {
        // Have we seen this number in the past?
        int result=0;
        details = "";
        int[] digits = separateSquareSumAndStore(num);
        boolean[] usedNumber = new boolean[MAX_ITER];
        do {
            usedNumber[result] = true;

            result=0;
            for(int digit: digits){
                result += Math.pow(digit, 2);
                details += digit + "^2 + ";
            }
            details = details.substring(0, details.length()-3);
            details += "=" +result+  " |";

            digits = separateSquareSumAndStore(result);
        }
        while (!usedNumber[result] && result != 1);

        return result == 1;
    }

    /**
     * The main program.
     *
     * @param args command line arguments (commented)
     */
    public static void main(String[] args) {
        for (int num = 1; num <= 100; num++) {
            if (hasNumProp(num)) {
                System.out.println(num + ": " + details);
            }
        }
    }
}
