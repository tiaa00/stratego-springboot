package spring.stratego;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerCRUD {

    private final MongoCollection<Document> playerCollection;
    

    @Autowired
    public PlayerCRUD(MongoDatabase database) {
        playerCollection = database.getCollection("Players");
    }

    public void insertPlayer(String playerName, String playerId) {
        Document playerDocument = new Document()
                .append("player_name", playerName)
                .append("player_id", playerId);

        playerCollection.insertOne(playerDocument);
    }

    public List<Document> findAllPlayers() {
        List<Document> players = new ArrayList<>();

        try (MongoCursor<Document> cursor = playerCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document player = cursor.next();
                players.add(player);
            }
        }

        return players;
    }

    public Document findPlayerByName(String playerName) {
        return playerCollection.find(new Document("player_name", playerName)).first();
    }

    public void updatePlayer(String playerName, String newIpAddress, String newRoomID) {
        Document filter = new Document("player_name", playerName);
        Document update = new Document("$set", new Document("ip_address", newIpAddress).append("room_id", newRoomID));

        playerCollection.updateOne(filter, update);
    }

    public void deletePlayer(String playerName) {
        playerCollection.deleteOne(new Document("player_name", playerName));
    }
}
