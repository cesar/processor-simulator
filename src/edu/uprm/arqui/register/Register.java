package edu.uprm.arqui.register;

/**
 * Created by cesarcruz on 9/3/14.
 */
public class Register {

    private int length;

    private String id;

    private short contents;

    public Register(int length, String id) {
        this.length = length;
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public short getContents() {
        return contents;
    }

    public void setContents(short contents) {
        this.contents = contents;
    }
}
