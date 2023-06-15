package spring.stratego;

abstract class Piece{
    // private Rank rank;
    private String player;

    public Piece(String player){
        this.player = player;
    }

    public String getPlayer(){
        return player;
    }
    
}

class BombPiece extends Piece {
    public BombPiece(String player) {
        super(player);
    }
}

class FlagPiece extends Piece{
    public FlagPiece(String player){
        super(player);
    }
}