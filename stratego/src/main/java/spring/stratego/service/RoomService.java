package spring.stratego.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.stratego.model.Room;
import spring.stratego.repository.RoomRepository;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room getRoomById(String roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    public void deleteRoom(String roomId) {
        roomRepository.deleteById(roomId);
    }
}
