package spring.stratego;

interface Rank {
    int getRank();
}

class Marshal implements Rank {
    @Override
    public int getRank(){
        return 10;
    }
}

class General implements Rank {
    @Override
    public int getRank(){
        return 9;
    }
}

class Colonel implements Rank {
    @Override
    public int getRank(){
        return 8;
    }
}

class Major implements Rank {
    @Override
    public int getRank(){
        return 7;
    }
}

class Captain implements Rank {
    @Override
    public int getRank(){
        return 6;
    }
}

class Lieutenants implements Rank {
    @Override
    public int getRank(){
        return 5;
    }
}

class Sergeants implements Rank {
    @Override
    public int getRank(){
        return 4;
    }
}

class Miners implements Rank {
    @Override
    public int getRank(){
        return 3;
    }
}

class Scouts implements Rank {
    @Override
    public int getRank(){
        return 2;
    }
}

class Spy implements Rank {
    @Override
    public int getRank(){
        return 1;
    }
}

class Bomb implements Rank {
    @Override
    public int getRank(){
        return 0;
    }
}

class Flag implements Rank {
    @Override
    public int getRank(){
        return 0;
    }
}