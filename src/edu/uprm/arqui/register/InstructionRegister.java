package edu.uprm.arqui.register;

import edu.uprm.arqui.instructions.Instruction;
import edu.uprm.arqui.memory.Memory;
import edu.uprm.arqui.register.ProgramCounterRegister;
/**
 * Created by janetmendoza on 9/7/14.
 */
public class InstructionRegister {
	  
	private int contents;
	private ProgramCounterRegister PC= new ProgramCounterRegister();
	private InstructionRegister IR= new InstructionRegister();
	
	 public InstructionRegister() {
	    new Register("IR", 16, 0);
	    }

	 public int getContents() {
	      return this.getContents();
	    }

	 public void setContents(int contents) {
	        this.contents = contents;
	    }
	 //no se si dejar este metodo en esta clase...???
	 public void InstructionCycle(){
		 int address= PC.getContents();
		 byte fetch=Memory.getDataAt(address);
		 Instruction instruction= new Instruction(fetch);
		 PC.increaseCounter();
		 IR.setContents(instruction.getInstruction());
		 int Opcode= instruction.getOpcode();
		/** if(Opcode==0123456..)
		 * redirect according to opcode to arithmetic,control program, logic
		 * and data move instructions
		 **/
		 
		 
	 }
}
