package spring.stratego;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import spring.stratego.model.Chat;
import spring.stratego.model.Player;
import spring.stratego.model.Room;
import spring.stratego.service.ChatService;
import spring.stratego.service.PlayerService;

@Controller
public class GameController {

    private final PlayerService playerService;
    private final GameRoomManager gameRoomManager;

    private int gameRoomCounter = 0;

    public void exitGame(){
        gameRoomManager.shutdown();
        System.out.println("Game exited");
        System.exit(0); //to forcefully terminate the application
    }

    public GameController(PlayerService playerService, GameRoomManager gameRoomManager) {
        this.playerService = playerService;
        this.gameRoomManager = gameRoomManager;
    }

    //optional to use(?)
    public void createGameRooms(){
        // Create a game room
        // roomID suppossedly auto generated  by database
        gameRoomManager.createGameRoom("room1");
        // gameRoomManager.createGameRoom("room2");
        // gameRoomManager.createGameRoom("room3");
    }
    
    //optional to use(?)
    public void joinGameRoomAndPlay(String roomID, Player player1, Player player2){
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

    private Player createPlayer(String name, String id) {
        Player player = new Player();
        player.setName(name);
        player.setId(id);
        Player createdPlayer = playerService.createPlayer(player);
        System.out.println("Created Player: " + createdPlayer);
        return createdPlayer;
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

    /*
     * ENDPOINT DEFINITIONS
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "index";
    }

    @PostMapping("/exit")
    public void handleExitButton(){
        exitGame();
    }

    @PostMapping("/createPlayer")
    public String createPlayer(Model model, @RequestParam String username, HttpSession session) {
        // method to store into database.
        Player player1 = createPlayer(username, session.toString());
        session.setAttribute("username", username);
        model.addAttribute("player", player1);
        Room[] rooms = {
            new Room("0", "testName", player1, player1),
            new Room("1", "testName", player1, null),
            new Room("2", "testName", player1, null),
        };
        model.addAttribute("rooms", rooms);
        return "lobby";
    }

    /*
     * Create room endpoint
     * Input from client: takde
     * Output to client: sessionId
     */
    @PostMapping("/createGameRoom")
    public String createGameRoom(@ModelAttribute("player") String player, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        int roomId = gameRoomCounter++;
        GameRoom gameRoom = new GameRoom(String.valueOf(roomId));
        session.setAttribute("roomId", roomId);
        session.setAttribute("player", player);
        return "redirect:/game/" + roomId;
    }

    /*
     * Join room endpoint
     * Input from client: sessionId:
     * Output to client: boolean true/false
     */
    @PostMapping("/joinGameRoom")

    /*
     * Game room endpoint
     * Input from client: roomID
     * Output to client: GameRoom state
     */
    @GetMapping("/game/{roomID}")
    public String gameRoom(Model model, @PathVariable String roomID, HttpSession session) {
        System.out.println("Room ID: " + roomID);
        
        if (roomID != null) {
            // Add model attributes needed for template 
            return "game";
        } else {
            // Handle invalid room ID
            return "error";
        }
    }

    @MessageMapping("/game/{sessionId}/move")
    @SendTo("/topic/game/{sessionId}")
    public String handleMoveMessage(@DestinationVariable String sessionId, MoveMessage moveMessage) {
        System.out.println("Received move message from session " + sessionId + " " + moveMessage);

        // convert int to String
        return String.valueOf("Move " + gameRoomCounter++);
        
    }

    /*
     * GameBoard endpoint
     * Input from client: GameBoard state
     * Output to client: GameBoard state
     */
    @MessageMapping("/game/{sessionId}/board")
    @SendTo("/topic/game/{sessionId}")
    public Board handleBoardMessage(@DestinationVariable String sessionId, Board board) {
        System.out.println("Received board message from session " + sessionId + " " + board);

        return board;
    }
    
    
}
