package com.files.utils;

import com.files.utils.processors.FileProcessor;
import com.files.utils.processors.helpers.FileReadHelper;
import com.files.utils.processors.helpers.FileWriteHelper;
import com.files.utils.processors.helpers.impl.FileReadHelperImpl;
import com.files.utils.processors.helpers.impl.FileWriteHelperImpl;

public class Main {

	public static void main(String[] args) {
		String fileToRead = "src/test/resources/testRead.txt";
		String fileToWrite = "src/test/resources/testWrite.txt";
		FileReadHelper reader = new FileReadHelperImpl();
		FileWriteHelper writer = new FileWriteHelperImpl();
		FileProcessor processor = new FileProcessor(reader, writer);
		processor.reverseContents(fileToRead, fileToWrite);

	}

}
