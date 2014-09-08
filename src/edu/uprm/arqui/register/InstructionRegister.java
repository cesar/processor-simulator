package edu.uprm.arqui.register;

import edu.uprm.arqui.memory.Memory;

/**
 * Created by cesarcruz on 9/3/14.
 */
public class InstructionRegister {

    /**
     * Instruction Register field
     */
    private int ir;

    /**
     * Instruction register instance
     */
    private static InstructionRegister instance = null;

    /**
     * Private constructor
     */
    private InstructionRegister(){
        this.ir = 0;
    }

    /**
     * Returns an instance of the instruction register,
     * if the instance does not exists, creates one.
     * @return
     */
    public static InstructionRegister getInstance(){
        if(instance == null){
            return new InstructionRegister();
        } else {
            return instance;
        }
    }

    /**
     * Fetches the next instruction as indicated by the program counter.
     */
    public void getNextInstruction(){

        ProgramCounterRegister pc = ProgramCounterRegister.getInstance();

        Memory mem = Memory.getInstance();

        this.ir = mem.getData(pc.getPc().getContents(), 2); //fetch two cells, a word

    }

}
