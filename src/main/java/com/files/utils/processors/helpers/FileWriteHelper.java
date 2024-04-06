package com.files.utils.processors.helpers;

import java.io.IOException;

import com.files.utils.etc.Status;

public interface FileWriteHelper {
	public Status writeToFile(String filePath, String text) throws IOException;

}
