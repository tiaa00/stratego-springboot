package spring.stratego;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class ChatHandler {
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private Set<String> specificClients = new HashSet<>();

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public void handleChatMessage(ChatMessage message) {
        // Code to process the received chat message

        // Broadcast the message to specific clients
        for (String clientId : specificClients) {
            messagingTemplate.convertAndSendToUser(clientId, "/queue/messages", message);
        }
    }

}
