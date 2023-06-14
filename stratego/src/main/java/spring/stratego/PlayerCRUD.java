package spring.stratego;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class PlayerCRUD {

    private static final String PLAYER_COLLECTION_NAME = "Players";

    private MongoCollection<Document> playercollection;

    public PlayerCRUD(MongoDatabase database) {
        playercollection = database.getCollection(PLAYER_COLLECTION_NAME);
    }

    public void insertPlayer(String playerName,String playerID) {
        Document playerDocument = new Document()
                .append("player_name", playerName)
                .append("player_id", playerID);

        playercollection.insertOne(playerDocument);
    }

    public List<Document> findAllPlayers() {
        List<Document> players = new ArrayList<>();

        try (MongoCursor<Document> cursor = playercollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document player = cursor.next();
                players.add(player);
            }
        }

        return players;
    }

    public Document findPlayerByName(String playerName) {
        return playercollection.find(new Document("player_name", playerName)).first();
    }

    public void updatePlayer(String playerName, String newIpAddress, String newRoomID) {
        Document filter = new Document("player_name", playerName);
        Document update = new Document("$set", new Document("ip_address", newIpAddress).append("room_id", newRoomID));

        playercollection.updateOne(filter, update);
    }

    public void deletePlayer(String playerName) {

        playercollection.deleteOne(new Document("player_name", playerName));
    }
}

