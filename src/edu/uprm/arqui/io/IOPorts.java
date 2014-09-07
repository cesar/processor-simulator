package edu.uprm.arqui.io;

import edu.uprm.arqui.memory.Memory;
import edu.uprm.arqui.util.NumberUtils;


/**
 * This class provides the implementation of all the I/O ports 
 * It will store and retrieve values from assigned memory depending on the the ports 
 * Created by eduardobreijo on 9/7/14.
 */
public class IOPorts {
	
	private static final int KEYBOARD_LOCATION = 128;
	private static final int PARALLEL_INPUT_LOCATION = 130;
	private static final int PARALLEL_OUTPUT_LOCATION = 132;
	private static final int HEX_DISPLAY_LOCATION = 136;
	private static final int ASCII_LOCATION = 140;
	
	private Memory memory;
	
	public IOPorts(Memory memory) {
		this.memory = memory;	
	}
	
	/**
	 * Set the value from the keyboard in the memory 
	 * @param data the 8 bits from the keyboard
	 */
	public void setKeyboard(byte data) {
		memory.setDataAt(KEYBOARD_LOCATION, data);
	}
	
	/**
	 * Retrieve the value of the keyboard from the memory
	 * @return the 8 bits from the memory
	 */
	public byte getKeyboard() {
		return memory.getDataAt(KEYBOARD_LOCATION);
	}
	
	/**
	 * Set the value of the parallel input in the memory
	 * @param data the 8 bits value
	 */
	public void setParallelInput(byte data) {
		memory.setDataAt(PARALLEL_INPUT_LOCATION, data);
	}
	
	/**
	 * Retrieve the value of the parallel input from the memory
	 * @return the 8 bits value
	 */
	public byte getParallelInput() {
		return memory.getDataAt(PARALLEL_INPUT_LOCATION);
	}
	
	/**
	 * Retrieve the value of the parallel output from the memory
	 * @return the 8 bits value
	 */
	public byte getParallelOutput() {
		return memory.getDataAt(PARALLEL_OUTPUT_LOCATION);
	}
	
	/**
	 * Retrieve 4 hexadecimal digits (each 4 bits) from the memory locations 136 to 139
	 * @return the hexadecimal number
	 */
	public String getHexDisplay() {
		int hexDisplay = 0;
		for (int i = 0; i < 4; i++) {
			int value = memory.getDataAt(HEX_DISPLAY_LOCATION + i);
			hexDisplay |= NumberUtils.getUnsignedValueOf(value, 4, 7, 8);
			if(i < 3) {
				hexDisplay = hexDisplay << 4;
			}
		}	
		return NumberUtils.intToHexString(hexDisplay, 4);
	}
	
	/**
	 * Retrieve the the ASCII code from memory locations 140 to 155
	 * @return the ASCII code within that memory locations
	 */
	public String getASCII() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 16; i++) {
			int data = memory.getDataAt(ASCII_LOCATION + i);
			char character = (char) NumberUtils.getUnsignedValueOf(data, 0, 7, 8);
			sb.append(character);
		}
		String ascii = sb.toString();
		return ascii;
	}
}
