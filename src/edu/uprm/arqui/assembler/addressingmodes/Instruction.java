package edu.uprm.arqui.assembler.addressingmodes;

/**
 * Created by cesarcruz on 9/9/14.
 */
public interface Instruction {

    public void setMnemonic(String mnemonic);

    public String getMnemonic();

    public String getAddressingMode();

    public String getInstructionType();

    public void setInstructionType(String type);

}
