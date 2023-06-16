package spring.stratego;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

@Controller
public class ChatSession {

    private ConcurrentHashMap<String, ChatMessage[]> chatMessagesMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Integer> messageCounterMap = new ConcurrentHashMap<>();
    
    /*
     * Chat endpoint
     * Input from client: chatContent
     * Output to client: chatContent
     */
    @MessageMapping("/game/{sessionId}/chat")
    @SendTo("/topic/chat/{sessionId}")
    public ChatMessage[] handleChatMessage(@DestinationVariable String sessionId, ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("Received chat message from sessionId " + sessionId + " HttpSessionId " + headerAccessor.getSessionId() +  " " + chatMessage);

        ChatMessage[] chatMessages;
        int messageCounter;

        if (!chatMessagesMap.containsKey(sessionId)) {
            chatMessages = new ChatMessage[100];
            messageCounter = 0;

            chatMessagesMap.put(sessionId, chatMessages);
            messageCounterMap.put(sessionId, messageCounter);
        } else {
            messageCounter = messageCounterMap.get(sessionId);
            chatMessages = chatMessagesMap.get(sessionId);
        }
        
        chatMessages[messageCounter++] = chatMessage;
        ChatMessage[] chatMessagesTemp = new ChatMessage[messageCounter];
        System.arraycopy(chatMessages, 0, chatMessagesTemp, 0, messageCounter);

        // Write
        chatMessagesMap.put(sessionId, chatMessages);
        messageCounterMap.put(sessionId, messageCounter);

        return chatMessagesTemp;

    }

}
