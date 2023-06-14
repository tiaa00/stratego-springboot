package spring.stratego;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService userService) {
        this.playerService = userService;
    }

    @PostMapping
    public Player createUser(@RequestBody Player user) {
        return playerService.saveUser(user);
    }

    // other endpoint methods
}
