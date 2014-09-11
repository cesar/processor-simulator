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

    public static Instruction build(int ir) {
        int opcode = NumberUtils.getUnsignedValueOf(ir, 0, Processor.OPCODE_SIZE - 1, Processor.IR_SIZE);

        Specification specs = InstructionTable.getSpecification(opcode);

        if (specs.getAddressingMode() == Processor.REGISTER_ADDRESSING_MODE) {
            return registerInstructionBuilder(specs);

        } else if (specs.getAddressingMode() == Processor.DIRECT_ADDRESSING_MODE) {
            return directInstructionBuilder(specs);
        } else if (specs.getAddressingMode() == Processor.RELATIVE_ADDRESSING_MODE) {
            return relativeInstructionBuilder(specs);
        } else {
            throw new UnsupportedOperationException();
        }

    }

    private static RegisterAddressingMode registerInstructionBuilder(Specification specs) {
        RegisterAddressingMode instruction = new RegisterAddressingMode();

        InstructionRegister ir = InstructionRegister.getInstance();

        int regA = NumberUtils.getUnsignedValueOf(ir.getValue(), Processor.OPCODE_SIZE, Processor.OPCODE_SIZE + 2, Processor.IR_SIZE);

        int regB = NumberUtils.getUnsignedValueOf(ir.getValue(), Processor.OPCODE_SIZE + 3, Processor.OPCODE_SIZE + 5, Processor.IR_SIZE);

        int regC = NumberUtils.getUnsignedValueOf(ir.getValue(), Processor.OPCODE_SIZE + 6, Processor.OPCODE_SIZE + 8, Processor.IR_SIZE);

        instruction.setRegA(regA);

        instruction.setRegB(regB);

        instruction.setRegC(regC);

        instruction.setMnemonic(specs.getMnemonic());

        instruction.setInstructionType(specs.getType());

        return instruction;
    }

    private static DirectAddressingMode directInstructionBuilder(Specification specs) {
        DirectAddressingMode instruction = new DirectAddressingMode();

        InstructionRegister ir = InstructionRegister.getInstance();

        int regA = NumberUtils.getUnsignedValueOf(ir.getValue(), Processor.OPCODE_SIZE, Processor.OPCODE_SIZE + 2, Processor.IR_SIZE);

        int operand = NumberUtils.getUnsignedValueOf(ir.getValue(), Processor.OPCODE_SIZE + 3, 15, Processor.IR_SIZE);

        instruction.setRegA(regA);

        instruction.setOperand(operand);

        instruction.setMnemonic(specs.getMnemonic());

        instruction.setInstructionType(specs.getType());

        return instruction;

    }

    private static RelativeAddressingMode relativeInstructionBuilder(Specification specs) {

        RelativeAddressingMode instruction = new RelativeAddressingMode();

        InstructionRegister ir = InstructionRegister.getInstance();

        int operand = NumberUtils.getUnsignedValueOf(ir.getValue(), Processor.OPCODE_SIZE, 15, Processor.IR_SIZE);

        instruction.setOperand(operand);

        instruction.setMnemonic(specs.getMnemonic());

        instruction.setInstructionType(specs.getType());

        return instruction;
    }
}
