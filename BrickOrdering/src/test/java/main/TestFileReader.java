package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestFileReader {
	
	/**
	 * Returns the content of a file in the test/resources directory with a given name as a String
	 * @param String fileName The name of the file you would like the content from
	 * @return The content of the file as a string
	 */ 
	public String testDataFileAsString(String fileName) {
		String folderPath = "\\src\\test\\resources\\";
		File file = new File(System.getProperty("user.dir") + folderPath + fileName);
		try {
			return Files.readString(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
