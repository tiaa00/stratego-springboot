package spring.stratego.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rooms")
public class Room {
    @Id
    private String roomID;
    private String name;
    private Player player1;
    private Player player2;
    public static final int MAX_PLAYERS = 2;

    public Room(String roomID, String name, Player player1, Player player2) {
        this.roomID = roomID;
        this.name = name;
        this.player1 = player1;
        this.player2 = player2;
        this.name = name;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getName() {
        return name;
    }

    public void setName(String roomName) {
        this.name = roomName;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getNumPlayers() {
        if (player1 == null && player2 == null) {
            return 0;
        } else if (player1 != null && player2 == null) {
            return 1;
        } else if (player1 == null && player2 != null) {
            return 1;
        } else {
            return 2;
        }
    }

    // toString method

    @Override
    public String toString() {
        return "Room{" +
                ", roomID='" + roomID + '\'' +
                ", name='" + name + '\'' +
                ", player1='" + player1.getName() + '\'' +
                ", player2='" + player1.getName() + '\'' +
                '}';
    }
}

