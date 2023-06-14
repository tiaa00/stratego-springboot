package spring.stratego;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Players")
public class Player {

    @Id
    private String id;
    private String playerName;
    private String playerID;

    public Player(String playerName, String playerID) {
        this.playerName = playerName;
        this.playerID = playerID;
    }

    // Getters and setters
    // ...
}

