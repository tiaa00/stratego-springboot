package spring.stratego;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import spring.stratego.model.Player;

@Service
class GameRoomManager {
    private Map<String, GameRoom> gameRoomMap;
    private ExecutorService roomCreationExecutor;

    public GameRoomManager() {
        this.gameRoomMap = new HashMap<>();
        this.roomCreationExecutor = Executors.newCachedThreadPool();
    }

    public GameRoom createGameRoom(String name) {
        String roomId = String.valueOf(gameRoomMap.size());
        GameRoom gameRoom = new GameRoom(roomId, name);
        gameRoomMap.put(roomId, gameRoom);
        return gameRoom;
    }

    public GameRoom getGameRoom(String roomId) {
        return gameRoomMap.get(roomId);
    }

    public boolean joinGameRoom(GameRoom gameRoom, Player player) {
        // GameRoom gameRoomTemp = gameRoomMap.get(gameRoom.getId());
        if (gameRoom != null && gameRoom.getPlayers().size() < 2) {
            gameRoom.addPlayer(player);
            return true;
        } else {
            throw new IllegalArgumentException("Room is full");
        }
    }

    public void shutdown() {
        roomCreationExecutor.shutdown();
    }

    public GameRoom[] getGameRooms() {
        return gameRoomMap.values().toArray(new GameRoom[0]);
    }

    public int getGameRoomCount() {
        return gameRoomMap.size();
    }

    public void createGameRoomConcurrently(String roomId) {
        roomCreationExecutor.submit(() -> {
            createGameRoom(roomId);
            System.out.println("Game Room Created - ID: " + roomId);
        });
    }
}
