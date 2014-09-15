package edu.uprm.arqui.instructions;

import edu.uprm.arqui.assembler.addressingmodes.Instruction;
import edu.uprm.arqui.assembler.addressingmodes.RegisterAddressingMode;
import edu.uprm.arqui.memory.Memory;
import edu.uprm.arqui.register.MainRegisters;
/**
 * Created by cesarcruz on 9/3/14.
 */
public class LogicInstructions {
	
	private static Memory memory = null;
	private static MainRegisters registers = null;


    /**
     * Perform logic and on the values of RB and RC,
     * store contents on RA
     * @param instruction
     */
	public static void and(Instruction instruction){

        init();

		RegisterAddressingMode inst = (RegisterAddressingMode) instruction;

        int rb = registers.getRegister(inst.getRegB());

        int rc = registers.getRegister(inst.getRegC());

        byte ra = (byte)(rb & rc);

        registers.setRegister(ra, inst.getRegA());
	}

    /**
     *  Perform bitwise OR on contents of register B and C, store result in register A
      * @param instruction
     */
	public static void or(Instruction instruction){
        init();

		RegisterAddressingMode inst = (RegisterAddressingMode) instruction;

		int rb = registers.getRegister(inst.getRegB());

        int rc= registers.getRegister(inst.getRegC());

        byte ra = (byte)(rb | rc); //OR operation

		registers.setRegister(ra, inst.getRegA());
	}

    /**
     * Perform logical XOR on contents of register B and C, store contents on register A
      * @param instruction
     */
	public static void xor(Instruction instruction){

        init();

		RegisterAddressingMode inst = (RegisterAddressingMode) instruction;
		byte rb= registers.getRegister(inst.getRegB());
		byte rc= registers.getRegister(inst.getRegC());
		byte ra= (byte)(~rb&rc|rb&~rc); //XOR operation: A'B + AB'
		registers.setRegister(ra, inst.getRegA());
	}

    /**
     * Get two's complement store in register A
     * @param instruction
     */
	public static void not(Instruction instruction){

        init();
        RegisterAddressingMode inst = (RegisterAddressingMode) instruction;
		byte Rb= registers.getRegister(inst.getRegB());
		byte Ra= (byte)(~Rb); //OR operation: 

		registers.setRegister(Ra, inst.getRegA());
	}

    /**
     * Get negation, store in register A
     * @param instruction
     */
	public static void neg(Instruction instruction){
        init();
        RegisterAddressingMode inst = (RegisterAddressingMode) instruction;
		byte Rb= registers.getRegister(inst.getRegB());
		byte Ra= (byte)(~Rb+ 0x01); //NEG operation: 
		registers.setRegister(Ra, inst.getRegA());
	}

    /**
     * Bitwise shift bits in register B to the right by amount specified in register C,
     * store results in register A
     * @param instruction
     */
	public static void shr(Instruction instruction){
        init();

        RegisterAddressingMode inst = (RegisterAddressingMode) instruction;

        int rb = registers.getRegister(inst.getRegB());

        int rc = registers.getRegister(inst.getRegC());

        byte ra = (byte)(rb >>> rc);

        registers.setRegister(ra, inst.getRegA());
    }

    /**
     * Shift bits in register B to the left
     * by the amount indicated on register C,
     * store result in register A
      * @param instruction
     */
	public static void shl(Instruction instruction){
        init();

        RegisterAddressingMode inst = (RegisterAddressingMode) instruction;

        int rb = registers.getRegister(inst.getRegB());

        int rc = registers.getRegister(inst.getRegC());

        byte ra = (byte) (rb << rc);

        registers.setRegister(ra, inst.getRegA());
    }

    /**
     * Rotate bits in register B to the right
     * by the amount in register C and
     * store result in register A
      * @param instruction
     */
	public static void rtr(Instruction instruction){

        init();

        RegisterAddressingMode inst = (RegisterAddressingMode) instruction;

        int rb = registers.getRegister(inst.getRegB());

        int rc = registers.getRegister(inst.getRegC());

        byte ra = (byte) ((rb >>> rc) | (rb << (Integer.SIZE - rc)));

        registers.setRegister(ra, inst.getRegA());

    }

    /**
     * Rotate bits in register B to the right
     * by the amount in register C and store results in register A
     * @param instruction
     */
	public static void rtl(Instruction instruction){
        init();

        RegisterAddressingMode inst = (RegisterAddressingMode) instruction;

        int rb = registers.getRegister(inst.getRegB());

        int rc = registers.getRegister(inst.getRegC());

        //MAGIC
        byte ra = (byte) ((rb << rc) | (rb >>> (Integer.SIZE - rc)));

        registers.setRegister(ra, inst.getRegA());
    }

    /**
     * Initiate memory and registers
     */
    private static void init(){
        registers = MainRegisters.getInstance();

        memory = Memory.getInstance();
    }
}
