package abstract_impl;

public final class Board {
    private static int dimensions;
    private static int size;
    private static char[][][] holes;
    private static char[][][] theBoard;

    /**
     * Class contructor
     */
    private Board(String[] args) {
        for (int index = 0; index < args.length; index += 2) {
            if (args[index].endsWith("-d")) {
                dimensions = Integer.parseInt(args[index + 1]);
            } else if (dimensions == 2) {
                if (args[index].endsWith("-n")) {
                    size = Integer.parseInt(args[index + 1]);
                    holes = new char[size][size][1];
                    theBoard = new char[size][size][1];     // set height of board as 1 if it is a dimensional board
                } else if (args[index].endsWith("-h")) {
                    String[] temp = args[index + 1].split("\\.");
                    if (temp.length == 1) {
                        holes[Integer.parseInt(temp[0])][0][0] = ' ';
                    } else {
                        holes[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][0] = ' ';
                    }
                }
            } else if (dimensions == 3) {
                if (args[index].endsWith("-n")) {
                    size = Integer.parseInt(args[index + 1]);
                    holes = new char[size][size][size];
                    theBoard = new char[size][size][size];
                } else if (args[index].endsWith("-h")) {
                    String[] temp = args[index + 1].split("\\.");
                    if (temp.length == 1) {
                        holes[Integer.parseInt(temp[0])][0][0] = ' ';
                    } else if (temp.length == 2) {
                        holes[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][0] = ' ';
                    } else {
                        holes[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][Integer.parseInt(temp[2])] = ' ';
                    }
                }
            }
        }
    }

    /**
     * Converts the current board to a string representation
     *
     * @return board     string representation of board
     */
    public static String boardToString() {
        String board = "";

        int heightDimension = dimensions == 3 ? size : 1;
        for (int h = 0; h < heightDimension; h++) {
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    board += " " + theBoard[r][c][h];
                }
                board += "\n";
            }
            board += "\n";
        }
        return board;
    }

    /**
     * Initializes the board with the colors 'w', 'b', ' '
     */
    private static void colorBoard() {
        char NOT_LEGAL = ' ';
        int heightDimension = dimensions == 3 ? size : 1;
        for (int height = 0; height < heightDimension; height++) {
            for (int row = 0; row < size; row++) {
                for (int column = 0; column < size; column++) {
                    theBoard[row][column][height] = whatColor(row, column, height);
                    if (holes[row][column][height] == ' ') {
                        theBoard[row][column][height] = NOT_LEGAL;
                    }
                }
            }
        }
    }


    /**
     * Sets the color to the board
     *
     * @param row    index of row
     * @param column index of column
     * @param height index of height
     * @return 'w'/'b'      white or black color
     */
    public static char whatColor(int row, int column, int height) {
        // if even -> return 'w'
        if ((row + column + height) % 2 == 0) {
            return 'w';
        }
        // if odd -> return 'b'
        else {
            return 'b';
        }
    }

    /**
     * prints the board, for a visual representation
     */
    public static void printBoard() {
        int heightDimension = dimensions == 3 ? size : 1;
        for (int h = 0; h < heightDimension; h++) {
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    System.out.print(theBoard[r][c][h] + " ");
                }
                System.out.println();
            }
        }
    }

    /**
     * Initializes the class parameters of the interface_impl.Board (Utility class)
     *
     * @param args arguments passed for initializing the class
     */
    public static void initBoard(String[] args) {
        new Board(args);
        colorBoard();
    }

    /**
     * Get-method for dimensions
     *
     * @return dimensions
     */
    public static int getDimensions() {
        return dimensions;
    }

    /**
     * Get-method for size
     *
     * @return size
     */
    public static int getSize() {
        return size;
    }

    /**
     * Get-method for theBoard
     *
     * @return theBoard
     */
    public static char[][][] getBoard() {
        return theBoard;
    }

}