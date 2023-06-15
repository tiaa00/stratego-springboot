package spring.stratego.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rooms")
public class Room {
    @Id
    private String id;
    private String roomID;
    private String player1ID;
    private String player2ID;
    private String chatID;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getPlayer1ID() {
        return player1ID;
    }

    public void setPlayer1ID(String player1ID) {
        this.player1ID = player1ID;
    }

    public String getPlayer2ID() {
        return player2ID;
    }

    public void setPlayer2ID(String player2ID) {
        this.player2ID = player2ID;
    }

    public String getChatID() {
        return chatID;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }

    // toString method

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", roomID='" + roomID + '\'' +
                ", player1ID='" + player1ID + '\'' +
                ", player2ID='" + player2ID + '\'' +
                ", chatID='" + chatID + '\'' +
                '}';
    }
}

