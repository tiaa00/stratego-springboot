package spring.stratego;

class Board {
    private static final int BOARD_SIZE = 10;
    private Piece[][] gameBoard;

    public Board() {
        this.gameBoard = new Piece[BOARD_SIZE][BOARD_SIZE];
    }

    public boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }

    public Piece getPieceAtCoordinate(int x, int y) {
        return gameBoard[x][y];
    }

    public Piece setPieceAtCoordinate(int x, int y, Piece piece) {
        return gameBoard[x][y] = piece;
    }
}

