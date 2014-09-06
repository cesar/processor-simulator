package edu.uprm.arqui.instructions;

import edu.uprm.arqui.util.NumberUtils;

/**
 * This class provides the implementation of an instruction.
 * It lets you get the opcode and operands of an instruction. 
 * 
 * Created by eduardobreijo on 9/6/14.
 */
public class Instruction {
	
	private int instruction;
	private static int OPCODE_SIZE = 5;
	private static int RADIX = 16;
	
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
	 * Get the opcode which is the first 5 bits in the instruction
	 * @return the opcode integer value
	 */
	public int getOpcode() {
		return NumberUtils.getUnsignedValueOf(instruction, 0, OPCODE_SIZE - 1, 16);
	}
	
	/**
	 * Get the operand of an instruction given the start and end bits, that is,
	 * given the boundaries of the operand
	 * @param start the first bit of the operand in the instruction
	 * @param end the last bit of the operand in the instruction
	 * @return the operand integer value
	 */
	public int getOperand(int start, int end) {
		return NumberUtils.getUnsignedValueOf(instruction, start, end, 16);
	}
	
	/**
	 * Get the instruction in its hexadecimal representation
	 * @return a String with the hex representation of the instruction
	 */
	public String getInstructionInHex() {
		return NumberUtils.intToHexString(instruction, 4);
	}
}
