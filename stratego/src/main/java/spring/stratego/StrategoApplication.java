package spring.stratego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import spring.stratego.model.Chat;
import spring.stratego.model.Player;
import spring.stratego.service.ChatService;
import spring.stratego.service.PlayerService;

@SpringBootApplication
@Controller
public class StrategoApplication {

    public static void main(String[] args) {
        // Create and run Spring Boot application
        SpringApplication.run(StrategoApplication.class, args);
	}
}
