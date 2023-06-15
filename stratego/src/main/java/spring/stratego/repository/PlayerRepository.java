package spring.stratego.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.stratego.model.Player;

public interface PlayerRepository extends MongoRepository<Player, String> {
    // Additional custom query methods can be defined here if needed
}
