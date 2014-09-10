package edu.uprm.arqui.assembler.addressingmodes;

/**
 * Created by cesarcruz on 9/9/14.
 */
public class RegisterAddressingMode implements Instruction {


    /**
     * Field containing instruction types
     */
    private String type;
    /**
     * Addressing Mode
     */
    private static String addressingMode = "register";

    /**
     * Field containing instruction mnemonic
     */
    private String mnemonic;

    /**
     * Field containing location of register A
     */
    private int regA;

    /**
     * Field containing location of register B
     */
    private int regB;

    /**
     * Field containing location of register C
     */
    private int regC;

    public RegisterAddressingMode() {
        this.mnemonic = null;

        this.regA = 0;

        this.regB = 0;

        this.regC = 0;
    }

    /**
     * Get the mnemonic of the instruction
     * @return
     */
    public String getMnemonic() {
        return mnemonic;
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

    /**
     * Set the mneumonic of an instruction
     *
     * @param mnemonic
     */
    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    /**
     * Get the register A location
     *
     * @return
     */
    public int getRegA() {
        return regA;
    }

    /**
     * Set the register A location
     *
     * @param regA
     */
    public void setRegA(int regA) {
        this.regA = regA;
    }

    /**
     * Get the register B location
     *
     * @return
     */
    public int getRegB() {
        return regB;
    }

    /**
     * Set the register B location
     *
     * @param regB
     */
    public void setRegB(int regB) {
        this.regB = regB;
    }

    /**
     * Get the register C location
     *
     * @return
     */
    public int getRegC() {
        return regC;
    }

    /**
     * Set the register C location
     *
     * @param rebC
     */
    public void setRegC(int rebC) {
        this.regC = rebC;
    }


}
