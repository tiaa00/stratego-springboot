package spring.stratego;

import java.util.ArrayList;
import java.util.List;

// GameRoom class representing a game room
class GameRoom {
    private String roomId;
    private List<String> players;
    private String gameState;

    public GameRoom(String roomId) {
        this.roomId = roomId;
        this.players = new ArrayList<>();
        this.gameState = "Waiting"; // Initial game state
    }

    public String getRoomId() {
        return roomId;
    }

    public List<String> getPlayers() {
        return players;
    }

    public String getGameState() {
        // need to change the game state.
        return gameState;
    }

    public void addPlayer(String player) {
        players.add(player);
    }
}
