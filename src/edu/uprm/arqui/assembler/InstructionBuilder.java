package edu.uprm.arqui.assembler;

import edu.uprm.arqui.assembler.addressingmodes.DirectAddressingMode;
import edu.uprm.arqui.assembler.addressingmodes.Instruction;
import edu.uprm.arqui.assembler.addressingmodes.RegisterAddressingMode;
import edu.uprm.arqui.assembler.addressingmodes.RelativeAddressingMode;
import edu.uprm.arqui.processor.Processor;
import edu.uprm.arqui.register.InstructionRegister;
import edu.uprm.arqui.util.NumberUtils;

/**
 * Created by cesarcruz on 9/9/14.
 */
public class InstructionBuilder {

    public static Instruction build(int ir){
        int opcode = NumberUtils.getSignedValueOf(ir.getValue(), 0, Processor.OPCODE_SIZE - 1, 16);

        InstructionTable specs = InstructionTable.getInformation(opcode);

        if(specs.addressingMode == "register"){
            return registerInstructionBuilder(specs);

        } else if(specs.addressingMode == "direct"){
            return directInstructionBuilder(specs);
        } else if(specs.addressingMode == "relative"){
            return relativeInstructionBuilder();
        } else {
            throw new UnsupportedOperationException();
        }

    }

    private static RegisterAddressingMode registerInstructionBuilder(InstructionTable specs){

        InstructionRegister ir = InstructionRegister.getInstance();

        RegisterAddressingMode instruction = new RegisterAddressingMode();

        int regA = NumberUtils.getUnsignedValueOf(ir.getValue(), Processor.OPCODE_SIZE, Processor.OPCODE_SIZE + 2, 16);

        int regB = NumberUtils.getUnsignedValueOf(ir.getValue(), Processor.OPCODE_SIZE + 3, Processor.OPCODE_SIZE + 5, 16);

        int regC = NumberUtils.getUnsignedValueOf(ir.getValue(), Processor.OPCODE_SIZE + 6, Processor.OPCODE_SIZE + 8, 16);

        instruction.setRegA(regA);

        instruction.setRegB(regB);

        instruction.setRegC(regC);

        instruction.setMnemonic(specs.Mnemonic);

        instruction.setInstructionType(specs.Type);

        return instruction;
    }

    private static DirectAddressingMode directInstructionBuilder(InstructionTable specs){
        DirectAddressingMode instruction = new DirectAddressingMode();

        InstructionRegister ir = InstructionRegister.getInstance();

        int regA = NumberUtils.getUnsignedValueOf(ir.getValue(), Processor.OPCODE_SIZE, Processor.OPCODE_SIZE + 2, 16);

        int operand = NumberUtils.getUnsignedValueOf(ir.getValue(), Processor.OPCODE_SIZE + 3, 15, 16);

        instruction.setRegA(regA);

        instruction.setOperand(operand);

        instruction.setMnemonic(specs.getMnemonic());

        return instruction;

    }

    private static RelativeAddressingMode relativeInstructionBuilder(InstructionTable specs){

        RelativeAddressingMode instruction = new RelativeAddressingMode();

        InstructionRegister ir = InstructionRegister.getInstance();

        int operand = NumberUtils.getUnsignedValueOf(ir.getValue(), Processor.OPCODE_SIZE, 15, 16);

        instruction.setOperand(operand);

        instruction.setMnemonic(specs.getMnemonic);

        return instruction;
    }
}
