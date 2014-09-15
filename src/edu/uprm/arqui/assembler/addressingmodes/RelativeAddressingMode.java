package edu.uprm.arqui.assembler.addressingmodes;

import edu.uprm.arqui.util.NumberUtils;

/**
 * Created by cesarcruz on 9/9/14.
 */
public class RelativeAddressingMode implements Instruction {

    /**
     * Addressing mode
     */
    private static String addressingMode = "relative";

    /**
     * Field containing type of instruction
     */
    private String type;

    /**
     * Field containing the mnemonic of the instruction
     */
    private String mnemonic;

    /**
     * Field containing the operand of the instruction
     */
    private int operand;

    /**
     * Get the mnemonic of the instruction
     *
     * @return
     */
    public String getMnemonic() {
        return mnemonic;
    }

    /**
     * Set the mnemonic of the instruction
     *
     * @param mnemonic
     */
    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
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
     * Get the LSB of operand of the instruction
     *
     * @return
     */
    public int getOperandLSB(){
        return NumberUtils.getUnsignedValueOf(operand, 3, 10, 11);
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
    public String getAddressingMode() {
        return this.addressingMode;
    }

    @Override
    public String getInstructionType() {
        return this.type;
    }

    @Override
    public void setInstructionType(String type) {
        this.type = type;
    }
}
