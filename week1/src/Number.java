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
    static int MAX_ITER = 1000;
    static String combos = new String("");
    static String counts = "";
    static boolean[] usedNumber = new boolean[MAX_ITER];

    /**
     * Prints the solution into a format that is easier to read by the human.
     *
     * @param     index     number which has the intrinsic property of returning to 1
     *
     */
    public static void makeSolutionLookPretty(int index){
        String[] splitCombos = combos.split("-");
        String[] splitCounts = counts.split("-");
        System.out.print(index + "\t: ");

        for (int r=0; r < splitCombos.length; r++){
            String [] k = splitCombos[r].split(",");

            for (int c=k.length-1; c>0; c--){
                // If it is not the last value, add '+' in the end
                if (c!=1){
                    System.out.print(k[c]+"^2 + ");
                } else {
                    System.out.print(k[c]+"^2" );
                }
            }
            // Print summation of those two values
            System.out.print(" = " + splitCounts[r] + " | ");
        }
        System.out.println();
    }

    /**
     * Separates, squares and sums the value of the numbers.
     *
     * @param     index     number which has the intrinsic property of returning to 1
     * @param     sum       the previous sum of individual elements of the 'index' param (starts with 0)
     *
     * @return    sum       the sum of individual elements of the 'number' param after calculating
     *
     */
    public static int separateSquareSumAndStore(int index, int sum){
        int number = index;
        int remainder;

        while (number > 0) {
            remainder = number % 10; // Finds the first (the right-most) value
            number    = number / 10; // Removes the first (the right-most) value

            sum      += pow(remainder, 2.0); // Squares the remainder and then adds to the existing sum
            combos    = combos.concat("," + remainder);
        }
        counts += sum + "-";
        combos += "-";

        return sum;
    }

    /**
     * Checks for the particular intrisinc number property.
     *
     * @param     index     number which has the intrinsic property of returning to 1
     * @param     sum       the previous sum of individual elements of the 'index' param (starts with 0)
     * @param     flag      changes the flow of the program after the first iteration
     *
     */
    public static void checkForNumProp(int index, int sum, boolean flag){
        // Have we seen this number in the past?
        while (!usedNumber[sum]) { // !counts.contains(String.valueOf(sum))
            if (flag) {
                usedNumber[index] = true;
                sum = separateSquareSumAndStore(index, sum );
            }
            else {
                usedNumber[sum] = true;
                sum = separateSquareSumAndStore(sum, 0);
            }

            if (sum == 1){
                makeSolutionLookPretty(index);
                break;
            }
            checkForNumProp(index, sum, false);
        }
    }

    /**
     * The main program.
     *
     * @param    args    command line arguments (commented)
     */
    public static void main(String[] args) {
        for (int i = 1; i < MAX_ITER; i++){
            counts     = "";
            combos     = "";
            usedNumber = new boolean[MAX_ITER];

            checkForNumProp(i, 0, true);
        }
    }
}
