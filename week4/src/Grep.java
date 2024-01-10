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
 * This program writes the pattern for regular expressions for certain DFA.
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Grep {
    static Scanner sc;

    /**
     * Checks whether the input string is accepted by the pattern ^ab$
     *
     * @param charRepresentation the input text passed as a  character array.
     * @param inputString        the input string passed as a string.
     * @return state                     boolean value to show whether string is accepted or rejected.
     */
    public static boolean anchorAB(char[] charRepresentation, String inputString) {

        int state = 0;
        if (inputString.length() == 2) {
            for (char c : charRepresentation) {
                if (state == 0) {
                    state = (c == 'a') ? 1 : -1;
                    continue;
                }
                if (state == 1) {
                    state = (c == 'b') ? 2 : -1;
                    continue;
                }
                if (state == 2) {
                    state = -1;
                }
            }
        }
        return state == 2;
    }

    /**
     * Checks whether the input string is accepted by the pattern ^[ab]c$
     *
     * @param charRepresentation the input text passed as a  character array.
     * @param inputString        the input string passed as a string.
     * @return state                     boolean value to show whether string is accepted or rejected.
     */
    public static boolean anchorABC(char[] charRepresentation, String inputString) {
        int state = 0;
        if (inputString.length() == 2) {
            for (char c : charRepresentation) {
                if (state == 0) {
                    state = (c == 'a' || c == 'b') ? 1 : -1;
                    continue;
                }
                if (state == 1) {
                    if (c == 'c') {
                        state = 2;
                    }
                }
            }
        }
        return state == 2;
    }

    /**
     * Checks whether the input string is accepted by the pattern ^(bab|aba)$
     *
     * @param charRepresentation the input text passed as a  character array.
     * @param inputString        the input string passed as a string.
     * @return state                     boolean value to show whether string is accepted or rejected.
     */
    public static boolean substringABA(char[] charRepresentation, String inputString) {
        int state = 0;
        if (inputString.length() == 3) {
            for (char c : charRepresentation) {
                if (state == 0) {
                    if (c == 'a') {
                        state = 1;
                        continue;
                    } else if (c == 'b') {
                        state = 4;
                        continue;
                    } else {
                        state = -1;
                    }
                }
                if (state == 1) {
                    state = (c == 'b') ? 2 : -1;
                    continue;
                }
                if (state == 2) {
                    state = (c == 'a') ? 3 : -1;
                }
                if (state == 4) {
                    state = (c == 'a') ? 5 : -1;
                    continue;
                }
                if (state == 5) {
                    state = (c == 'b') ? 3 : -1;
                }
            }
        }
        return (state == 3);
    }

    /**
     * Checks whether the input string is accepted by the pattern ^..\a\b$
     *
     * @param charRepresentation the input text passed as a  character array.
     * @param inputString        the input string passed as a string.
     * @return state                     boolean value to show whether string is accepted or rejected.
     */
    public static boolean dotDotAB(char[] charRepresentation, String inputString) {
        int state = 0;
        if (inputString.length() == 4) {
            for (char c : charRepresentation) {
                if (state == 0) {
                    if (c == 'a') {
                        state = 1;
                        continue;
                    } else if (c == 'b') {
                        state = 5;
                        continue;
                    } else {
                        state = -1;
                    }
                }
                if (state == 1) {
                    state = (c == 'b') ? 2 : -1;
                    continue;
                }
                if (state == 2) {
                    state = (c == 'a') ? 3 : -1;
                    continue;
                }
                if (state == 3) {
                    state = (c == 'b') ? 4 : -1;
                }
                if (state == 5) {
                    state = (c == 'a') ? 6 : -1;
                    continue;
                }
                if (state == 6) {
                    state = (c == 'b') ? 7 : -1;
                    continue;
                }
                if (state == 7) {
                    state = (c == 'a') ? 4 : -1;
                }
            }
        }
        return (state == 4);
    }

    /**
     * Checks whether the input string is accepted by the pattern ^..\b\a$
     *
     * @param charRepresentation the input text passed as a  character array.
     * @param inputString        the input string passed as a string.
     * @return state                     boolean value to show whether string is accepted or rejected.
     */
    public static boolean dotDotBA(char[] charRepresentation, String inputString) {
        int state = 0;
        if (inputString.length() == 4) {
            for (char c : charRepresentation) {
                if (state == 0) {
                    if (c == 'a') {
                        state = 1;
                        continue;
                    } else if (c == 'b') {
                        state = 5;
                        continue;
                    } else {
                        state = -1;
                    }
                }
                if (state == 1) {
                    state = (c == 'b') ? 2 : -1;
                    continue;
                }
                if (state == 2) {
                    state = (c == 'b') ? 3 : -1;
                    continue;
                }
                if (state == 3) {
                    state = (c == 'a') ? 4 : -1;
                }
                if (state == 5) {
                    state = (c == 'a') ? 6 : -1;
                    continue;
                }
                if (state == 6) {
                    state = (c == 'a') ? 7 : -1;
                    continue;
                }
                if (state == 7) {
                    state = (c == 'b') ? 4 : -1;
                }
            }
        }
        return (state == 4);
    }

    /**
     * Checks whether the input string is accepted by the pattern '.a+b.'
     *
     * @param charRepresentation the input text passed as a  character array.
     * @param inputString        the input string passed as a string.
     * @return state                     boolean value to show whether string is accepted or rejected.
     */
    public static boolean dotAPlusBDot(char[] charRepresentation, String inputString) {
        int state = 0;
        if (inputString.length() >= 4) {
            for (char c : charRepresentation) {
                if (state == 0) {
                    state = 1;
                    continue;
                }
                if (state == 1 || state == 2) {
                    if (c == 'a') {
                        state = 2;
                        continue;
                    }
                }
                if (state == 2) {
                    state = (c == 'b') ? 3 : 0;
                    continue;
                }
                if (state == 3) {
                    state = 4;
                }
            }
        }
        return (state == 4);
    }


    /**
     * Checks whether the input string is accepted by the pattern '.ab.'
     *
     * @param charRepresentation the input text passed as a  character array.
     * @param inputString        the input string passed as a string.
     * @return state             boolean value to show whether string is accepted or rejected.
     */
    public static boolean dotABdot(char[] charRepresentation, String inputString) {
        int state = 0;
        if (inputString.length() >= 4) {
            for (char c : charRepresentation) {
                if (state == 0) {
                    state = 1;
                    continue;
                }
                if (state == 1) {
                    if (c == 'a') {
                        state = 2;
                        continue;
                    }
                }
                if (state == 2) {
                    state = (c == 'b') ? 3 : 0;
                    continue;
                }
                if (state == 3) {
                    state = 4;
                }
            }
        }
        return (state == 4);
    }

    /**
     * Takes text file input from the reader and returns scanner containing data from text file.
     *
     * @param args contains user input text file.
     * @return sc          returns scanner form of user input text file.
     */
    public static Scanner readFile(String[] args) {
        String fileToRead;
        fileToRead = args[0];
        Path path = Paths.get(fileToRead);
        try {
            sc = new Scanner(path.toAbsolutePath());
        } catch (IOException e) {
        }

        return sc;
    }

    /**
     * The main program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        //take command line argument if provided or will ask user for input.
        if (args.length == 0) {
            System.out.println("Enter String to test:");
        } else if (args.length == 1) {
            in = readFile(args);
        } else {
            System.out.println("Wrong command line argument.");
        }

        //will pass the input will scanner has a next line and store it in input string and also as a
        // character array and call all methods to match patterns.

        while (in.hasNextLine()) {
            String inputString = in.nextLine();
            char[] input = inputString.toCharArray();

            System.out.println(inputString + "\t:^ab$    :\t" + anchorAB(input, inputString));

            System.out.println(inputString + "\t:.a+b.   :\t" + dotAPlusBDot(input, inputString));

            System.out.println(inputString + "\t:.ab.    :\t" + dotABdot(input, inputString));

            System.out.println(inputString + "\t:^[ab]c& :\t" + anchorABC(input, inputString));

            System.out.println(inputString + "\t:aba|bab :\t" + substringABA(input, inputString));

            System.out.println(inputString + "\t:^..ab&  :\t" + dotDotAB(input, inputString));

            System.out.println(inputString + "\t:^..ba&  :\t" + dotDotBA(input, inputString) + "\n");

        }
    }
}







