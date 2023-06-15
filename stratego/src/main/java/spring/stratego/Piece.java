package spring.stratego;

interface Piece {
    public abstract int getRank();
    void setTotalPiece(int total);
    int getTotalPiece();
    Object getPlayer();
}

class Marshal implements Piece {
    private int rank;
    private int totalPiece;
    private Object player;
    // GameRoom player =
    public Marshal(Object player){
        this.rank=10;
        this.totalPiece=1;
        this.player = player;
    }

    public int getRank(){
        return rank;
    }

    public void setTotalPiece(int decrement){
        this.totalPiece = this.totalPiece - decrement;
    }

    public int getTotalPiece(){
        return totalPiece;
    }

    public Object getPlayer(){
        return player;
    }
}

class General implements Piece {
    private int rank;
    private int totalPiece;
    private Object player;
    public General(Object player){
        this.rank=9;
        this.totalPiece=1;
        this.player = player;
    }
    @Override
    public int getRank(){
        return rank;
    }

    public void setTotalPiece(int decrement){
        this.totalPiece = this.totalPiece - decrement;
    }

    public int getTotalPiece(){
        return totalPiece;
    }

    public Object getPlayer(){
        return player;
    }
}

class Colonel implements Piece {
    private int rank;
    private int totalPiece;
    private Object player;
    public Colonel(Object player){
        this.rank=8;
        this.totalPiece=2;
        this.player = player;
    }
    @Override
    public int getRank(){
        return rank;
    }

    public void setTotalPiece(int decrement){
        this.totalPiece = this.totalPiece - decrement;
    }

    public int getTotalPiece(){
        return totalPiece;
    }

    public Object getPlayer(){
        return player;
    }
}

class Major implements Piece {
    private int rank;
    private int totalPiece;
    private Object player;
    public Major(Object player){
        this.rank=7;
        this.totalPiece=3;
        this.player = player;
    }
    @Override
    public int getRank(){
        return rank;
    }

    public void setTotalPiece(int decrement){
        this.totalPiece = this.totalPiece - decrement;
    }

    public int getTotalPiece(){
        return totalPiece;
    }

    public Object getPlayer(){
        return player;
    }
}

class Captain implements Piece {
    private int rank;
    private int totalPiece;
    private Object player;
    public Captain(Object player){
        this.rank=6;
        this.totalPiece=4;
        this.player = player;
    }
    @Override
    public int getRank(){
        return rank;
    }

    public void setTotalPiece(int decrement){
        this.totalPiece = this.totalPiece - decrement;
    }

    public int getTotalPiece(){
        return totalPiece;
    }
    public Object getPlayer(){
        return player;
    }
}

class Lieutenants implements Piece {
    private int rank;
    private int totalPiece;
    private Object player;
    public Lieutenants(Object player){
        this.rank=5;
        this.totalPiece=4;
        this.player = player;
    }
    @Override
    public int getRank(){
        return rank;
    }

    public void setTotalPiece(int decrement){
        this.totalPiece = this.totalPiece - decrement;
    }

    public int getTotalPiece(){
        return totalPiece;
    }
    public Object getPlayer(){
        return player;
    }
}

class Sergeants implements Piece {
    private int rank;
    private int totalPiece;
    private Object player;
    public Sergeants(Object player){
        this.rank=4;
        this.totalPiece=4;
        this.player = player;
    }
    @Override
    public int getRank(){
        return rank;
    }

    public void setTotalPiece(int decrement){
        this.totalPiece = this.totalPiece - decrement;
    }

    public int getTotalPiece(){
        return totalPiece;
    }
    public Object getPlayer(){
        return player;
    }
}

class Miners implements Piece {
    private int rank;
    private int totalPiece;
    private Object player;
    public Miners(){
        this.rank=3;
        this.totalPiece=5;
    }
    @Override
    public int getRank(){
        return rank;
    }

    public void setTotalPiece(int decrement){
        this.totalPiece = this.totalPiece - decrement;
    }

    public int getTotalPiece(){
        return totalPiece;
    }
    public Object getPlayer(){
        return player;
    }
}

class Scouts implements Piece {
    private int rank;
    private int totalPiece;
    private Object player;
    public Scouts(Object player){
        this.rank=2;
        this.totalPiece=8;
        this.player = player;
    }
    @Override
    public int getRank(){
        return rank;
    }

    public void setTotalPiece(int decrement){
        this.totalPiece = this.totalPiece - decrement;
    }

    public int getTotalPiece(){
        return totalPiece;
    }
    public Object getPlayer(){
        return player;
    }
}

class Spy implements Piece {
    private int rank;
    private int totalPiece;
    private Object player;
    public Spy(Object player){
        this.rank=1;
        this.totalPiece=1;
        this.player = player;
    }
    @Override
    public int getRank(){
        return rank;
    }

    public void setTotalPiece(int decrement){
        this.totalPiece = this.totalPiece - decrement;
    }

    public int getTotalPiece(){
        return totalPiece;
    }
    public Object getPlayer(){
        return player;
    }
}

class Bomb implements Piece {
    private int rank;
    private int totalPiece;
    private Object player;
    public Bomb(Object player){
        this.rank=20;
        this.totalPiece=6;
        this.player = player;
    }
    @Override
    public int getRank(){
        return rank;
    }

    public void setTotalPiece(int decrement){
        this.totalPiece = this.totalPiece - decrement;
    }

    public int getTotalPiece(){
        return totalPiece;
    }
    public Object getPlayer(){
        return player;
    }
}

class Flag implements Piece {
    private int rank;
    private int totalPiece;
    private Object player;
    public Flag(Object player){
        this.rank=0;
        this.totalPiece=1;
        this.player = player;
    }
    @Override
    public int getRank(){
        return rank;
    }

    public void setTotalPiece(int decrement){
        this.totalPiece = this.totalPiece - decrement;
    }

    public int getTotalPiece(){
        return totalPiece;
    }
    public Object getPlayer(){
        return player;
    }
}