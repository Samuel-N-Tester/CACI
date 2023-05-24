package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import orders.BasicOrderList;

@SpringBootApplication
@RestController
public class BrickController {
	
	BasicOrderList basicOrderList = new BasicOrderList();

	public static void main(String[] args) {
		SpringApplication.run(BrickController.class, args);
	}

	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	@ResponseBody
	public Long createOrder(@RequestBody int numberOfBricks) {
		Long orderReference = basicOrderList.createOrder(numberOfBricks);
		return orderReference;
	}

}
