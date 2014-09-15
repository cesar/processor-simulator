package edu.uprm.arqui.instructions;

import edu.uprm.arqui.assembler.Dissasembler;
import edu.uprm.arqui.assembler.addressingmodes.DirectAddressingMode;
import edu.uprm.arqui.assembler.addressingmodes.RegisterAddressingMode;
import edu.uprm.arqui.assembler.addressingmodes.RelativeAddressingMode;
import edu.uprm.arqui.memory.Memory;
import edu.uprm.arqui.processor.Processor;
import edu.uprm.arqui.register.MainRegisters;
import edu.uprm.arqui.util.NumberUtils;
import edu.uprm.arqui.assembler.addressingmodes.Instruction;


/**
 * This class implements all the functions related to data move 
 * from the instruction set
 * Created by cesarcruz on 9/3/14.
 */
public class DataMoveInstructions {
	
	private static Memory memory = Memory.getInstance();
	private static MainRegisters registers = MainRegisters.getInstance();
	
	//Operation: R[ra] <- [mem]
	
	public static void ld(Instruction instruction) {
		DirectAddressingMode daMode = (DirectAddressingMode) instruction;
		byte data = memory.getByte(daMode.getOperand());
	    int location = daMode.getRegA();
	    registers.setRegister(data, location);
	}
	
	//Operation: R[ra] <- cons
	
	public static void ldi(Instruction instruction){
		DirectAddressingMode daMode = (DirectAddressingMode) instruction;
		byte data = (byte) daMode.getOperand();
	    int location = daMode.getRegA();
	    registers.setRegister(data, location);
	}
	
	//Operation: R[1] <- cons
	
	public static void ldacc(Instruction instruction){
		RelativeAddressingMode reaMode = (RelativeAddressingMode) instruction;
		byte data = (byte) reaMode.getOperandLSB();
	    registers.setRegister(data, 1);
	}
	
	//Operation: [mem] <- R[ra]
	public static void st(Instruction instruction){
		DirectAddressingMode daMode = (DirectAddressingMode) instruction;
		byte data = registers.getRegister(daMode.getRegA()) ;
	    int location = daMode.getOperand() ;
		memory.setDataAt(location, data);
	}
	
	//Operation: [mem] <- R[1]
	public static void stacc(Instruction instruction){
		RelativeAddressingMode reaMode = (RelativeAddressingMode) instruction;
		byte data = (byte) registers.getRegister(1);
		int value = reaMode.getOperand();
	    int location = NumberUtils.getUnsignedValueOf(value, 3, 10, 11);
		memory.setDataAt(location, data);
	}
	
	/**
	 * Load Register operation: Ra <- mem[Rb]
	 * Load a register Ra (destination) with the content of the memory where 
	 * address is given by another register Rb(source).
	 * 
	 * @param instruction the instruction object
	 */
	public static void ldr(Instruction instruction) {
		RegisterAddressingMode raMode = (RegisterAddressingMode) instruction;
		int value = registers.getRegister(raMode.getRegB());
		int content = NumberUtils.getUnsignedValueOf(value, 0, Processor.GENERAL_REGISTER_SIZE - 1, Processor.GENERAL_REGISTER_SIZE);
		byte data = memory.getByte(content);
		registers.setRegister(data, raMode.getRegA());
	}

	/**
	 * Load Register operation: Rb <- mem[Ra]
	 * Load a register Rb (destination) with the content of the memory where 
	 * address is given by another register Ra (source).
	 * 
	 * @param instruction the instruction object
	 */
	public static void str(Instruction instruction) {
		RegisterAddressingMode raMode = (RegisterAddressingMode) instruction;
		int value = registers.getRegister(raMode.getRegA());
		int content = NumberUtils.getUnsignedValueOf(value, 0, Processor.GENERAL_REGISTER_SIZE - 1, Processor.GENERAL_REGISTER_SIZE);
		byte data = memory.getByte(content);
		registers.setRegister(data, raMode.getRegB());
	}
}
