package spring.stratego;

import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import spring.stratego.model.Chat;
import spring.stratego.model.Player;
import spring.stratego.service.ChatService;
import spring.stratego.service.PlayerService;

@SpringBootApplication
@Controller
public class StrategoApplication {

    private static final GameRoomManager gameRoomManager = new GameRoomManager();
	public static void main(String[] args) {
        // Create and run Spring Boot application
        ConfigurableApplicationContext context = SpringApplication.run(StrategoApplication.class, args);
        PlayerService playerService = context.getBean(PlayerService.class);
        ChatService chatService = context.getBean(ChatService.class);

        //CRUD
        // Create and insert a player
        createPlayer(playerService, "Munir", "3");
        // Create a chat
        createChat(chatService, "Hello, how are you?", "1");
        // Retrieve Chat By ID
        getChatById(chatService, "1");
        // Retrieve a player by ID
        getPlayerById(playerService, "3");

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

    public static void createPlayer(PlayerService playerService, String name, String id) {
        Player player = new Player();
        player.setName(name);
        player.setId(id);
        Player createdPlayer = playerService.createPlayer(player);
        System.out.println("Created Player: " + createdPlayer);
    }

    private static void createChat(ChatService chatService, String chatLog, String chatId) {
        Chat chat = new Chat();
        chat.setChatLog(chatLog);
        chat.setChatId(chatId);
        Chat createdChat = chatService.createChat(chat);
        System.out.println("Created Chat: " + createdChat);
    }

    private static void getChatById(ChatService chatService, String chatId) {
        Chat chat = chatService.getChatById(chatId);
        if (chat != null) {
            System.out.println("Retrieved Chat: " + chat);
        } else {
            System.out.println("Chat not found.");
        }
    }
    private static void getPlayerById(PlayerService playerService, String playerId) {
        Player retrievedPlayer = playerService.getPlayerById(playerId);
        if (retrievedPlayer != null) {
            System.out.println("Retrieved Player: " + retrievedPlayer.getName());
        } else {
            System.out.println("Player not found.");
        }
    }

    // @PostMapping("/submit")
    // public String handleUserSubmit(@RequestParam String username, RedirectAttributes redirectAttributes) {
    //     // You can now use this username for whatever you want.
    //     // System.out.println(username);
    //     // Add the username to the redirect attributes
    //     redirectAttributes.addFlashAttribute("username", username);

    //     // Redirect to the lobby page
    //     return "redirect:/lobby";
    // }

    @PostMapping("/lobby")
    public String showLobby(Model model, @RequestParam String username, HttpSession session) {
        // method to store into database.
        session.setAttribute("username", username);
        model.addAttribute("username", username);
        return "lobby";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "index";
    }

    //optional to use(?)
    public static void createGameRooms(){
        // Create a game room
		// roomID suppossedly auto generated  by database
        gameRoomManager.createGameRoom("room1");
		// gameRoomManager.createGameRoom("room2");
        // gameRoomManager.createGameRoom("room3");
    }

    //optional to use(?)
    public static void joinGameRoomAndPlay(String roomID, String player1, String player2){
        //to check the player is join the room or not
        boolean join1 = gameRoomManager.joinGameRoom(roomID, player1);
        boolean join2 = gameRoomManager.joinGameRoom(roomID, player2);
        if(join1 && join2){
            GameRoom games = new GameRoom(roomID);
            System.out.println(player1+ " "+ player2+ " join "+ roomID);
        }else{
            System.out.println("not enough player....wait");
        }
    }

    @PostMapping("/exit")
    public void handleExitButton(){
        exitGame();
    }

    public static void exitGame(){
        gameRoomManager.shutdown();
        System.out.println("Game exited");
        System.exit(0); //to forcefully terminate the application
    }
}
