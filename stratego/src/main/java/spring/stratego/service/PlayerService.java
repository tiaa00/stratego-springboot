package spring.stratego.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.stratego.model.Player;
import spring.stratego.repository.PlayerRepository;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(String id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(String id, Player player) {
        Player existingPlayer = getPlayerById(id);
        if (existingPlayer != null) {
            existingPlayer.setName(player.getName());
            return playerRepository.save(existingPlayer);
        } else {
            return null;
        }
    }

    public void deletePlayer(String id) {
        playerRepository.deleteById(id);
    }
}
