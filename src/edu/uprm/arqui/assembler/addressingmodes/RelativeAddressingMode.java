package edu.uprm.arqui.assembler.addressingmodes;

/**
 * Created by cesarcruz on 9/9/14.
 */
public class RelativeAddressingMode implements Instruction{

    /**
     * Addressing mode
     */
    private static String addressingMode = "relative";

    /**
     * Field containing the mnemonic of the instruction
     */
    private String mnemonic;

    /**
     * Field containing the operand of the instruction
     */
    private int operand;

    /**
     * Get the mneumonic of the instruction
     * @return
     *
     */
    public String getMnemonic() {
        return mnemonic;
    }

    /**
     * Set the mneumonic of the instruction
     * @param mnemonic
     */
    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    /**
     * Get the operand of the instrcuton
     * @return
     */
    public int getOperand() {
        return operand;
    }

    /**
     * Set the operand of the instruction
     * @param operand
     */
    public void setOperand(int operand) {
        this.operand = operand;
    }

    @Override
    public String getAddressingMode() {
        return this.addressingMode;
    }
}
