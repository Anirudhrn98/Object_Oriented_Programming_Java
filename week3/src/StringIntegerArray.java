/*
 * StringIntegerArray.java
 *
 * Version:
 *      $Id$
 *
 * Revision:
 *      $Log$
 *
 */

/*
 * This program compares content, object memory references, and multiple other scenarios regarded in homework 3:
 * https://www.cs.rit.edu/~hpb/Lectures/2211/605/
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

import java.util.Arrays;

public class StringIntegerArray {
    public static void main(String[] args) {
        String aString = "";
        String bString = "";
        String cString = "";
        String dString = "";
        String eString = "";
        String fString = "";
        String gString = "";
        String hString = "";
        String iString = "";

        if (args.length == 1) {
            aString = "123425" + "25";
            bString = "12342525";
            cString = "5";
            dString = "25" + "2" + cString;
            eString = "a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z";
            fString = "A, B, C, D, E, F, G, H, I, J, K, *, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z";
            gString = aString + (bString + cString) + dString;
            hString = "2525" + "+" + "2525";
            iString = "2525+2525";
        } else {
            aString = "12342" + new String("" + 5) + "25";
            bString = "12342525";
            cString = "5";
            dString = "" + "25" + "" + "2" + cString;
            eString = "b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z";
            fString = "B, C, D, E, F, G, H, I, J, K, *, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z";
            gString = ((aString + bString) + "" + 5) + dString;
            hString = "2525" + "+" + "2525";
            iString = "25" + (2 + 5) + "+2525";
        }
        // 1. Determine if aString and bString are equal
        boolean firstQ = aString.equals(bString);
        System.out.println("I: \t\t" + firstQ);

        // 2. Determine if aString and bString are identical
        boolean secondQ = (aString.equals(bString)) && (aString == bString);
        System.out.println("II: \t" + secondQ);

        // 3. Determine if aString and dString are equal
        boolean thirdQ = aString.equals(dString);
        System.out.println("III: \t" + thirdQ);

        // 4. Determine if aString and dString are identical
        boolean fourthQ = (aString.equals(dString)) && (aString == dString);
        System.out.println("IV: \t" + fourthQ);

        // 5. Determine if hString and iString are equal
        boolean fifthQ = hString.equals(iString);
        System.out.println("V: \t\t" + fifthQ);

        // 6. Determine if hString and iString are identical
        boolean sixth = (hString.equals(iString)) && (hString == iString);
        System.out.println("VI: \t" + sixth);

        // 7. Extract from dString the string starting at the number defined at the first position
        // in aString and ending t the number defined at the second position in aString.
        // You have to extract these numbers - 2 and 5, and you can not hard code them
        int aStringFirst = Integer.parseInt(aString.split("")[0]);
        int aStringSecond = Integer.parseInt(aString.split("")[1]);

        // Integer.parseInt(dString, aStringFirst, aStringSecond+1, 10)
        String[] rangeFromFirstToSecond = dString.substring(aStringFirst, aStringSecond + 1).split("");
        System.out.println("VII: \t" + Arrays.toString(rangeFromFirstToSecond));

        // 8. Extract the character of eString which is at the position of ’*’ on fString
        char foundChar = eString.charAt(fString.indexOf("*"));
        System.out.println("VIII: \t" + foundChar);

        // 9. Sort the digits in bString, and extract out of dString between the 3. and 4 lowest number.
        // In this example the numbers would be 2 and 3.
        char[] sortedBString = bString.toCharArray();
        Arrays.sort(sortedBString);
        int indexTwo = Integer.parseInt(Character.toString(sortedBString[3]));
        int indexThree = Integer.parseInt(Character.toString(sortedBString[4]));
        String[] asd = dString.substring(indexTwo, indexThree).split("");
        System.out.println("IX: \t" + Arrays.toString(asd));
    }
}
