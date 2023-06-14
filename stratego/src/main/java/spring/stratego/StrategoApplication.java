package spring.stratego;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StrategoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrategoApplication.class, args);
		GameRoomManager gameRoomManager = new GameRoomManager();

        // Create a game room
		// roomID suppossedly auto generated  by database
        gameRoomManager.createGameRoom("room1");
		gameRoomManager.createGameRoomConcurrently("room2");
        gameRoomManager.createGameRoomConcurrently("room3");
        // System.out.println("Game Room Created - ID: " + gameRoom1.getRoomId());

		// Wait for a while to allow room creation tasks to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

		// Scanner s = new Scanner(System.in);
        // // // Join game room
		// System.out.println("*First player*\nEnter username:");
		// String username1 = s.next();
		// System.out.println("*Second player*\nEnter username:");
        // String username2 = s.next();
		// System.out.println("*Enter room code to join:");
        // String roomNo = s.next();

        String username1 = "player1";
        String username2 = "player2";
        String roomNo = "room1";

		boolean join1 = gameRoomManager.joinGameRoom(roomNo, username1);
        System.out.println("Player1 joined game room: " + join1);

        boolean join2 = gameRoomManager.joinGameRoom(roomNo, username2);
        System.out.println("Player2 joined game room: " + join2);

		GameRoom gameRoom1 = gameRoomManager.getGameRoom("room1");
        GameRoom gameRoom2 = gameRoomManager.getGameRoom("room2");
        GameRoom gameRoom3 = gameRoomManager.getGameRoom("room3");

		//check if the room is created or not
		if (gameRoom1 != null) {
            System.out.println("Game Room 1 ID: " + gameRoom1.getRoomId());
            System.out.println("Game Room 1 Players: " + gameRoom1.getPlayers());
        }

        if (gameRoom2 != null) {
            System.out.println("Game Room 2 ID: " + gameRoom2.getRoomId());
            System.out.println("Game Room 2 Players: " + gameRoom2.getPlayers());
        }

        if (gameRoom3 != null) {
            System.out.println("Game Room 3 ID: " + gameRoom3.getRoomId());
            System.out.println("Game Room 3 Players: " + gameRoom3.getPlayers());
        }

        // Shutdown the game room manager (terminate the thread pool)
        gameRoomManager.shutdown();
	}

}
