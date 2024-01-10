
public class Chess {
    int max = -1;            // Maximum number of Pieces
    String maxBoard = "";    // The corresponding board (of maximum # of Pieces)

    /**
     * places piece in the required index
     *
     * @param args     Arguments to initialize board
     */
    void run(String[] args) {
        Board.initBoard(args);

//        interface_impl.Bishop k = new interface_impl.Bishop(interface_impl.Board.getSize(), interface_impl.Board.getDimensions(), interface_impl.Board.getBoard());
//        interface_impl.Rook k = new interface_impl.Rook(interface_impl.Board.getSize(), interface_impl.Board.getDimensions(), interface_impl.Board.getBoard());
//        interface_impl.Knight k = new interface_impl.Knight(interface_impl.Board.getSize(), interface_impl.Board.getDimensions(), interface_impl.Board.getBoard());
        Queen k = new Queen(Board.getSize(), Board.getDimensions(), Board.getBoard());

        checkPieceCombinations(Board.getSize(), Board.getDimensions(), Board.getBoard(),
                k, k.chessPiece, 0, 0, 0);
        System.out.println("The max number of " + k.chessPieceName + " = " + max);
        System.out.println("The corresponding board = \n" + maxBoard);
    }

    /**
     * places piece in the required index
     *
     * @param chessPiece     Represents symbol of the chess piece
     * @param rowPieceLoc    row index of piece
     * @param colPieceLoc    column index of piece
     * @param heightPieceLoc height index of piece
     * @return boolean              true if it is placed/ false if it is not
     */
    boolean placePiece(Moveable chesspiece, char[][][] theBoard,
                       char chessPiece, int rowPieceLoc, int colPieceLoc, int heightPieceLoc) {
        if (theBoard[rowPieceLoc][colPieceLoc][heightPieceLoc] != ' '
                && chesspiece.isAbleToMove(rowPieceLoc, colPieceLoc, heightPieceLoc, chessPiece)) {
            theBoard[rowPieceLoc][colPieceLoc][heightPieceLoc] = chessPiece;
            return true;
        }
        return false;
    }

    /**
     * Checks the combination of piece placement in the board and recursively checks all the combinations and stores
     * the combination with the maximum number of pieces.
     *
     * @param chessPiece     the chess piece to play
     * @param chp            the character representing chess piece
     * @param rowPieceLoc    row index of piece
     * @param colPieceLoc    column index of piece
     * @param heightPieceLoc height index of the piece
     */
    public void checkPieceCombinations(int size, int dimensions, char[][][] theBoard,
                                       Moveable chessPiece, char chp, int rowPieceLoc, int colPieceLoc, int heightPieceLoc) {

        // only if you see to store the highest number of Pieces -> SAVE THAT (update the last state)
        char x1 = theBoard[rowPieceLoc][colPieceLoc][heightPieceLoc];
        placePiece(chessPiece, theBoard, chp, rowPieceLoc, colPieceLoc, heightPieceLoc);

        // if it has reached the end of board
        int temp = dimensions == 3 ? size : 1;
        if (rowPieceLoc == size - 1
                && (colPieceLoc == size - 1)
                && (heightPieceLoc == temp - 1)) {
            int current = Board.countPiece(chp);
            if (current > max) {
                max = current;
                maxBoard = Board.boardToString(); // TODO: ASK ABHISHEK
            }
            return;
        }

        // iterate recursively
        if (colPieceLoc < size - 1) {
            checkPieceCombinations(size, dimensions, theBoard, chessPiece, chp, rowPieceLoc, colPieceLoc + 1, heightPieceLoc);
        } else if (rowPieceLoc < size - 1) {
            checkPieceCombinations(size, dimensions, theBoard, chessPiece, chp, rowPieceLoc + 1, 0, heightPieceLoc);
        } else if (temp != 1) {
            checkPieceCombinations(size, dimensions, theBoard, chessPiece, chp, 0, 0, heightPieceLoc + 1);
        }

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
        Chess c = new Chess();
        c.run(args);
    }
}