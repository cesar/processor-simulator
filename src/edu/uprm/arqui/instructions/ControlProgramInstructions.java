package edu.uprm.arqui.instructions;

import edu.uprm.arqui.assembler.addressingmodes.DirectAddressingMode;
import edu.uprm.arqui.assembler.addressingmodes.Instruction;
import edu.uprm.arqui.assembler.addressingmodes.RegisterAddressingMode;
import edu.uprm.arqui.assembler.addressingmodes.RelativeAddressingMode;
import edu.uprm.arqui.processor.Processor;
import edu.uprm.arqui.register.BitCondition;
import edu.uprm.arqui.register.MainRegisters;
import edu.uprm.arqui.register.ProgramCounterRegister;
import edu.uprm.arqui.util.NumberUtils;

/**
 * Created by eduardobreijo on 9/3/14.
 */
public class ControlProgramInstructions {
	
	private static MainRegisters registers = MainRegisters.getInstance();
	private static ProgramCounterRegister pc = ProgramCounterRegister.getInstance();
	private static BitCondition condition = BitCondition.getInstance();
	
	/**
	 * Jump Register Operation using Register Addressing Mode: pc <- Ra
	 * The program counter is now set with the content of register Ra
	 * 
	 * @param instruction the instruction object
	 */
	public static void jmpr(Instruction instruction) {
		RegisterAddressingMode raModeMode = (RegisterAddressingMode) instruction;
		int value = registers.getRegister(raModeMode.getRegA());
		int content = NumberUtils.getUnsignedValueOf(value, 0, Processor.GENERAL_REGISTER_SIZE - 1, Processor.GENERAL_REGISTER_SIZE);
		pc.setPc(content);
	}
	
	/**
	 * Jump Address Operation using Relative Addressing Mode: pc <- address
	 * The program counter is now set with the an address given by the instruction
	 * 
	 * @param instruction the instruction object
	 */
	public static void jmpa(Instruction instruction) {
		RelativeAddressingMode raMode = (RelativeAddressingMode) instruction;
		int value = raMode.getOperand(); // There are 11-bits here, which means that the pc can go out of the memory
		int address = (value & 0x0FF);
		pc.setPc(address);
	}
	
	/**
	 * Jump Condition Register Operation using Direct Addressing Mode: if cond then pc <- Ra
	 * If the condition is met, the program counter will be set with the content of register Ra
	 * 
	 * @param instruction the instruction object
	 */
	public static void jcr(Instruction instruction) {
		DirectAddressingMode daMode = (DirectAddressingMode) instruction;
		if(condition.getBitCondition()) {
			int value = registers.getRegister(daMode.getRegA());
			int content = NumberUtils.getUnsignedValueOf(value, 0, Processor.GENERAL_REGISTER_SIZE - 1, Processor.GENERAL_REGISTER_SIZE);
			pc.setPc(content);
		}
	}
	
	/**
	 * Jump Condition Address Operation using Direct Addressing Mode: if cond then pc <- address
	 * If the condition is met, the program counter will be set an address given by the instruction
	 * 
	 * @param instruction the instruction object
	 */
	public static void jca(Instruction instruction) {
		RelativeAddressingMode raMode = (RelativeAddressingMode) instruction;
		if(condition.getBitCondition()) {
			int value = raMode.getOperand(); // There are 11-bits here, which means that the pc can go out of the memory
			int address = (value & 0x0FF);
			pc.setPc(address);
		}
	}
	
	/**
	 * Loop Operation using Direct Addressing Mode
	 * Ra <- Ra – 1 
	 * If Ra != 0 then [pc] <- address
	 * @param instruction the instruction object
	 */
	public static void loop(Instruction instruction) {
		DirectAddressingMode daMode = (DirectAddressingMode) instruction;
		int counter = NumberUtils.getUnsignedValueOf(registers.getRegister(daMode.getRegA()), 0, Processor.GENERAL_REGISTER_SIZE - 1, Processor.GENERAL_REGISTER_SIZE);
		while(counter != 0) {
			int address = daMode.getOperand();
			address = (address & 0x0FF);
			pc.setPc(address);
			counter--;
			registers.setRegister((byte) (counter), daMode.getRegA());
		}
	}
	
	//Operation:Cond <- R[Ra] > R[Rb]
	
	public static void gr(Instruction instruction){
		RegisterAddressingMode raMode = (RegisterAddressingMode) instruction;
		byte Ra= registers.getRegister(raMode.getRegA());
		byte Rb= registers.getRegister(raMode.getRegB());
		if(Ra>Rb){
			condition.setBitCondition(true);
		}
		else{
			condition.setBitCondition(false);
		}
	}
	
	//Operation: Cond <- R[Ra] >= R[Rb]
	
	public static void gre(Instruction instruction){
		RegisterAddressingMode raMode = (RegisterAddressingMode) instruction;
		byte Ra= registers.getRegister(raMode.getRegA());
		byte Rb= registers.getRegister(raMode.getRegB());
		if(Ra>=Rb){
			condition.setBitCondition(true);
		}
		else{
			condition.setBitCondition(false);
		}
	}
	
	//Operation:Cond <- R[Ra] == R[Rb]
	
	public static void eq(Instruction instruction){
		RegisterAddressingMode raMode = (RegisterAddressingMode) instruction;
		byte Ra= registers.getRegister(raMode.getRegA());
		byte Rb= registers.getRegister(raMode.getRegB());
		if(Ra==Rb){
			condition.setBitCondition(true);
		}
		else{
			condition.setBitCondition(false);
		}
	}
	
	//Operation: Cond <- R[Ra] != R[Rb]
	
	public static void neq(Instruction instruction){
		RegisterAddressingMode raMode = (RegisterAddressingMode) instruction;
		byte Ra= registers.getRegister(raMode.getRegA());
		byte Rb= registers.getRegister(raMode.getRegB());
		if(Ra!=Rb){
			condition.setBitCondition(true);
		}
		else{
			condition.setBitCondition(false);
		}
	}
	
	/**
	 * No Operation
	 */
	public static void nop(){
		// Do nothing
	}
	
	/**
	 * Stop Operation. It stops the processor	
	 * 
	 * @param processor the processor that needs to be stopped
	 */
	public static void stop(Processor processor){
		if(processor.isRunning()) {
			processor.setRun(false);
		}
	}
	
}
