package spring.stratego;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import spring.stratego.model.Player;

// GameRoom class representing a game room
class GameRoom {
    private String roomId;
    private List<Player> players;
    private String gameState;
    private Lock turnLock; //to ensure only one thread can modify it at a time
    private AtomicInteger turn;
    private Board board;

    public GameRoom(String roomId) {
        this.roomId = roomId;
        this.players = new ArrayList<>();
        this.gameState = "Waiting"; // Initial game state
        this.turnLock = new ReentrantLock();
        this.turn = new AtomicInteger(0);
        this.board = new Board();
    }

    public String getRoomId() {
        return roomId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getGameState() {
        // need to change the game state.
        return gameState;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean isPlayerturn(Player player){
        return players.indexOf(player) == turn.get() %2;
    }

    public void makeMove(Player player, int fromX, int fromY, int destX, int destY){
        turnLock.lock();
        try{
            if(isPlayerturn(player)){
                System.out.println(player+ " turn");
            }

            boolean isValidMove = validateMove(fromX, fromY, destX, destY);

            if(isValidMove){
                updateGameState(fromX, fromY, destX, destY);
            }

            //increment the turn counter
            turn.incrementAndGet();
            // Notify the opponent that it's their turn
            String opponent = players.get((turn.get() % 2 + 1) % 2).getName();
            System.out.println(opponent + "'s turn.");
        }finally{
            //release the turnLock after accessing or modifying the turn varible
            turnLock.unlock();
        }
    }

    private boolean validateMove(int fromX, int fromY, int destX, int destY) {
        //validate the move based on the game rules
        //Check if the source and destination coordinates are within the game board bounds
        if (!board.isValidCoordinate(fromX, fromY) || !board.isValidCoordinate(destX, destY)) {
            System.out.println("Invalid coordinates");
            return false;
        }

        // Check if the piece at the source coordinates belongs to the current player
        Piece sourcePiece = board.getPieceAtCoordinate(fromX, fromY);
        if (sourcePiece == null || !sourcePiece.getPlayer().equals(players.get(turn.get() % 2))) {
            System.out.println("Invalid source piece");
            return false;
        }

        if (!isAdjacent(fromX, fromY, destX, destY)) {
            return false; //cannot move
        }

        // Check if the destination square is already occupied
        if (board.getPieceAtCoordinate(destX, destY) != null) {
            return false;
        }

        return true;
    }

    private boolean isAdjacent(int x1, int y1, int x2, int y2) {
        // Check if the coordinates (x1, y1) and (x2, y2) are adjacent
        // Two squares are adjacent if they are horizontally, vertically, or diagonally adjacent
        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);
        return (dx == 0 && dy == 1) || (dx == 1 && dy == 0) || (dx == 1 && dy == 1);
    }

    private synchronized Board updateGameState(int fromX, int fromY, int destX, int destY) {
    // Move the piece from the source square to the destination square
    Piece sourcePiece = board.getPieceAtCoordinate(fromX, fromY);
    Piece destPiece = board.getPieceAtCoordinate(destX, destY);

    //if the destPiece is vacant
    if(destPiece==null){
        board.setPieceAtCoordinate(destX, destY, sourcePiece); //moved the piece
        board.setPieceAtCoordinate(fromX, fromY, null);
    } 
    
    // Check if the destination square contains a bomb piece
    if (destPiece instanceof Bomb) {
        // Remove the piece from the game board
        board.setPieceAtCoordinate(destX, destY, null);
        board.setPieceAtCoordinate(fromX, fromY, null);
        sourcePiece.setTotalPiece(1);
        destPiece.setTotalPiece(1);
        // System.out.println("Player encountered a bomb! Piece removed.");
    }
    
    // Check if the destination square contains a flag piece
    if (destPiece instanceof Flag) {
        // The player has won the game
        // System.out.println( getplayer+ " captured the flag! Game over. Player wins!");
        destPiece.setTotalPiece(1); 
        gameState = "Game Over";
    }

    //check rank between the pieces
    if(sourcePiece.getRank()>destPiece.getRank()){
        board.setPieceAtCoordinate(destX, destY, sourcePiece); //moved the piece
        board.setPieceAtCoordinate(fromX, fromY, null); //vacant the source coordinate
        destPiece.setTotalPiece(1); 
    }else if(sourcePiece.getRank()<destPiece.getRank()){ // opponent win-> player lost their piece
        board.setPieceAtCoordinate(fromX, fromY, null);
        sourcePiece.setTotalPiece(1);
    }else{ //both of the pieces from the same rank
        board.setPieceAtCoordinate(fromX, fromY, null);
        board.setPieceAtCoordinate(destX, destY, null);
        sourcePiece.setTotalPiece(1);
        destPiece.setTotalPiece(1);
    }

    return board;
}

}
