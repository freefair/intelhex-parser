package io.freefair.intelhex.parser;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class ParsedLine
{
	private byte byteCount;
	private int address;
	private ParsedLineType type;
	private Byte[] data = new Byte[0];
	private byte checksum;

	public byte getCalculatedChecksum() {
		int checksum = Byte.toUnsignedInt(byteCount) & 0xFF;
		checksum += address & 0xFF;
		checksum += (address>>8) & 0xFF;
		checksum += (type == ParsedLineType.DATA ? 0 : 1) & 0xFF;
		for (Byte bte: data){
			checksum += Byte.toUnsignedInt(bte) & 0xFF;
		}
		checksum &= 0xFF;
		checksum = ~checksum;
		checksum += 1;
		checksum &= 0xFF;
		return (byte)(checksum);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(":");

		result.append(String.format("%02X", byteCount));
		result.append(String.format("%04X", address));
		result.append(String.format("%02X", type == ParsedLineType.DATA ? 0 : 1));
		for (Byte bte : data){
			result.append(String.format("%02X", bte));
		}
		result.append(String.format("%02X", checksum));

		return result.toString();
	}
}
