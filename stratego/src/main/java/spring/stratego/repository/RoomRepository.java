package spring.stratego.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.stratego.model.Room;

public interface RoomRepository extends MongoRepository<Room, String> {
}
