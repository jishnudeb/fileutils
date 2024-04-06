package com.files.utils.processors.helpers.impl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.files.utils.etc.Status;
import com.files.utils.processors.helpers.FileWriteHelper;

public class FileWriteHelperImpl implements FileWriteHelper {

	@Override
	public Status writeToFile(String filePath, String text) throws IOException {
		Status isSuccess = Status.FILE_WRITE_FAIL;
		try(BufferedWriter bufWriter = Files.newBufferedWriter(Paths.get(filePath));){
			bufWriter.write(text);
			isSuccess = Status.SUCCESS;
		}
		return isSuccess;
	}


}
