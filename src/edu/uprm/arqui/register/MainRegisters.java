package edu.uprm.arqui.register;

/**
 * Created by cesarcruz on 9/3/14.
 */
public class MainRegisters {

    private Register[] registers;

    public MainRegisters(){

        this.registers = new Register[8];

        for(int i = 0; i < 8; i++) {
            this.registers[i] = new Register("R" + i,8, 0);
        }
    }

    public Register getRegister(int address){
        return this.registers[address];
    }

    public void setRegister(int address, Register register) {
        if(register.getLength() <= 8) {
            this.registers[address] = register;
        }
    }
}
