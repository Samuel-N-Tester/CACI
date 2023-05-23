package main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@ActiveProfiles("test")
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetOrderTest {

    @Autowired
    WebTestClient webTestClient;
    
    TestFileReader testFileReader = new TestFileReader();

	/**
	 * Test to see if abnormal dates are correctly returned
	 */
    @Test
    public void testWithRequest1() {
        String request = testFileReader.testDataFileAsString("request1.json");
        String expectedReply = testFileReader.testDataFileAsString("reply2.json");
        
        // this would be order reference 1
        webTestClient.post()
    	.uri("/createOrder")
    	.contentType(MediaType.APPLICATION_JSON)
    	.body(BodyInserters.fromValue(request));
        
        // this would be order reference 2
        webTestClient.post()
    	.uri("/createOrder")
    	.contentType(MediaType.APPLICATION_JSON)
    	.body(BodyInserters.fromValue(request));
        
        // this would be order reference 3
        webTestClient.post()
    	.uri("/createOrder")
    	.contentType(MediaType.APPLICATION_JSON)
    	.body(BodyInserters.fromValue(request));
        
        // retrieve order reference 2
        webTestClient.get()
	    	.uri("/createOrder?order=2")
	    	.exchange()
	        .expectStatus().isOk()
	        .expectBody().json(expectedReply);
    }
	
}
