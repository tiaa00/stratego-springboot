package spring.stratego.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import spring.stratego.model.Chat;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
    // Custom query methods, if needed
}
