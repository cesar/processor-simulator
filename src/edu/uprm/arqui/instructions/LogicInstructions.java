package edu.uprm.arqui.instructions;

import edu.uprm.arqui.assembler.Dissasembler;
import edu.uprm.arqui.assembler.addressingmodes.RegisterAddressingMode;
import edu.uprm.arqui.memory.Memory;
import edu.uprm.arqui.register.MainRegisters;

/**
 * Created by cesarcruz on 9/3/14.
 */
public class LogicInstructions {
	
	private static Memory memory = Memory.getInstance();
	private static MainRegisters Reg = MainRegisters.getInstance();
	//Operation: R[ra]<= R[rb]*R[rc]
	
	public static void and(){
		RegisterAddressingMode instruction= (RegisterAddressingMode) Dissasembler.dissasembleInstruction();
		byte Rb= Reg.getRegister(instruction.getRegB());
		byte Rc= Reg.getRegister(instruction.getRegC());
		byte Ra= (byte)(Rb & Rc); //AND operation
		byte location= Reg.getRegister(instruction.getRegA());
		Reg.setRegister(Ra, location);
	}
	
	//Operation: R[ra]<= R[rb]+R[rc]
	
	public static void or(){
		RegisterAddressingMode instruction= (RegisterAddressingMode) Dissasembler.dissasembleInstruction();
		byte Rb= Reg.getRegister(instruction.getRegB());
		byte Rc= Reg.getRegister(instruction.getRegC());
		byte Ra= (byte)(Rb | Rc); //OR operation
		byte location= Reg.getRegister(instruction.getRegA());
		Reg.setRegister(Ra, location);
	}
	
	//Operation: R[ra]<= R[rb] (+) R[rc]
	
	public static void xor(){
		RegisterAddressingMode instruction= (RegisterAddressingMode) Dissasembler.dissasembleInstruction();
		byte Rb= Reg.getRegister(instruction.getRegB());
		byte Rc= Reg.getRegister(instruction.getRegC());
		byte Ra= (byte)(~Rb&Rc|Rb&~Rc); //XOR operation: A'B + AB'
		byte location= Reg.getRegister(instruction.getRegA());
		Reg.setRegister(Ra, location);
	}
	
	//Operation: R[ra]<= (not)~R[rb]
	
	public static void not(){
		RegisterAddressingMode instruction= (RegisterAddressingMode) Dissasembler.dissasembleInstruction();
		byte Rb= Reg.getRegister(instruction.getRegB());
		byte Ra= (byte)(~Rb); //OR operation: 
		byte location= Reg.getRegister(instruction.getRegA());
		Reg.setRegister(Ra, location);
	}
	
	//Operation: R[ra]<= - R[rb]
	
	public static void neg(){
		RegisterAddressingMode instruction= (RegisterAddressingMode) Dissasembler.dissasembleInstruction();
		byte Rb= Reg.getRegister(instruction.getRegB());
		byte Ra= (byte)(~Rb+ 0x01); //NEG operation: 
		byte location= Reg.getRegister(instruction.getRegA());
		Reg.setRegister(Ra, location);
	}
	//Estos los hago ya mismo...pq tengo clase ahora 
	//Operation: R[ra]<= R[rb] shr R[rc]
	
	public static void shr(){}
	
	//Operation: R[ra]<= R[rb] shl R[rc]
	
	public static void shl(){}
	
	//Operation: R[ra]<= R[rb] rtr R[rc]
	
	public static void rtr(){}
	
	//Operation: R[ra]<= R[rb] rtl R[rc]
	
	public static void rtl(){}
}
