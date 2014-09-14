package edu.uprm.arqui.processor;

import edu.uprm.arqui.util.NumberUtils;

public class Instruction {

	private int instruction;
	private static int OPCODE_SIZE = 5;
	private static int RADIX = 16;
	private boolean stopInstruction= false;

	/**
	 * Constructor for an instruction given as an integer value
	 * @param instrucion
	 */
	public Instruction(int instrucion) {
		this.instruction = instrucion;
	}

	/**
	 * Constructor for an instruction given as an hexadecimal string
	 * @param instruction
	 */
	public Instruction(String instruction) {
		this.instruction = Integer.parseInt(instruction, RADIX);
	}

	/**
	 * Get the instruction value
	 * @return instruction integer
	 */
	public int getInstruction() {
		return instruction;
	}
	
	/**
	 * Gets if a given instruction is the stop instruction.
	 * @return true if its the stop instruction, false otherwise.
	 */
	public boolean isStopInstruction() {
		return stopInstruction;
	}

	/**
	 * Get the instruction in its hexadecimal representation
	 * @return a String with the hex representation of the instruction
	 */
	public String getInstructionInHex() {
		return NumberUtils.intToHexString(instruction, 4);
	}
}