package interface_impl;

interface Moveable{
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
    boolean isAbleToMove(int rowPieceLoc, int colPieceLoc, int heightPieceLoc, char chessPiece);
}

class Rook implements Moveable{
    static char chessPiece = 'R';
    static String chessPieceName = "interface_impl.Rook";
    static int size;
    static int dimensions;
    static char[][][] theBoard;

    /**
     * Class interface_impl.Rook constructor
     */
    public Rook(int sizeVar, int dimensionsVar, char[][][] theBoardVar) {
        size = sizeVar;
        dimensions = dimensionsVar;
        theBoard = theBoardVar;
    }

    /**
     * checks to see if the piece can be placed in the required position
     *
     * @param rowPieceLoc           Row index of interface_impl.Rook
     * @param colPieceLoc           Column index of interface_impl.Rook
     * @param heightPieceLoc        Height index of interface_impl.Rook
     * @param chessPiece            Character representing interface_impl.Rook
     * @return boolean    true if it can be placed/ false if it can't
     */
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

class Bishop implements Moveable {
    final char chessPiece = 'B';
    final String chessPieceName = "interface_impl.Bishop";
    static int size;
    static int dimensions;
    static char[][][] theBoard;

    /**
     * Class interface_impl.Rook constructor
     */
    public Bishop(int sizeVar, int dimensionsVar, char[][][] theBoardVar) {
        size = sizeVar;
        dimensions = dimensionsVar;
        theBoard = theBoardVar;
    }
    /**
     * checks to see if the piece can be placed in the required position
     *
     * @param rowPieceLoc           Row index of interface_impl.Bishop
     * @param colPieceLoc           Column index of interface_impl.Bishop
     * @param heightPieceLoc        Height index of interface_impl.Bishop
     * @param chessPiece            Character representing interface_impl.Bishop
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

class Knight implements Moveable {
    final char chessPiece = 'N';
    final String chessPieceName = "interface_impl.Knight";
    static int size;
    static int dimensions;
    static char[][][] theBoard;

    /**
     * Class interface_impl.Rook constructor
     */
    public Knight(int sizeVar, int dimensionsVar, char[][][] theBoardVar) {
        size = sizeVar;
        dimensions = dimensionsVar;
        theBoard = theBoardVar;
    }
    /**
     * helper method - to reduce redundant code in `isAbleToMove()`
     *
     * @param chessPiece            Character representing interface_impl.Bishop
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
     * @param rowPieceLoc           Row index of interface_impl.Knight
     * @param colPieceLoc           Column index of interface_impl.Knight
     * @param heightPieceLoc        Height index of interface_impl.Knight
     * @param chessPiece            Character representing interface_impl.Knight
     * @return boolean              true if it can be placed/ false if it can't
     */
    public boolean isAbleToMove(int rowPieceLoc, int colPieceLoc, int heightPieceLoc, char chessPiece) {
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

class Queen implements Moveable {
    final char chessPiece = 'Q';
    final String chessPieceName = "interface_impl.Queen";

    Bishop b;
    Rook r;

    /**
     * Class interface_impl.Queen constructor
     */
    Queen(int size, int dimensions, char[][][] theBoard) {
        b = new Bishop(size, dimensions, theBoard);
        r = new Rook(size, dimensions, theBoard);
    }

    /**
     * checks to see if the piece can be placed in the required position
     *
     * @param rowPieceLoc           Row index of interface_impl.Queen
     * @param colPieceLoc           Column index of interface_impl.Queen
     * @param heightPieceLoc        Height index of interface_impl.Queen
     * @param chessPiece            Character representing interface_impl.Queen
     * @return boolean              true if it can be placed/ false if it can't
     */
    public boolean isAbleToMove(int rowPieceLoc, int colPieceLoc, int heightPieceLoc, char chessPiece) {
        return b.isAbleToMove(rowPieceLoc, colPieceLoc, heightPieceLoc, 'Q') &&
                r.isAbleToMove(rowPieceLoc, colPieceLoc, heightPieceLoc, 'Q');
    }
}
