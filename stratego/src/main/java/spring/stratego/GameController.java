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

    public void exitGame(){
        gameRoomManager.shutdown();
        System.out.println("Game exited");
        System.exit(0); //to forcefully terminate the application
    }

    public GameController(PlayerService playerService, GameRoomManager gameRoomManager) {
        this.playerService = playerService;
        this.gameRoomManager = gameRoomManager;
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
        session.setAttribute("player", player1);


        GameRoom[] gameRooms = gameRoomManager.getGameRooms();
        System.out.println("Game Rooms size: " + gameRooms.length);
        model.addAttribute("gameRooms", gameRooms);
        return "redirect:/lobby";
    }

    @GetMapping("/lobby")
    public String lobby(Model model, HttpSession session) {
        GameRoom[] gameRooms = gameRoomManager.getGameRooms();
        System.out.println("Game Rooms size: " + gameRooms.length);
        model.addAttribute("gameRooms", gameRooms);
        return "lobby";
    }

    /*
     * Create room endpoint
     * Input from client: takde
     * Output to client: sessionId
     */
    @PostMapping("/createGameRoom")
    // Get roomname input field from this POST request
    public String createGameRoom(@ModelAttribute("roomname") String roomname, Model model, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        System.out.println("Creating game room with name: " + roomname);
        Player currentPlayer = (Player) session.getAttribute("player");
        GameRoom gameRoom = gameRoomManager.createGameRoom(roomname);
    
        gameRoomManager.joinGameRoom(gameRoom, currentPlayer);

        session.setAttribute("roomId", gameRoom.getId());
        session.setAttribute("player", currentPlayer);
        session.setAttribute(roomname, gameRoom);
        return "redirect:/game/" + gameRoom.getId();
    }

    /*
     * Join room endpoint
     * Input from client: sessionId:
     * Output to client: boolean true/false
     */
    @PostMapping("/joinGameRoom")
    public String joinGameRoom(@ModelAttribute("roomId") String roomId, Model model, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        System.out.println("Joining game room with id: " + roomId);
        Player currentPlayer = (Player) session.getAttribute("player");
        GameRoom gameRoom = gameRoomManager.getGameRoom(roomId);
        if (gameRoom != null) {
            gameRoomManager.joinGameRoom(gameRoom, currentPlayer);
            session.setAttribute("roomId", gameRoom.getId());
            session.setAttribute("player", currentPlayer);
            session.setAttribute("roomname", gameRoom.getName());
            session.setAttribute("gameRoom", gameRoom);
            return "redirect:/game/" + gameRoom.getId();
        } else {
            return "error";
        }
    }

    /*
     * Game room endpoint
     * Input from client: roomID
     * Output to client: GameRoom state
     */
    @GetMapping("/game/{roomID}")
    public String gameRoom(Model model, @PathVariable String roomID, HttpSession session) {
        System.out.println("Room ID: " + roomID);

        Player currentPlayer = (Player) session.getAttribute("player");
        GameRoom gameRoom = gameRoomManager.getGameRoom(roomID);

        
        if (gameRoom.playerIsInRoom(currentPlayer)) {
            return "game";
        } else {
            // Handle invalid room ID
            return "notInRoom";
        }
    }

    @MessageMapping("/game/{sessionId}/move")
    @SendTo("/topic/game/{sessionId}")
    public String handleMoveMessage(@DestinationVariable String sessionId, MoveMessage moveMessage) {
        System.out.println("Received move message from session " + sessionId + " " + moveMessage);

        // convert int to String
        return "handleMoveMessage";
        
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
