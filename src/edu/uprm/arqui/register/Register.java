package edu.uprm.arqui.register;

/**
 * Created by cesarcruz on 9/3/14.
 *
 * This class represents an actual register of variable length,
 * the register contents is stored in an integer value.
 * The length instance signifies the size of the register.
 */
public class Register {

    public Register(String id, int length, int contents) {
        this.id = id;
        this.contents = contents;
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getContents() {
        return contents;
    }

    public void setContents(int contents) {
        this.contents = contents;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    private String id;

    private int contents;

    private int length;


}
