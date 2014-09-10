package edu.uprm.arqui.assembler;

/**
 * Created by cesarcruz on 9/9/14.
 */
public class Specification {

    /**
     * Addressing mode of instruction
     */
    private String addressingMode;

    /**
     * Mnemonic of instruction
     */
    private String mnemonic;

    /**
     * Type of instruction,
     */
    private String type;

    public Specification(String addressingMode, String mnemonic, String type) {
        this.addressingMode = addressingMode;

        this.mnemonic = mnemonic;

        this.type = type;
    }


    /**
     * Get the addressing mode of an instruction
     *
     * @return
     */
    public String getAddressingMode() {
        return addressingMode;
    }

    /**
     * Set the addressing mode of an instruction
     *
     * @param addressingMode
     */
    public void setAddressingMode(String addressingMode) {
        this.addressingMode = addressingMode;
    }

    /**
     * Get the mnemonic of an instruction
     *
     * @return
     */
    public String getMnemonic() {
        return mnemonic;
    }

    /**
     * Set the mnemonic of an instruction
     *
     * @param mnemonic
     */
    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    /**
     * Get the type of an instruction
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of an instruction
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}

