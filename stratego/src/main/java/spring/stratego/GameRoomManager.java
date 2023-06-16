package spring.stratego;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import spring.stratego.model.Player;

@Service
class GameRoomManager {
    private Map<String, GameRoom> gameRooms;
    private ExecutorService roomCreationExecutor;

    public GameRoomManager() {
        this.gameRooms = new HashMap<>();
        this.roomCreationExecutor = Executors.newCachedThreadPool();
    }

    public GameRoom createGameRoom(String roomId) {
        GameRoom gameRoom = new GameRoom(roomId);
        gameRooms.put(roomId, gameRoom);
        return gameRoom;
    }

    public GameRoom getGameRoom(String roomId) {
        return gameRooms.get(roomId);
    }

    public boolean joinGameRoom(String roomId, Player player) {
        GameRoom gameRoom = gameRooms.get(roomId);
        if (gameRoom != null && gameRoom.getPlayers().size() < 2) {
            gameRoom.addPlayer(player);
            return true;
        }
        return false;
    }

    public void shutdown() {
        roomCreationExecutor.shutdown();
    }

    public void createGameRoomConcurrently(String roomId) {
        roomCreationExecutor.submit(() -> {
            createGameRoom(roomId);
            System.out.println("Game Room Created - ID: " + roomId);
        });
    }
}
