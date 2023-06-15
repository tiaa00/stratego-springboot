package spring.stratego.service;

import org.springframework.stereotype.Service;
import spring.stratego.model.Chat;
import spring.stratego.repository.ChatRepository;

import java.util.List;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    public Chat getChatById(String chatId) {
        return chatRepository.findById(chatId).orElse(null);
    }

    public void deleteChat(String chatId) {
        chatRepository.deleteById(chatId);
    }
}
