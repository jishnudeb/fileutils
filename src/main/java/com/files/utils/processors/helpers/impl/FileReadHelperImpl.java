package com.files.utils.processors.helpers.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.files.utils.processors.helpers.FileReadHelper;

public class FileReadHelperImpl implements FileReadHelper {

	@Override
	public List<String> readAllLines(String filePath) throws IOException, URISyntaxException {
		return Files.readAllLines(Paths.get(filePath));
	}

}
