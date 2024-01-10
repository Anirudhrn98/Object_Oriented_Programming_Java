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
    static void checkPieceCombinations(ChessPiece chessPiece, char chp, int rowPieceLoc, int colPieceLoc, int heightPieceLoc) {
        // only if you see to store the highest number of Pieces -> SAVE THAT (update the last state)
        char x1 = Board.theBoard[rowPieceLoc][colPieceLoc][heightPieceLoc];
        chessPiece.placePiece(chp, rowPieceLoc, colPieceLoc, heightPieceLoc);

        // if it has reached the end of board
        int temp = Board.dimensions == 3 ? Board.size: 1;
        if (rowPieceLoc == ChessPiece.MAX_SIZE - 1
                && (colPieceLoc == ChessPiece.MAX_SIZE - 1)
                && (heightPieceLoc == temp - 1)) {
            int current = chessPiece.countPiece(chp);
            if (current > max) {
                max = current;
                maxBoard = Board.boardToString();
            }
            return;
        }

        // iterate recursively
        if (colPieceLoc < ChessPiece.MAX_SIZE - 1 ) {
            checkPieceCombinations(chessPiece, chp, rowPieceLoc, colPieceLoc + 1, heightPieceLoc);
        } else if (rowPieceLoc < ChessPiece.MAX_SIZE - 1) {
            checkPieceCombinations(chessPiece, chp, rowPieceLoc + 1, 0, heightPieceLoc);
        } else if (temp != 1){
            checkPieceCombinations(chessPiece, chp, 0, 0, heightPieceLoc + 1);
        }

        //
        if (Board.theBoard[rowPieceLoc][colPieceLoc][heightPieceLoc] == chp) {
            Board.theBoard[rowPieceLoc][colPieceLoc][heightPieceLoc] = x1;
            if (colPieceLoc < ChessPiece.MAX_SIZE - 1) {
                checkPieceCombinations(chessPiece, chp, rowPieceLoc, colPieceLoc + 1, heightPieceLoc);
            }
        }
    }

    /**
     * The main program of the chess game
     */
    public static void main(String[] args) {
        Board.readArgs(args);
        Board.initTheBoard();
//        Bishop k = new Bishop(Board.size, Board.dimensions, Board.theBoard);
//        Rook k = new Rook(Board.size, Board.dimensions, Board.theBoard);
//        Knight k = new Knight(Board.size, Board.dimensions, Board.theBoard);
        Queen k = new Queen(Board.size, Board.dimensions, Board.theBoard);

        checkPieceCombinations(k, k.chessPiece, 0, 0, 0);
        System.out.println("The max number of " + k.chessPieceName + " = " + max);
        System.out.println("The corresponding board = \n" + maxBoard);
    }
}
