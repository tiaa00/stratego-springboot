package spring.stratego;

import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@SpringBootApplication
@Controller
public class StrategoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrategoApplication.class, args);
		GameRoomManager gameRoomManager = new GameRoomManager();

        // Create a game room
		// roomID suppossedly auto generated  by database
        gameRoomManager.createGameRoom("room1");
		gameRoomManager.createGameRoomConcurrently("room2");
        gameRoomManager.createGameRoomConcurrently("room3");
        // System.out.println("Game Room Created - ID: " + gameRoom1.getRoomId());

		// Wait for a while to allow room creation tasks to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

		// Scanner s = new Scanner(System.in);
        // // // Join game room
		// System.out.println("*First player*\nEnter username:");
		// String username1 = s.next();
		// System.out.println("*Second player*\nEnter username:");
        // String username2 = s.next();
		// System.out.println("*Enter room code to join:");
        // String roomNo = s.next();

        String username1 = "player1";
        String username2 = "player2";
        String roomNo = "room1";

		boolean join1 = gameRoomManager.joinGameRoom(roomNo, username1);
        System.out.println("Player1 joined game room: " + join1);

        boolean join2 = gameRoomManager.joinGameRoom(roomNo, username2);
        System.out.println("Player2 joined game room: " + join2);

		GameRoom gameRoom1 = gameRoomManager.getGameRoom("room1");
        GameRoom gameRoom2 = gameRoomManager.getGameRoom("room2");
        GameRoom gameRoom3 = gameRoomManager.getGameRoom("room3");

		//check if the room is created or not
		if (gameRoom1 != null) {
            System.out.println("Game Room 1 ID: " + gameRoom1.getRoomId());
            System.out.println("Game Room 1 Players: " + gameRoom1.getPlayers());
        }

        if (gameRoom2 != null) {
            System.out.println("Game Room 2 ID: " + gameRoom2.getRoomId());
            System.out.println("Game Room 2 Players: " + gameRoom2.getPlayers());
        }

        if (gameRoom3 != null) {
            System.out.println("Game Room 3 ID: " + gameRoom3.getRoomId());
            System.out.println("Game Room 3 Players: " + gameRoom3.getPlayers());
        }

        // Shutdown the game room manager (terminate the thread pool)
        gameRoomManager.shutdown();
	}

    // @PostMapping("/submit")
    // public String handleUserSubmit(@RequestParam String username, RedirectAttributes redirectAttributes) {
    //     // You can now use this username for whatever you want.
    //     // System.out.println(username);
    //     // Add the username to the redirect attributes
    //     redirectAttributes.addFlashAttribute("username", username);

    //     // Redirect to the allroom page
    //     return "redirect:/allroom";
    // }

    @PostMapping("/allroom")
    public String showAllRoom(Model model, @RequestParam String username, HttpSession session) {
        session.setAttribute("username", username);
        model.addAttribute("username", username);
        return "allroom";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "index";
    }

}
