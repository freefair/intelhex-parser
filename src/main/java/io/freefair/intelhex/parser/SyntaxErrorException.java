package io.freefair.intelhex.parser;

/**
 * Created by dennis on 08.01.16
 * (c) by dennis
 */
public class SyntaxErrorException extends RuntimeException {
	public SyntaxErrorException(String line) {
		super(line);
	}
}
