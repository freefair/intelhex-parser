package io.freefair.intelhex.parser;

import java.io.*;
import java.nio.file.Files;

public class FileParser extends IntelHexParser
{
	public FileParser(File file) {
		try {
            Files.lines(file.toPath()).forEach(line -> addLine(line));
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
}
