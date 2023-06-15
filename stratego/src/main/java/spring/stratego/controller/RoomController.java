package spring.stratego.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.stratego.model.Room;
import spring.stratego.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room createdRoom = roomService.createRoom(room);
        if (createdRoom != null) {
            return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable String id, @RequestBody Room room) {
        Room updatedRoom = roomService.getRoomById(id);
        if (updatedRoom != null) {
            updatedRoom.setRoomID(room.getRoomID());
            updatedRoom.setPlayer1ID(room.getPlayer1ID());
            updatedRoom.setPlayer2ID(room.getPlayer2ID());
            updatedRoom.setChatID(room.getChatID());
            Room savedRoom = roomService.updateRoom(updatedRoom);
            return new ResponseEntity<>(savedRoom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable String id) {
        Room room = roomService.getRoomById(id);
        if (room != null) {
            return new ResponseEntity<>(room, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable String id) {
        Room room = roomService.getRoomById(id);
        if (room != null) {
            roomService.deleteRoom(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
