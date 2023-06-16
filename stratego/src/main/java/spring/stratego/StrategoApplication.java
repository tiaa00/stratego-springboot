package spring.stratego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class StrategoApplication {

    public static void main(String[] args) {
        // Create and run Spring Boot application
        SpringApplication.run(StrategoApplication.class, args);
	}
}
