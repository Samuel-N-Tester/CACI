package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import orders.BasicOrderList;
import orders.BasicOrder;

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
		return basicOrderList.createOrder(numberOfBricks);
	}
	
	@RequestMapping(value = "/getOrder", method = RequestMethod.GET)
	@ResponseBody
	public BasicOrder getOrder(@RequestParam(value="order", required=false, defaultValue="0")Long orderReference) {
		return basicOrderList.getOrder(orderReference);
	}
	
	@RequestMapping(value = "/getAllOrders", method = RequestMethod.GET)
	@ResponseBody
	public BasicOrder[] getAllOrders() {
		return basicOrderList.getAllOrders();
	}

}
