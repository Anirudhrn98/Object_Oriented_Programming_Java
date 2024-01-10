public class Chess {
    static int max = -1;            // Maximum number of Pieces
    static String maxBoard = "";    // The corresponding board (of maximum # of Pieces)

    /**
     * Checks the combination of piece placement in the board and recursively checks all the combinations and stores
     * the combination with the maximum number of pieces.
     *
     * @param chessPiece            the chess piece to play
     * @param chp                   the character representing chess piece
     * @param rowPieceLoc           row index of piece
     * @param colPieceLoc           column index of piece
     * @param heightPieceLoc        height index of the piece
     */
    static void checkPieceCombinations(int size, int dimensions, char[][][] theBoard,
                                       ChessPiece chessPiece, char chp, int rowPieceLoc, int colPieceLoc, int heightPieceLoc) {
        // only if you see to store the highest number of Pieces -> SAVE THAT (update the last state)
        char x1 = theBoard[rowPieceLoc][colPieceLoc][heightPieceLoc];
        chessPiece.placePiece(chp, rowPieceLoc, colPieceLoc, heightPieceLoc);

        // if it has reached the end of board
        int temp = dimensions == 3 ? size: 1;
        if (rowPieceLoc == size - 1
                && (colPieceLoc == size - 1)
                && (heightPieceLoc == temp - 1)) {
            int current = chessPiece.countPiece(chp);
            if (current > max) {
                max = current;
                maxBoard = Board.boardToString(); // TODO: ASK ABHISHEK
            }
            return;
        }

        // iterate recursively
        if (colPieceLoc < size - 1 ) {
            checkPieceCombinations(size, dimensions, theBoard, chessPiece, chp, rowPieceLoc, colPieceLoc + 1, heightPieceLoc);
        } else if (rowPieceLoc < size - 1) {
            checkPieceCombinations(size, dimensions, theBoard, chessPiece, chp, rowPieceLoc + 1, 0, heightPieceLoc);
        } else if (temp != 1){
            checkPieceCombinations(size, dimensions, theBoard, chessPiece, chp, 0, 0, heightPieceLoc + 1);
        }

        //
        if (theBoard[rowPieceLoc][colPieceLoc][heightPieceLoc] == chp) {
            theBoard[rowPieceLoc][colPieceLoc][heightPieceLoc] = x1;
            if (colPieceLoc < size - 1) {
                checkPieceCombinations(size, dimensions, theBoard, chessPiece, chp, rowPieceLoc, colPieceLoc + 1, heightPieceLoc);
            }
        }
    }

    /**
     * The main program of the chess game
     */
    public static void main(String[] args) {
        Board.initBoard(args);

//        Bishop k = new Bishop(interface_impl.Board.getSize(), interface_impl.Board.getDimensions(), interface_impl.Board.getBoard());
//        Rook k = new Rook(interface_impl.Board.getSize(), interface_impl.Board.getDimensions(), interface_impl.Board.getBoard());
//        Knight k = new Knight(interface_impl.Board.getSize(), interface_impl.Board.getDimensions(), interface_impl.Board.getBoard());
        Queen k = new Queen(Board.getSize(), Board.getDimensions(), Board.getBoard());

        checkPieceCombinations(Board.getSize(), Board.getDimensions(), Board.getBoard(),
                k, k.chessPiece, 0, 0, 0);
        System.out.println("The max number of " + k.chessPieceName + " = " + max);
        System.out.println("The corresponding board = \n" + maxBoard);
    }
}
