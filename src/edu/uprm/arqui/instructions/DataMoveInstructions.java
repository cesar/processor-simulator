package edu.uprm.arqui.instructions;

import edu.uprm.arqui.assembler.Dissasembler;
import edu.uprm.arqui.assembler.InstructionBuilder;
import edu.uprm.arqui.assembler.addressingmodes.DirectAddressingMode;
import edu.uprm.arqui.assembler.addressingmodes.RegisterAddressingMode;
import edu.uprm.arqui.assembler.addressingmodes.RelativeAddressingMode;
import edu.uprm.arqui.memory.Memory;
import edu.uprm.arqui.register.InstructionRegister;
import edu.uprm.arqui.register.MainRegisters;


/**
 * Created by cesarcruz on 9/3/14.
 */
public class DataMoveInstructions {
	
	//Operation: R[ra] <- [mem]
	
	public static void LD(){
		Memory mem= Memory.getInstance();
		DirectAddressingMode instruction= (DirectAddressingMode) Dissasembler.dissasembleInstruction();
		MainRegisters Reg= MainRegisters.getInstance();
		byte data= (byte) mem.getData(instruction.getOperand(), 2);//no estoy segura en los cells to fetch
	    int location= instruction.getRegA();
	    Reg.setRegister(data, location);
		
	}
	
	//Operation: R[ra] <- cons
	
	public static void LDI(){
		MainRegisters Reg= MainRegisters.getInstance();
		DirectAddressingMode instruction= (DirectAddressingMode) Dissasembler.dissasembleInstruction();
		byte data= (byte) instruction.getOperand();
	    int location= instruction.getRegA();
	    Reg.setRegister(data, location);
	}
	
	//Operation: R[1] <- cons
	
	public static void LDACC(){
		MainRegisters Reg= MainRegisters.getInstance();
		RelativeAddressingMode instruction= (RelativeAddressingMode) Dissasembler.dissasembleInstruction();
		byte data= (byte) instruction.getOperand();
	    Reg.setRegister(data, 1);
	}
	
	//Operation: [mem] <- R[ra]
	public static void ST(){
		Memory mem= Memory.getInstance();
		MainRegisters Reg= MainRegisters.getInstance();
		DirectAddressingMode instruction= (DirectAddressingMode) Dissasembler.dissasembleInstruction();
		byte data= (byte)Reg.getRegister(instruction.getRegA()) ;
	    int location=instruction.getOperand() ;
		mem.setDataAt(location, data);
	}
	
	//Operation: [mem] <- R[1]
	public static void STACC(){
		Memory mem= Memory.getInstance();
		MainRegisters Reg= MainRegisters.getInstance();
		RelativeAddressingMode instruction= (RelativeAddressingMode) Dissasembler.dissasembleInstruction();
		byte data= (byte) Reg.getRegister(1);
	    int location=instruction.getOperand() ;
		mem.setDataAt(location, data);
	}
	
	//Operation: R[Ra] <- mem[R[Rb]]
	public static void LDR(){
		Memory mem= Memory.getInstance();
		MainRegisters Reg= MainRegisters.getInstance();
		RegisterAddressingMode instruction= (RegisterAddressingMode) Dissasembler.dissasembleInstruction();
		byte Mlocation= (byte) Reg.getRegister(instruction.getRegB());
		byte data= (byte) mem.getData(Mlocation, 1);//cells?
		byte Rlocation= (byte) instruction.getRegA();
	    Reg.setRegister(data, Rlocation);	
	}
	
	//Operation: R[Rb] <- mem[R[Ra]]
	public static void STR(){
		Memory mem= Memory.getInstance();
		MainRegisters Reg= MainRegisters.getInstance();
		RegisterAddressingMode instruction= (RegisterAddressingMode) Dissasembler.dissasembleInstruction();
		byte Mlocation= (byte) Reg.getRegister(instruction.getRegA());
		byte data= (byte) mem.getData(Mlocation, 1);//cells?
		byte Rlocation= (byte) instruction.getRegB();
	    Reg.setRegister(data, Rlocation);	
	}
}
