package edu.uprm.arqui.instructions;

import edu.uprm.arqui.assembler.addressingmodes.DirectAddressingMode;
import edu.uprm.arqui.assembler.addressingmodes.Instruction;
import edu.uprm.arqui.assembler.addressingmodes.RegisterAddressingMode;
import edu.uprm.arqui.register.MainRegisters;
import edu.uprm.arqui.util.NumberUtils;

/**
 * Created by eduardobreijo on 9/3/14.
 */
public class ArithmeticInstructions {
	
	private static MainRegisters registers = MainRegisters.getInstance();
	
	/**
	 * Add operation using Register Addressing Mode: Ra <- Rb + Rc
	 * Provides the functionality of the ADD function on 8-bit registers
	 * 
	 * @param instruction the instruction object
	 */
	public static void add(Instruction instruction) {
		RegisterAddressingMode raMode = (RegisterAddressingMode) instruction;
		int value1 = registers.getRegister(raMode.getRegB());
		int value2 = registers.getRegister(raMode.getRegC());
		int sum = value1 + value2;
		registers.setRegister((byte)sum, raMode.getRegA());
	}
	
	/**
	 * Subtraction operation using Register Addressing Mode: Ra <- Rb - Rc
	 * Provides the functionality of the SUB function on 8-bit registers
	 * 
	 * @param instruction the instruction object
	 */
	public static void sub(Instruction instruction) {
		RegisterAddressingMode raMode = (RegisterAddressingMode) instruction;
		int value1 = registers.getRegister(raMode.getRegB());
		int value2 = registers.getRegister(raMode.getRegC());
		int sub = value1 - value2;
		registers.setRegister((byte)sub, raMode.getRegA());
	}
	
	/**
	 * Add operation using Direct Addressing Mode: R1 <- Ra + const
	 * Provides the functionality of the ADI function on 8-bit registers
	 * 
	 * @param instruction the instruction object
	 */
	public static void adi(Instruction instruction) {
		DirectAddressingMode daMode = (DirectAddressingMode) instruction;
		int value1 = registers.getRegister(daMode.getRegA());
		int value2 = NumberUtils.getSignedValueOf(daMode.getOperand(), 0, 7, 8);
		int sum = value1 + value2;
		registers.setRegister((byte)sum, 1);
	}
	
	/**
	 * Subtraction operation using Direct Addressing Mode: R1 <- Ra - const
	 * Provides the functionality of the SBI function on 8-bit registers
	 * 
	 * @param instruction the instruction object
	 */
	public static void sbi(Instruction instruction) {
		DirectAddressingMode daMode = (DirectAddressingMode) instruction;
		int value1 = registers.getRegister(daMode.getRegA());
		int value2 = NumberUtils.getSignedValueOf(daMode.getOperand(), 0, 7, 8);
		int sub = value1 - value2;
		registers.setRegister((byte)sub, 1);
	}
}
