package edu.uprm.arqui.assembler;

import edu.uprm.arqui.assembler.addressingmodes.Instruction;
import edu.uprm.arqui.processor.Processor;
import edu.uprm.arqui.register.InstructionRegister;
import edu.uprm.arqui.util.NumberUtils;

/**
 * Created by cesarcruz on 9/8/14.
 */
public class Dissasembler {
	public static Instruction dissasembleInstruction(){


    InstructionRegister ir = InstructionRegister.getInstance();

    int irContents = ir.getValue();

    Instruction instruction = InstructionBuilder.build(irContents);

    return instruction;
    }
}