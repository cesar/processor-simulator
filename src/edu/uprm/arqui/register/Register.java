package edu.uprm.arqui.register;

import java.util.Arrays;

/**
 * Created by cesarcruz on 9/3/14.
 */
public class Register {

    private char[] bits;

    public Register(char[] bits) {
        this.bits = bits;
    }

    public char[] getBits() {
        return bits;
    }

    public void setBits(char[] bits) {
        this.bits = bits;
    }
}
