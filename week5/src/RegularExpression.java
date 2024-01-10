/*
 * RegularExample.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/*
 * This program checks given inputs against regex patterns and prints output for which
 * the pattern matches.
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RegularExpression {
    static String delimiter;
    static String[] allPatternsToTest = {"^..$", "a word which is two characters long",
            "^..+$", "a word which is two or more characters long",
            "^[a-z&&[^aeiou]]*[a][a-z&&[^aeiou]]*[e][a-z&&[^aeiou]]*[i][a-z&&[^aeiou]]*[o][a-z&&[^aeiou]]*[u][a-z&&[^aeiou]]*$",
            " a word with the vowels ’aeiou’ in order and each vowel can appear only once",
            "^(AC/DC|ac/dc)$", " includes ac/dc or AC/DC",
            "^[a-z && [^hpb]]*$", "includes only lower case characters, but not the character ’h’, ’p’, and ’b’",
            "^[(](uno|one|eins)[)]$", "starts with ’(’, followed by ’one’ or ’uno’, or ’eins’, followed by ’)’",
            "^[mM]oma$", " starts with ’M’ or ’m’ followed ’oma’",
            "^(\\[a-m\\]|\\[O-Z\\])$", " starts with ’[’, followed by ’a-m’ or ’O-Z’, followed by ’]’",
            "^[a][1-3]{2}$", " starts with ’a’ followed by 2 digits in the range between 1 to 3 only",
            "^[a][0-9]+$", " starts with ’a’ followed by one digit or more digits",
            "^[a-z]{2}\\d{3}$", "starts with 2 lower case characters’ followed by 3 digits",
    };

    /**
     * Takes file containing user input file or asks user for input string to match against
     * all the patterns and prints out for patterns that match.
     *
     * @param sc Scanner containing user input strings to match
     * @return
     */
    public static void processStatic(Scanner sc) {
        String input;
        boolean readingFromUser = false;
        do {
            if (readingFromUser) {
                System.out.print("\nPlease enter an input: ");
            }
            if (sc == null) {
                readingFromUser = true;
                System.out.print("\nPlease enter an input: ");
                sc = new Scanner(System.in).useDelimiter(delimiter);
            }
            input = sc.next();


            if (!input.equals("\r\n")) {
                System.out.println("\nWord to test:  " + input);

                for (int id = 0; id < allPatternsToTest.length; id++) {
                    if (Pattern.matches(allPatternsToTest[id], input)) {
                        System.out.println("\tThis regular expression " + allPatternsToTest[id]
                                + " matches the following input: " + input);
                        System.out.println("\tverbal explanation: " + allPatternsToTest[id + 1] + "\n");
                    }
                }
            }
        } while (sc.hasNext() && !input.equals("!q"));
        sc.close();
    }

    /**
     * Takes scanner argument from user and stores it in a file and returns file containing
     * scanner input values. Also uses delimiter while processing the scanner input.
     *
     * @param sc    Scanner containing user input
     * @param args  command line arguments from user
     * @param index index of the argument containing text file from user
     * @return sc      scanner containing text file input of user
     */
    public static Scanner readFile(Scanner sc, String[] args, int index) {
        String fileToRead;
        fileToRead = args[index];
        Path path = Paths.get(fileToRead);
        try {
            sc = new Scanner(path.toAbsolutePath()).useDelimiter(delimiter);
        } catch (IOException e) {
        }

        return sc;
    }

    /**
     * Reads command line user input
     *
     * @param args command line arguments
     * @return sc     scanner containing file input from user or null
     */
    public static Scanner readArgs(String[] args) {
        Scanner sc = null;
        if (args.length > 1) {
            for (int index = 0; index < args.length; index += 2) {
                if (args[index].endsWith("-d")) {
                    delimiter = args[index + 1];
                } else if (args[index].endsWith("-input") && args[index + 1].endsWith(".txt")) {
                    sc = readFile(sc, args, index + 1);
                    return sc;
                } else {
                    return sc;
                }
            }
        } else {
            System.out.println("Wrong command line argument. Please try again!");
        }
        return sc;
    }

    /**
     * The main program
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = readArgs(args);
        processStatic(sc);
    }
}

