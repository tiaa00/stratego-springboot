package spring.stratego;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameSession {

    private int counter = 0;

    @MessageMapping("/game/{sessionId}/move")
    // @MessageMapping("/game")
    @SendTo("/topic/game/{sessionId}")
    public String handleMoveMessage(@DestinationVariable String sessionId, MoveMessage moveMessage) {
        System.out.println("Received move message from session " + sessionId + " " + moveMessage);

        // convert int to String
        return String.valueOf("Move " + counter++);
        
    }

    /*
     * Create room endpoint
     * Input from client: takde
     * Output to client: sessionId
     */


    /*
     * Join room endpoint
     * Input from client: sessionId:
     * Output to client: boolean true/false
     */

    /*
     * Chat endpoint
     * Input from client: chatContent
     * Output to client: chatContent
     */
    @MessageMapping("/game/{sessionId}/chat")
    @SendTo("/topic/game/{sessionId}")
    public ChatMessage handleChatMessage(@DestinationVariable String sessionId, ChatMessage chatMessage) {
        System.out.println("Received chat message from session " + sessionId + " " + chatMessage);

        return chatMessage;
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
