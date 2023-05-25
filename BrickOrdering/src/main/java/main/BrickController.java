package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import orders.AdvanceOrderList;
import orders.AdvanceOrder;

@SpringBootApplication
@RestController
public class BrickController {
	
	AdvanceOrderList advanceOrderList = new AdvanceOrderList();

	public static void main(String[] args) {
		SpringApplication.run(BrickController.class, args);
	}

	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	@ResponseBody
	public Long createOrder(@RequestBody int numberOfBricks) {
		return advanceOrderList.createOrder(numberOfBricks);
	}
	
	@RequestMapping(value = "/getOrder", method = RequestMethod.GET)
	@ResponseBody
	public AdvanceOrder getOrder(@RequestParam(value="order", required=false, defaultValue="0")Long orderReference) {
		return advanceOrderList.getOrder(orderReference);
	}
	
	@RequestMapping(value = "/getAllOrders", method = RequestMethod.GET)
	@ResponseBody
	public AdvanceOrder[] getAllOrders() {
		return advanceOrderList.getAllOrders();
	}
	
	@RequestMapping(value = "/updateOrder/{orderReference}/{numberOfBricks}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> updateOrder(@PathVariable Long orderReference, @PathVariable int numberOfBricks) {
		
		ResponseEntity<Object> responseEntity;	

		try {
			Long newOrderReference = advanceOrderList.updateOrder(orderReference, numberOfBricks);
			responseEntity = ResponseEntity.ok(newOrderReference);
		} catch (IllegalStateException e) {
			responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
	
	@RequestMapping(value = "/fulfilOrder/{orderReference}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> fulfilOrder(@PathVariable Long orderReference) {
		
		ResponseEntity<Object> responseEntity;	

		try {
			advanceOrderList.fulfilOrder(orderReference);
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

}
