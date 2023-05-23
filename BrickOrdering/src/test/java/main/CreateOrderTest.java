package main;

import org.junit.jupiter.api.Test;
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
class CreateOrderTest {

    @Autowired
    WebTestClient webTestClient;
    
    TestFileReader testFileReader = new TestFileReader();

	/**
	 * Test to see if the order reference is returned correctly when an order is made
	 */
    @Test
    public void testWithRequest1() {
        String request1 = testFileReader.testDataFileAsString("request1.json");
        String request2 = testFileReader.testDataFileAsString("request2.json");
        String request3 = testFileReader.testDataFileAsString("request3.json");
        String expectedReply = testFileReader.testDataFileAsString("reply1.json");
        
        // this would be order reference 1
        webTestClient.post()
    	.uri("/createOrder")
    	.contentType(MediaType.APPLICATION_JSON)
    	.body(BodyInserters.fromValue(request1));
        
        // this would be order reference 2
        webTestClient.post()
    	.uri("/createOrder")
    	.contentType(MediaType.APPLICATION_JSON)
    	.body(BodyInserters.fromValue(request2));
        
        // this would be order reference 3
        ((RequestBodySpec) webTestClient.post()
        	.uri("/createOrder")
        	.contentType(MediaType.APPLICATION_JSON)
        	.accept(MediaType.APPLICATION_JSON))
        	.body(BodyInserters.fromValue(request3))
        	.exchange()
            .expectStatus().isOk()
            .expectBody().json(expectedReply);
    }
	
}
