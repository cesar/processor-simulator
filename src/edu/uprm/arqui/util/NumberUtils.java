package edu.uprm.arqui.util;

/**
 * Created by eduardobreijo on 9/4/14.
 */
public class NumberUtils {

	public static int getUnsignedValueOf(byte value) {
		int unsignedValue = value & 0xFF;
		return unsignedValue;
	}
}
