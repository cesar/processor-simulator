package edu.uprm.arqui.assembler.addressingmodes;

/**
 * Created by cesarcruz on 9/9/14.
 */
public class DirectAddressingMode implements Instruction {

    /**
     * Addressing mode
     */
    private static String addressingMode = "direct";
    /**
     * Field containing instruction mneumonic
     */
    private String mnemonic;

    /**
     * Field containing register A address
     */
    private int regA;

    /**
     * Field containing memory address or constant value
     */
    private int operand;

    /**
     * Field containing instruction type
     */
    private String type;

    /**
     * Get the mnemonic of the instruction
     *
     * @return
     */
    public String getMnemonic() {
        return mnemonic;
    }

    @Override
    public String getAddressingMode() {
        return this.addressingMode;
    }

    /**
     * Set the mneumonic of an instruction
     *
     * @param mnemonic
     */
    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    /**
     * Get the address of the A register
     *
     * @return
     */
    public int getRegA() {
        return regA;
    }

    /**
     * Set the address of the A register
     *
     * @param regA
     */
    public void setRegA(int regA) {
        this.regA = regA;
    }

    /**
     * Get the operand of the instruction
     *
     * @return
     */
    public int getOperand() {
        return operand;
    }

    /**
     * Set the operand of the instruction
     *
     * @param operand
     */
    public void setOperand(int operand) {
        this.operand = operand;
    }

    @Override
    public String getInstructionType() {
        return type;
    }

    @Override
    public void setInstructionType(String type) {
        this.type = type;
    }
}
