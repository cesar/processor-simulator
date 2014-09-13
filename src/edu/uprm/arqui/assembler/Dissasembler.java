package edu.uprm.arqui.assembler;

import edu.uprm.arqui.assembler.addressingmodes.Instruction;
import edu.uprm.arqui.register.InstructionRegister;

/**
 * Created by cesarcruz on 9/8/14.
 */
public class Dissasembler {

	public static Instruction dissasembleInstruction(int ir) {
		
		Instruction instruction = InstructionBuilder.build(ir);

		return instruction;
    }
}
