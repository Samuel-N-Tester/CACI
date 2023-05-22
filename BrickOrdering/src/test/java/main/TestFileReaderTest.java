package main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestFileReaderTest {

    @Autowired
    WebTestClient webTestClient;
    
    TestFileReader testFileReader = new TestFileReader();

	/**
	 * Compare one request to the same one to make sure that the testing
	 * I/O is running correctly
	 */
	@Test
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
	void compareTwoDifferentRequests() {
		
		String request = testFileReader.testDataFileAsString("request1.json");
		String differentRequest = testFileReader.testDataFileAsString("request2.json");
		
		assertFalse(request.equals(differentRequest));
	}
	
}
