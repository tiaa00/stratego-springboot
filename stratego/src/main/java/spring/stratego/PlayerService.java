package spring.stratego;

import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository userRepository) {
        this.playerRepository = userRepository;
    }

    public Player saveUser(Player user) {
        return playerRepository.save(user);
    }

    // other CRUD operations
}

