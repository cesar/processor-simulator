package edu.uprm.arqui.register;

/**
 * Created by cesarcruz on 9/3/14.
 */
public class ProgramCounterRegister {

    private static Register contents;

    public ProgramCounterRegister() {
       this.contents = new Register("PC", 11, 0);
    }

    public int getContents() {
        return this.contents.getContents();
    }

    //Not sure how I would modify this method, seems iffy
    public void setContents(Register contents) {
        this.contents = contents;
    }

    public void increaseCounter(){
       this.contents.setContents(this.contents.getContents() + 2);
    }
}
