package edu.uprm.arqui.register;

import edu.uprm.arqui.processor.Processor;

/**
 * Created by cesarcruz on 9/3/14.
 */
public class MainRegisters {

    /**
     * Main registers
     */
   private byte registers[];

    /**
     * Main register instance
     */
   private static MainRegisters instance = null;

    /**
     * Private constructor
     */
   private MainRegisters(){
       this.registers = new byte[8];

       for(int i = 0; i < 8; i ++){
           this.registers[i] = 0;
       }
   }

    /**
     * Returns an instance of the main registers,
     * if the instance does not exists, creates one.
     * @return
     */
   public static MainRegisters getInstance(){
       if(instance == null){
           return new MainRegisters();
       } else {
           return instance;
       }
   }

    /**
     * Get the register specifies by the location
     * @param location
     * @return register byte
     */
   public byte getRegister(int location){
       return this.registers[location];
   }

    /**
     * Set register contents based on location
     * @param val
     * @param location
     */
   public void setRegiter(byte val, int location){

       if(location < Processor.GENERAL_REGISTER_SIZE){
           this.registers[location] = val;
       }
       //Throw exceptions here
   }
}
