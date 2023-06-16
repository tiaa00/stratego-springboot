package spring.stratego;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import spring.stratego.model.Player;

@Controller
public class GameSession {
    GameRoom gameRoom;

    private int gameRoomCounter = 0;

    @MessageMapping("/game/{sessionId}/move")
    // @MessageMapping("/game")
    @SendTo("/topic/game/{sessionId}")
    public String handleMoveMessage(@DestinationVariable String sessionId, MoveMessage moveMessage) {
        System.out.println("Received move message from session " + sessionId + " " + moveMessage);

        // convert int to String
        return String.valueOf("Move " + gameRoomCounter++);
        
    }

    /*
     * Create room endpoint
     * Input from client: takde
     * Output to client: sessionId
     */
    @PostMapping("/createGameRoom")
    public String createRoom(@ModelAttribute("player") String player, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        int roomId = gameRoomCounter++;
        gameRoom = new GameRoom(String.valueOf(roomId));
        session.setAttribute("roomId", roomId);
        session.setAttribute("player", player);
        return "redirect:/game/" + roomId;
    }

    /*
     * Join room endpoint
     * Input from client: sessionId:
     * Output to client: boolean true/false
     */

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
