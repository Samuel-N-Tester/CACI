package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BrickController {

	public static void main(String[] args) {
		SpringApplication.run(BrickController.class, args);
	}



}
