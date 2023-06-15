package spring.stratego.online;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class ClientHandler extends TextWebSocketHandler {

    private int clientCount = 1;

    private void onReceiveMessage(@Payload String message) {
        System.out.println("Received message: " + message);
    }

    private void sendMessage(WebSocketSession session, String message) throws IOException {
        session.sendMessage(new TextMessage(message));
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        onReceiveMessage(payload);
        System.out.println("Session URI: " + session.getUri());
        System.out.println("Session ID: " + session.getId());
        try {
            String messageString = "Hello, Client " + clientCount++ + "!";
            sendMessage(session, messageString);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("An error occurred while handling a WebSocket message: " + exception.getMessage());
        session.close(CloseStatus.SERVER_ERROR);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Connection closed: " + status.getReason());
    }

}
