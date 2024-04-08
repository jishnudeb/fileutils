package com.files.utils.processors;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.files.utils.etc.FileProcessorResult;
import com.files.utils.etc.Status;
import com.files.utils.processors.helpers.FileReadHelper;
import com.files.utils.processors.helpers.FileWriteHelper;

class FileProcessorTest {
	private FileReadHelper fileReadHelper = new FileReadHelperTestImpl();
	private FileWriteHelper fileWriteHelper = new FileWriteHelperTestImpl();
	private FileProcessor fileProcessor = new FileProcessor(fileReadHelper, fileWriteHelper);

	@Test
	void testSuccess() {
		FileProcessorResult fileProcessorResult = fileProcessor.reverseContents("SUCCESSFUL_FILE_READ_OP","SUCCESSFUL_FILE_WRITE_OP");
		Assertions.assertEquals(Status.SUCCESS, fileProcessorResult.status());
		Assertions.assertEquals("ABC", fileProcessorResult.inputText());
		Assertions.assertEquals("CBA", fileProcessorResult.outputText());
	}
	
	@Test
	void testFileReadError() {
		FileProcessorResult fileProcessorResult = fileProcessor.reverseContents("FAIL_FILE_READ_OP","SUCCESSFUL_FILE_WRITE_OP");
		Assertions.assertEquals(Status.FILE_READ_FAIL, fileProcessorResult.status());
		Assertions.assertNull(fileProcessorResult.inputText());
		Assertions.assertNull(fileProcessorResult.outputText());
	}
	
	@Test
	void testFileEmptyError() {
		FileProcessorResult fileProcessorResult = fileProcessor.reverseContents("EMPTY_FILE_READ_OP","SUCCESSFUL_FILE_WRITE_OP");
		Assertions.assertEquals(Status.READ_FILE_EMPTY_FAIL, fileProcessorResult.status());
		Assertions.assertNull(fileProcessorResult.inputText());
		Assertions.assertNull(fileProcessorResult.outputText());
	}
	
	@Test
	void testFileWriteError() {
		FileProcessorResult fileProcessorResult = fileProcessor.reverseContents("SUCCESSFUL_FILE_READ_OP","FAIL_FILE_WRITE_OP");
		Assertions.assertEquals(Status.FILE_WRITE_FAIL, fileProcessorResult.status());
		Assertions.assertEquals("ABC", fileProcessorResult.inputText());
		Assertions.assertNull(fileProcessorResult.outputText());
	}
}

class FileReadHelperTestImpl implements FileReadHelper {

	@Override
	public List<String> readAllLines(String filePath) throws IOException, URISyntaxException {
		List<String> strList = Arrays.asList("ABC");
		if("SUCCESSFUL_FILE_READ_OP".equals(filePath)) {
			return strList;
		} else if ("EMPTY_FILE_READ_OP".equals(filePath)) {
			return null;
		} else {
			throw new IOException("Invalid File Path");
		}
	}
}

class FileWriteHelperTestImpl implements FileWriteHelper {

	@Override
	public Status writeToFile(String filePath, String text) throws IOException {
		if("SUCCESSFUL_FILE_WRITE_OP".equals(filePath)) {
			return Status.SUCCESS;
		} else {
			throw new IOException("Invalid File Path");
		}
	}
}
