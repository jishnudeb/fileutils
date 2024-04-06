package com.files.utils.processors;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.files.utils.etc.FileProcessorResult;
import com.files.utils.etc.Status;
import com.files.utils.processors.helpers.FileReadHelper;
import com.files.utils.processors.helpers.FileWriteHelper;

public class FileProcessor {
	private final FileReadHelper fileReadHelper;
	private final FileWriteHelper fileWriteHelper;
	
	public FileProcessor(FileReadHelper fileReadHelper, FileWriteHelper fileWriteHelper) {
		this.fileReadHelper = fileReadHelper;
		this.fileWriteHelper = fileWriteHelper;
	}

	public FileProcessorResult reverseContents(String inputFilePath, String outputFilePath) {
		Status retStatus = Status.SUCCESS;
		String originalText = null;
		String reversedText = null;
		List<String> linesFromFileList = null;
		
		try {
			linesFromFileList = fileReadHelper.readAllLines(inputFilePath);
		} catch(IOException | URISyntaxException e) {
			retStatus = Status.FILE_READ_FAIL;
		} 
		if(retStatus != Status.FILE_READ_FAIL) {
			if(linesFromFileList != null && !linesFromFileList.isEmpty()) {
				String[] returnValues = reverseContents(linesFromFileList);
				originalText = returnValues[0];
				reversedText = returnValues[1];
				try {
					fileWriteHelper.writeToFile(outputFilePath, reversedText);
				} catch(IOException e) {
					retStatus = Status.FILE_WRITE_FAIL;
					reversedText = null;	
				}
				
			} else {
				retStatus = Status.READ_FILE_EMPTY_FAIL;
			}
		}
		return new FileProcessorResult(retStatus,originalText,reversedText);
	}
	
	
	private String[] reverseContents(List<String> linesFromFileList) {
		StringBuilder sbLine = new StringBuilder();
		linesFromFileList.forEach(sbLine::append);
		return new String[] {sbLine.toString(), sbLine.reverse().toString()};
	}
}
