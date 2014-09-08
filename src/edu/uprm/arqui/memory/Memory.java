package edu.uprm.arqui.memory;

import java.util.ArrayList;
import java.util.List;

import edu.uprm.arqui.util.NumberUtils;

/**
 * Created by eduardobreijo on 9/4/14.
 */
public class Memory {

	private static List<Byte> memory;
	private static int CELL_SIZE = 8;
	
	/**
	 * Initialize the Memory based on the number of cells.
	 * Each cell has 8 bits and it is initialized to zero.
	 * @param numberOfCells
	 */
	public Memory(int numberOfCells) {
		memory = new ArrayList<Byte>(numberOfCells);
		
		for(int i = 0; i < numberOfCells; i++){
			memory.add((byte) 0);
		}
	}
	
	public static byte getDataAt(int location) {
		return memory.get(location);
	}
	
	
	/**
	 * Set data at specific location in the memory
	 * @param location memory location
	 * @param data the value you want to set
	 */
	public void setDataAt(int location, byte data) {
		memory.set(location, data);
	}
	
	/**
	 * Get a chunk of data given a specific location and the amount of cells
	 * @param location memory location
	 * @param cellsToFetch the amount of cells you want to fetch the data
	 * @return the unsigned data
	 */
	public int getDataAt(int location, int cellsToFetch) {
		int data = 0;
		for (int i = 0; i < cellsToFetch; i++) {
			data |= memory.get(location + i);
			if(i < cellsToFetch - 1) {
				data = data << CELL_SIZE;
			}
		}
		int bits = CELL_SIZE * cellsToFetch;
		return NumberUtils.getUnsignedValueOf(data, 0, bits - 1, bits);
	}
	
	// TODO : Implementation of this method if we want add a chunk of data in multiple cells
	public void setDataAt(int location, int data, int numBits) {
	
		
	}
	
	/**
	 * Returns the content of the memory in the following format
	 * cellLocation in hex: unsigned value
	 */
	@Override
	public String toString() {
		int size = memory.size();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i++) {
			sb.append(String.format("%02X: %s\n", i, NumberUtils.getUnsignedValueOf(memory.get(i), 0, 7, 8)));
		}
		return sb.toString();
	}
}
