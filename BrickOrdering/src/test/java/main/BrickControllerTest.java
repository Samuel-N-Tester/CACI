package main;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.RequestBodySpec;
import org.springframework.web.reactive.function.BodyInserters;

@ActiveProfiles("test")
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BrickControllerTest {

    @Autowired
    WebTestClient webTestClient;
    
    TestFileReader testFileReader = new TestFileReader();
    
	/**
	 * Compare one request to the same one to make sure that the testing
	 * I/O is running correctly
	 */
	@Test
	@Order(0)
    public void addThreeOrders() {
        String request1 = testFileReader.testDataFileAsString("request1.json");
        String request2 = testFileReader.testDataFileAsString("request2.json");
        String request3 = testFileReader.testDataFileAsString("request3.json");
        
        // this would be order reference 1
        webTestClient.post()
    	.uri("/createOrder")
    	.contentType(MediaType.APPLICATION_JSON)
    	.body(BodyInserters.fromValue(request1))
    	.exchange();
        
        // this would be order reference 2
        webTestClient.post()
    	.uri("/createOrder")
    	.contentType(MediaType.APPLICATION_JSON)
    	.body(BodyInserters.fromValue(request2))
    	.exchange();
        
        // this would be order reference 3
        webTestClient.post()
    	.uri("/createOrder")
    	.contentType(MediaType.APPLICATION_JSON)
    	.body(BodyInserters.fromValue(request3))
    	.exchange();
    }

	/**
	 * Compare one request to the same one to make sure that the testing
	 * I/O is running correctly
	 */
	@Test
	@Order(1)
	void compareTwoMatchingRequests() {
		
		String request = testFileReader.testDataFileAsString("request1.json");
		String differentRequest = testFileReader.testDataFileAsString("request1.json");
		
		assertTrue(request.equals(differentRequest));
	}
	
	/**
	 * Compare one request to a different one to make sure that the testing
	 * I/O is running correctly
	 */
	@Test
	@Order(2)
	void compareTwoDifferentRequests() {
		
		String request = testFileReader.testDataFileAsString("request1.json");
		String differentRequest = testFileReader.testDataFileAsString("request2.json");
		
		assertFalse(request.equals(differentRequest));
	}
    
	/**
	 * Test to see if abnormal dates are correctly returned
	 */
    @Test
	@Order(3)
    public void testGetOrder() {
    	
        String expectedReply = testFileReader.testDataFileAsString("reply1.json");
        
        // retrieve order reference 2
        webTestClient.get()
	    	.uri("/getOrder?order=2")
	    	.exchange()
	        .expectStatus().isOk()
	        .expectBody().json(expectedReply);
    }
	
	/**
	 * Test to see if all orders are returned upon request
	 */
    @Test
	@Order(4)
    public void testGetAllOrders() {
    	
        String expectedReply = testFileReader.testDataFileAsString("reply2.json");
        
        // retrieve order reference 2
        webTestClient.get()
	    	.uri("/getAllOrders")
	    	.exchange()
	        .expectStatus().isOk()
	        .expectBody().json(expectedReply);
    }
    
	/**
	 * Test to see if the order reference is returned correctly when an order is made
	 */
    @Test
	@Order(5)
    public void testCreateOrder() {

        String request4 = testFileReader.testDataFileAsString("request4.json");
        String expectedReply = testFileReader.testDataFileAsString("reply3.json");
    	
        // this would be order reference 3
        ((RequestBodySpec) webTestClient.post()
        	.uri("/createOrder")
        	.contentType(MediaType.APPLICATION_JSON)
        	.accept(MediaType.APPLICATION_JSON))
        	.body(BodyInserters.fromValue(request4))
        	.exchange()
            .expectStatus().isOk()
            .expectBody().json(expectedReply);
    }
    
	/**
	 * Test to see if the order reference is returned correctly when an order is updated
	 */
    @Test
	@Order(6)
    public void testUpdateOrder() {

        String expectedReply = testFileReader.testDataFileAsString("reply4.json");
        
        // retrieve order reference 2
        webTestClient.put()
	    	.uri("/updateOrder/2/20")
	    	.exchange()
	        .expectStatus().isOk()
	        .expectBody().json(expectedReply);
    }
    
	/**
	 * Test to see if the order reference is returned correctly when an order is updated
	 */
    @Test
	@Order(7)
    public void testFulfuilOrder() {

        webTestClient.put()
	    	.uri("/fulfilOrder/3")
	    	.exchange()
	        .expectStatus().isOk();
    }
    
	/**
	 * Test to see if the order reference is returned correctly when an order is updated
	 */
    @Test
	@Order(8)
    public void testFulfuilOrderWithInvalidOrderReference() {

        webTestClient.put()
	    	.uri("/fulfilOrder/300")
	    	.exchange()
	        .expectStatus().isBadRequest();
    }
    
	/**
	 * Test to see if the order reference is returned correctly when an order is updated
	 */
    @Test
	@Order(9)
    public void testUpdateOrderThatIsDispatched() {
        
        // retrieve order reference 2
        webTestClient.put()
	    	.uri("/updateOrder/3/70")
	    	.exchange()
	        .expectStatus().isBadRequest();
    }
	
}
