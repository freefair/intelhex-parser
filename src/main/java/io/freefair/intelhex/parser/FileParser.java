package io.freefair.intelhex.parser;

import java.io.*;

public class FileParser extends IntelHexParser
{
	public FileParser(File file) {
		try(BufferedReader stream = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
			String line;
			do {
				line = stream.readLine();
				addLine(line);
			} while(line != null);
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
}
