package spring.stratego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import spring.stratego.model.Chat;
import spring.stratego.model.Player;
import spring.stratego.service.ChatService;
import spring.stratego.service.PlayerService;

@SpringBootApplication
@Controller
public class StrategoApplication {

    private final PlayerService playerService;

    private static final GameRoomManager gameRoomManager = new GameRoomManager();

    public StrategoApplication(PlayerService playerService) {
        this.playerService = playerService;
    }

    public static void main(String[] args) {
        // Create and run Spring Boot application
        SpringApplication.run(StrategoApplication.class, args);
	}

    private void createPlayer(String name, String id) {
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

    @PostMapping("/lobby")
    public String showLobby(Model model, @RequestParam String username, HttpSession session) {
        // method to store into database.
        createPlayer(username, session.toString());
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

    @GetMapping("/game")
    public String gameRoom(Model model) {
        return "game";
    }
}
