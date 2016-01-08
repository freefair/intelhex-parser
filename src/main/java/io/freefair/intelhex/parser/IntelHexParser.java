package io.freefair.intelhex.parser;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntelHexParser
{
	private List<String> lines = new ArrayList<>();

	@Getter
	private List<ParsedLine> parsedLines = new ArrayList<>();

	public void addLine(String line) throws SyntaxErrorException {
		if(!line.startsWith(":"))
			throw new SyntaxErrorException(line);
		lines.add(line);
	}

	public void parseLines() {
		parsedLines.addAll(lines.stream().map(this::parseLine).collect(Collectors.toList()));
	}

	private ParsedLine parseLine(String line) {
		ParsedLine result = new ParsedLine();
		String byteCount = line.substring(1, 3);
		String address = line.substring(3, 7);
		String type = line.substring(7, 9);
		String data = line.substring(9, line.length() - 2);
		String checksum = line.substring(line.length() - 2, line.length());

		result.setByteCount((byte)Integer.parseInt(byteCount, 16));
		result.setAddress(Integer.parseInt(address, 16));
		result.setType(type.equals("00") ? ParsedLineType.DATA : ParsedLineType.EOF);
		if(data.length() > 0)
			result.setData(Arrays.stream(data.split("(?<=\\G..)")).map(s -> (byte)Integer.parseInt(s, 16)).collect(Collectors.toList()).toArray(new Byte[data.length()/2]));
		result.setChecksum((byte)Integer.parseInt(checksum, 16));

		return result;
	}
}
