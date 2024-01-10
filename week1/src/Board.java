/*
 * Board.java
 *
 * Version:
 *      $Id$
 *
 * Revision:
 *      $Log$
 *
 */

/*
 * This program finds the maximum number of kings that can be places in the board with the given board.
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

public class Board {
    static int MAX_ROWS = 6;        // Maximum number of rows
    static int MAX_COLUMNS = 6;     // Maximum number of cols
    static char[][] theBoard = new char[1 + MAX_ROWS][MAX_COLUMNS];
    static int max = -1;            // Maximum number of kings
    static String maxBoard = "";    // The corresponding board (of maximum # of kings)


    /**
     * Converts the current board to a string representation
     *
     * @return board     string representation of board
     */
    public static String boardToString() {
        String board = "";

        for (int r = 0; r < MAX_ROWS; r++) {
            for (int c = 0; c < MAX_COLUMNS; c++) {
                board += " " + theBoard[r][c];
            }
            board += "\n";
        }
        return board;
    }

    /**
     * Initializes the board with 'w', 'b', '.'
     */
    public static void initTheBoard() {
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int column = 0; column < MAX_COLUMNS; column++) {
                theBoard[row][column] = whatColor(row, column);
                createWall();
            }
        }
    }

    /**
     * Sets the color to the board
     *
     * @param row    index of row
     * @param column index of column
     * @return 'w'        white color
     * @return 'b'        black color
     */
    public static char whatColor(int row, int column) {
        // if even -> return 'w'
        if ((row + column) % 2 == 0) {
            return 'w';
        } else {
            return 'b';
        }
    }

    /**
     * prints the board, for a visual representation
     */
    public static void printBoard() {
        for (int r = 0; r < MAX_ROWS; r++) {
            for (int c = 0; c < MAX_COLUMNS; c++) {
                System.out.print(theBoard[r][c] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Code written by H.P. Bischop
     * <p>
     * Builds the wall in the board, where nothing can be placed
     */
    public static void createWall() {
        char NOT_LEGAL = '.';
        theBoard[2][0] = NOT_LEGAL;
        theBoard[2][1] = NOT_LEGAL;
        theBoard[2][2] = NOT_LEGAL;
        theBoard[2][3] = NOT_LEGAL;
        theBoard[3][1] = NOT_LEGAL;
        theBoard[3][3] = NOT_LEGAL;
        theBoard[4][1] = NOT_LEGAL;
        theBoard[4][2] = NOT_LEGAL;
        theBoard[4][3] = NOT_LEGAL;
        for (int index = 0; index < MAX_ROWS - 1; index += 2) {
            theBoard[1][index] = NOT_LEGAL;
            theBoard[MAX_ROWS - 2][index] = NOT_LEGAL;
        }
    }

    /**
     * checks to see if the king can be placed in the required position
     *
     * @param rowKingLoc Row index of king
     * @param colKingLoc Column index of king
     * @return boolean        true if it can be placed/ false if it can't
     */
    public static boolean isKingInDancer(int rowKingLoc, int colKingLoc) {
        // Check, in rows, if there's a king in sight
        for (int row = rowKingLoc - 1; row <= rowKingLoc + 1; row++) {
            // Is inside the row boundaries of the board
            if (row < 0) {
                row = 0;
            }
            if (row >= MAX_ROWS) {
                continue;
            }

            // Check, in columns, if there's a king in sight
            for (int column = colKingLoc - 1; column <= colKingLoc + 1; column++) {
                // Is inside the column boundaries of the board
                if (column < 0) {
                    column = 0;
                }
                if (column >= MAX_COLUMNS) {
                    continue;
                }

                // Check if the king is getting scared of itself
                if ((row != rowKingLoc || column != colKingLoc) && theBoard[row][column] == 'K') {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * places king in the required index
     *
     * @param rowKingLoc row index of king
     * @param colKingLoc column index of king
     * @return boolean        true if it is placed/ false if it is not
     */
    public static boolean placeKing(int rowKingLoc, int colKingLoc) {
        if (theBoard[rowKingLoc][colKingLoc] != '.' && !isKingInDancer(rowKingLoc, colKingLoc)) {
            theBoard[rowKingLoc][colKingLoc] = 'K';
            return true;
        }
        return false;
    }

    /**
     * Counts # of kings in the board
     *
     * @return cntr    # of kings
     */
    public static int countKings() {
        int cntr = 0;
        for (int r = 0; r < MAX_ROWS; r++) {
            for (int c = 0; c < MAX_COLUMNS; c++) {
                if (theBoard[r][c] == 'K') {
                    cntr += 1;
                }
            }
        }
        return cntr;
    }

    /**
     * Checks the combination of king placement in the board
     *
     * @param rowKingLoc row index of king
     * @param colKingLoc column index of king
     */
    static void checkKingCombinations(int rowKingLoc, int colKingLoc) {
        // only if you see to store the highest number of kings -> SAVE THAT (update the last state)
        char x1 = theBoard[rowKingLoc][colKingLoc];
        boolean x = placeKing(rowKingLoc, colKingLoc);

        // if it has reached the end of board
        if ((rowKingLoc == MAX_ROWS - 1) && (colKingLoc == MAX_COLUMNS - 1)) {
            int current = countKings();
            if (current > max) {
                max = current;
                maxBoard = boardToString();
            }
//            int combinationInd = insertInComb(combinationOfKings);
//            counts[combinationInd] = countKings();
            //
            return;
        }

        // iterate recursively
        if (colKingLoc == MAX_COLUMNS - 1) {
            checkKingCombinations(rowKingLoc + 1, 0);
        } else {
            checkKingCombinations(rowKingLoc, colKingLoc + 1);
        }

        if (theBoard[rowKingLoc][colKingLoc] == 'K') {
            theBoard[rowKingLoc][colKingLoc] = x1;
            if (colKingLoc < MAX_COLUMNS - 1) {
                checkKingCombinations(rowKingLoc, colKingLoc + 1);

            }
        }
    }

    /**
     * The main program.
     *
     * @param args command line arguments (commented)
     */
    public static void main(String[] args) {
        int[] counts = new int[100];
        String[] combOfKings = new String[100];
        initTheBoard();

        checkKingCombinations(0, 0);
        System.out.println("The max number of kings = " + max);
        System.out.println("The corresponding board =" + maxBoard);
    }

}
