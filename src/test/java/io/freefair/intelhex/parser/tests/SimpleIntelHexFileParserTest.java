package io.freefair.intelhex.parser.tests;

import io.freefair.intelhex.parser.IntelHexParser;
import io.freefair.intelhex.parser.ParsedLine;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.List;

public class SimpleIntelHexFileParserTest
{
	String firstFile = ":020000011000ED\n" +
			":10010000214601360121470136007EFE09D2190140\n" +
			":100110002146017EB7C20001FF5F16002148011988\n" +
			":10012000194E79234623965778239EDA3F01B2CAA7\n" +
			":100130003F0156702B5E712B722B732146013421C7\n" +
			":00000001FF";

	@Test
	public void firstSimpleTest() {
		IntelHexParser parser = new IntelHexParser();
		Arrays.stream(firstFile.split("\n")).forEach(parser::addLine);
		parser.parseLines();
		List<ParsedLine> parsedLines = parser.getParsedLines();

		assertThat(parsedLines.size(), is(6));

		ParsedLine parsedLine = parsedLines.get(0);
		assertThat(parsedLine.getByteCount(), is((byte)2));

		assertThat(parsedLine.getChecksum(), is(parsedLine.getCalculatedChecksum()));

		parsedLine = parsedLines.get(1);
		assertThat(parsedLine.getByteCount(), is((byte)16));

		assertThat(parsedLine.getChecksum(), is(parsedLine.getCalculatedChecksum()));

		parsedLine = parsedLines.get(2);
		assertThat(parsedLine.getByteCount(), is((byte)16));

		assertThat(parsedLine.getChecksum(), is(parsedLine.getCalculatedChecksum()));

		parsedLine = parsedLines.get(3);
		assertThat(parsedLine.getByteCount(), is((byte)16));

		assertThat(parsedLine.getChecksum(), is(parsedLine.getCalculatedChecksum()));

		parsedLine = parsedLines.get(4);
		assertThat(parsedLine.getByteCount(), is((byte)16));

		assertThat(parsedLine.getChecksum(), is(parsedLine.getCalculatedChecksum()));

		parsedLine = parsedLines.get(5);
		assertThat(parsedLine.getByteCount(), is((byte)0));

		assertThat(parsedLine.getChecksum(), is(parsedLine.getCalculatedChecksum()));
	}
}
