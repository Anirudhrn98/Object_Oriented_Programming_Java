package abstract_impl;

abstract class ChessPiece {
    static int size;
    static int dimensions;
    static char[][][] theBoard;
    static char hole = ' ';
    static char chessPiece = '?';

    /**
     * Class ChessPiece constructor
     */
    ChessPiece(int sizeVar, int dimensionsVar, char[][][] theBoardVar) {
        size = sizeVar;
        dimensions = dimensionsVar;
        theBoard = theBoardVar;
    }

    /**
     * places piece in the required index
     *
     * @param chessPiece            Represents symbol of the chess piece
     * @param rowPieceLoc           row index of piece
     * @param colPieceLoc           column index of piece
     * @param heightPieceLoc        height index of piece
     * @return boolean              true if it is placed/ false if it is not
     */
    public boolean placePiece(char chessPiece, int rowPieceLoc, int colPieceLoc, int heightPieceLoc) {
        if (theBoard[rowPieceLoc][colPieceLoc][heightPieceLoc] != hole && isAbleToMove(rowPieceLoc, colPieceLoc, heightPieceLoc, chessPiece)) {
            theBoard[rowPieceLoc][colPieceLoc][heightPieceLoc] = chessPiece;
            return true;
        }
        return false;
    }

    /**
     * Counts # of pieces in the board
     *
     * @return cntr    # of pieces
     */
    public int countPiece(char chessPiece) {
        int cntr = 0;
        for (int h = 0; h < (dimensions == 3 ? size : 1); h++) {
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (theBoard[r][c][h] == chessPiece) {
                        cntr += 1;
                    }
                }
            }
        }
        return cntr;
    }

    /**
     * abstract method to be implemented in child methods
     * -> checks to see if the piece can be placed in the required position
     *
     * @param rowPieceLoc               Row index of piece
     * @param colPieceLoc               Column index of piece
     * @param heightPieceLoc            Height index of piece
     * @param chessPiece                Character representing the rook
     * @return boolean                  true if it can be placed/ false if it can't
     */
    abstract boolean isAbleToMove(int rowPieceLoc, int colPieceLoc, int heightPieceLoc, char chessPiece);
}

class Rook extends ChessPiece {
    static char chessPiece = 'R';
    static String chessPieceName = "Rook";

    /**
     * Class Rook constructor
     */
    public Rook(int size, int dimensions, char[][][] theBoard) {
        super(size, dimensions, theBoard);
    }

    /**
     * checks to see if the piece can be placed in the required position
     *
     * @param rowPieceLoc           Row index of Rook
     * @param colPieceLoc           Column index of Rook
     * @param heightPieceLoc        Height index of Rook
     * @param chessPiece            Character representing Rook
     * @return boolean    true if it can be placed/ false if it can't
     */
    @Override
    public boolean isAbleToMove(int rowPieceLoc, int colPieceLoc, int heightPieceLoc, char chessPiece) {
        for (int row = 0; row < size; row++) {
            if ((row != rowPieceLoc) && theBoard[row][colPieceLoc][heightPieceLoc] == chessPiece) {
                return false;
            }
        }

        for (int column = 0; column < size; column++) {
            // Check if the piece is getting scared of itself
            if ((column != colPieceLoc) && theBoard[rowPieceLoc][column][heightPieceLoc] == chessPiece) {
                return false;
            }
        }

        for (int height = 0; height < (dimensions == 3 ? size : 1); height++) {
            // Check if the piece is getting scared of itself
            if ((height != heightPieceLoc) && theBoard[rowPieceLoc][colPieceLoc][height] == chessPiece) {
                return false;
            }
        }
        return true;
    }

}

class Bishop extends ChessPiece {
    final char chessPiece = 'B';
    final String chessPieceName = "Bishop";

    /**
     * Class Bishop constructor
     */
    Bishop(int size, int dimensions, char[][][] theBoard) {
        super(size, dimensions, theBoard);
    }

    /**
     * checks to see if the piece can be placed in the required position
     *
     * @param rowPieceLoc           Row index of Bishop
     * @param colPieceLoc           Column index of Bishop
     * @param heightPieceLoc        Height index of Bishop
     * @param chessPiece            Character representing Bishop
     * @return boolean              true if it can be placed/ false if it can't
     */
    @Override
    public boolean isAbleToMove(int rowPieceLoc, int colPieceLoc, int heightPieceLoc, char chessPiece) {

        // (2,3,0)
        int coor1 = Math.abs(rowPieceLoc - colPieceLoc);
        // coor1 = 1
        int coorIdOfSmaller = rowPieceLoc <= colPieceLoc ? 0 : 1;
        // coorIdOfSmaller = 0

        int[] coords = new int[2];
        // the opposite becomes the difference of row and column
        coords[1 - coorIdOfSmaller] = coor1;
        for (int coor = coords[coorIdOfSmaller]; coords[0] + coor < size && coords[1] + coor < size; coor++) {
            if (((coords[0] + coor != rowPieceLoc) || (coords[1] + coor != colPieceLoc))
                    && theBoard[coords[0] + coor][coords[1] + coor][heightPieceLoc] == chessPiece) {
                return false;
            }
        }

        int sum = rowPieceLoc + colPieceLoc;

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (row + column == sum && theBoard[row][column][heightPieceLoc] == chessPiece) {
                    return false;
                }
            }
        }


        for (int height = 1; height < (dimensions == 3 ? size : 1); height++) {
            for (int row = rowPieceLoc - height; row <= rowPieceLoc + height; row = row + height) {
                for (int column = colPieceLoc - height; column <= colPieceLoc + height; column = column + height) {
                    if (row < size && row >= 0) {
                        if ((heightPieceLoc + height < size) & (column < size && column >= 0)
                                && (theBoard[row][column][heightPieceLoc + height] == chessPiece)) {
                            return false;
                        }
                        if ((heightPieceLoc - height >= 0) && (column < size && column >= 0)
                                && (theBoard[row][column][heightPieceLoc - height] == chessPiece)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}

class Knight extends ChessPiece {
    final char chessPiece = 'N';
    final String chessPieceName = "Knight";

    /**
     * Class Knight constructor
     */
    Knight(int size, int dimensions, char[][][] theBoard) {
        super(size, dimensions, theBoard);
    }

    /**
     * helper method - to reduce redundant code in `isAbleToMove()`
     *
     * @param chessPiece            Character representing Bishop
     * @param directionOne          First directions to iterate over
     * @param directionTwo          Second directions to iterate over
     * @param firstOne              Flag denoting which one is row/column/height
     * @return boolean              true if it can be placed/ false if it can't
     */
    boolean isAbleToMoveOneDir(char chessPiece, int directionOne, int directionTwo, int thirdDirection, int firstOne) {
        for (int dirOne = directionOne - 2; dirOne <= directionOne + 2; dirOne = dirOne + 4) {
            if (dirOne < size && dirOne >= 0) {
                for (int dirTwo = directionTwo - 1; dirTwo <= directionTwo + 1; dirTwo = dirTwo + 2) {
                    if ((firstOne == 0) && (dirTwo < size && dirTwo >= 0)
                            && (theBoard[dirOne][dirTwo][thirdDirection] == chessPiece)) {
                        return false;
                    } else if ((firstOne == 1) && (dirTwo < size && dirTwo >= 0)
                            && (theBoard[dirTwo][dirOne][thirdDirection] == chessPiece)) {
                        return false;
                    } else if ((firstOne == 2) && (dirTwo < size && dirTwo >= 0)
                            && (theBoard[dirTwo][thirdDirection][dirOne] == chessPiece)) {
                        return false;
                    } else if ((firstOne == 3) && (dirTwo < size && dirTwo >= 0)
                            && (theBoard[dirOne][thirdDirection][dirTwo] == chessPiece)) {
                        return false;
                    } else if ((firstOne == 4) && (dirTwo < size && dirTwo >= 0)
                            && (theBoard[thirdDirection][dirOne][dirTwo] == chessPiece)) {
                        return false;
                    } else if ((firstOne == 5) && (dirTwo < size && dirTwo >= 0)
                            && (theBoard[thirdDirection][dirTwo][dirOne] == chessPiece)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * checks to see if the piece can be placed in the required position
     *
     * @param rowPieceLoc           Row index of Knight
     * @param colPieceLoc           Column index of Knight
     * @param heightPieceLoc        Height index of Knight
     * @param chessPiece            Character representing Knight
     * @return boolean              true if it can be placed/ false if it can't
     */
    @Override
    boolean isAbleToMove(int rowPieceLoc, int colPieceLoc, int heightPieceLoc, char chessPiece) {
        if (dimensions != 3) {
            return isAbleToMoveOneDir(chessPiece, rowPieceLoc, colPieceLoc, heightPieceLoc, 0) &&
                    isAbleToMoveOneDir(chessPiece, colPieceLoc, rowPieceLoc, heightPieceLoc, 1);
        } else {
            return isAbleToMoveOneDir(chessPiece, rowPieceLoc, colPieceLoc, heightPieceLoc, 0) &&
                    isAbleToMoveOneDir(chessPiece, colPieceLoc, rowPieceLoc, heightPieceLoc, 1) &&
                    isAbleToMoveOneDir(chessPiece, heightPieceLoc, rowPieceLoc, colPieceLoc, 2) &&
                    isAbleToMoveOneDir(chessPiece, rowPieceLoc, heightPieceLoc, colPieceLoc, 3) &&
                    isAbleToMoveOneDir(chessPiece, heightPieceLoc, colPieceLoc, rowPieceLoc, 4) &&
                    isAbleToMoveOneDir(chessPiece, colPieceLoc, heightPieceLoc, rowPieceLoc, 5);

        }
    }
}

class Queen extends ChessPiece {
    final char chessPiece = 'Q';
    final String chessPieceName = "Queen";

    Bishop b;
    Rook r;

    /**
     * Class Queen constructor
     */
    Queen(int size, int dimensions, char[][][] theBoard) {
        super(size, dimensions, theBoard);
        b = new Bishop(size, dimensions, theBoard);
        r = new Rook(size, dimensions, theBoard);
    }

    /**
     * checks to see if the piece can be placed in the required position
     *
     * @param rowPieceLoc           Row index of Queen
     * @param colPieceLoc           Column index of Queen
     * @param heightPieceLoc        Height index of Queen
     * @param chessPiece            Character representing Queen
     * @return boolean              true if it can be placed/ false if it can't
     */
    @Override
    boolean isAbleToMove(int rowPieceLoc, int colPieceLoc, int heightPieceLoc, char chessPiece) {
        return b.isAbleToMove(rowPieceLoc, colPieceLoc, heightPieceLoc, 'Q') &&
                r.isAbleToMove(rowPieceLoc, colPieceLoc, heightPieceLoc, 'Q');
    }
}
