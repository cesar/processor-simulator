package edu.uprm.arqui.register;

/**
 * Created by cesarcruz on 9/3/14.
 * Program counter class, follows lazy Singleton instantiation
 */
public class ProgramCounterRegister {

    /**
     * Program Counter field
     */
    private int pc;

    /**
     * Program counter instance
     */
    private ProgramCounterRegister instance = null;

    /**
     * Private constructor
     */
    private ProgramCounterRegister() {

        this.pc = 0;
    }

    /**
     * Method to get the ProgramCounter instance,
     * if there is no instance, create a new one.
     * @return
     */
    public static ProgramCounterRegister getInstance(){
        if(instance == null){
            return new ProgramCounterRegister();
        } else {
            return instance;
        }
    }

    /**
     * Get the program counter
     * @return int representation of the program counter
     */
    public int getPc() {
        return this.pc;
    }

    /**
     * Set the contents of the program counter
     * @param int to place in program counter
     */
    public void setPc(int pc) {
        this.pc = pc;
    }

    /**
     * Increase the program counter by two,
     * to represent the location of the next instruction in memory
     */
    public void increaseCounter(){
       setPc(getPc() + 2);
    }
}
