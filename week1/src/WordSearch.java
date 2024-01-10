/*
 * WordSearch.java
 *
 * Version:
 *      $Id$
 *
 * Revision:
 *      $Log$
 *
 */

/*
 * This program searches for words in a given puzzle, forwards/ backwards/ upwards/ downwards.
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */
public class WordSearch {
    static String[] puzzle = {  "tomato",       // 6 x 6
            "omelet",
            "carrot",
            "onions",
            "garlic",
            "bagels"
    };
    static String[] wordsToSearchFor = { "to", "me", "ma", "on" };

    /**
     * Reverse given string.
     *
     * @param     strToReverse      String to reverse
     *
     */
    public static String reverseString(String strToReverse){
        String result = new String("");
        for (int i = strToReverse.length() - 1; i >= 0 ; i--){
            result += strToReverse.charAt(i);
        }
        return result;
    }

    /**
     * Searches for words and their reverse order in the given puzzle.
     *
     * @param     puzz                 given puzzle to search for
     * @param     direction            row/column
     * @param     directionString      "row"/"column"
     * @param     word                 string to look for
     * @param     hasFound             has found string as it is
     * @param     hadFoundReverse      has found reversed string
     *
     */
    public static void deeperSearchForWord(String[] puzz, int direction, String directionString, String word,
                                           boolean hasFound, boolean hadFoundReverse){

        hasFound = puzz[direction].contains(word);                       // checks for string as it is
        hadFoundReverse = puzz[direction].contains(reverseString(word)); // checks for reversed string

        if (hasFound || hadFoundReverse){
            hasFound = false;
            hadFoundReverse = false;
            System.out.println("Found '"+word+"' in "+directionString+": "+direction+" "+puzz[direction]);
        }
    }

    /**
     * Searches for a specific word in the given puzzle.
     *
     * @param     word      word to look for
     *
     */
    public static void searchForWord(String word){
        String[] columWise = new String[puzzle.length];
        boolean hasFound = false;
        boolean hadFoundReverse = false;

        for ( int row = 0; row < puzzle.length; row++){

            deeperSearchForWord(puzzle, row, "row", word, hasFound, hadFoundReverse);

            // Look in the columns direction
            columWise[row] = "";
            for ( int col = 0; col < puzzle.length; col++){
                columWise[row] = columWise[row] + puzzle[col].charAt(row);
            }

            deeperSearchForWord(columWise, row, "col", word, hasFound, hadFoundReverse);
        }
    }

    /**
     * The main program.
     *
     * @param    args    command line arguments (commented)
     */
    public static void main(String[] args) {
        for (int i = 0; i < wordsToSearchFor.length; i++ ){
            searchForWord(wordsToSearchFor[i]);
        }
    }
}
