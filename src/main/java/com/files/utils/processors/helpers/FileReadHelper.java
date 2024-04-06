package com.files.utils.processors.helpers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface FileReadHelper {
	public List<String> readAllLines(String filePath) throws IOException, URISyntaxException;

}
