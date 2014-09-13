package edu.uprm.arqui.instructions;

import edu.uprm.arqui.assembler.Dissasembler;
import edu.uprm.arqui.assembler.addressingmodes.Instruction;
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
	
	public static void and(Instruction instruction){
		RegisterAddressingMode raMode= (RegisterAddressingMode) instruction;
		byte Rb= Reg.getRegister(raMode.getRegB());
		byte Rc= Reg.getRegister(raMode.getRegC());
		byte Ra= (byte)(Rb & Rc); //AND operation
		byte location= Reg.getRegister(raMode.getRegA());
		Reg.setRegister(Ra, location);
	}
	
	//Operation: R[ra]<= R[rb]+R[rc]
	
	public static void or(Instruction instruction){
		RegisterAddressingMode raMode= (RegisterAddressingMode) instruction;
		byte Rb= Reg.getRegister(raMode.getRegB());
		byte Rc= Reg.getRegister(raMode.getRegC());
		byte Ra= (byte)(Rb | Rc); //OR operation
		byte location= Reg.getRegister(raMode.getRegA());
		Reg.setRegister(Ra, location);
	}
	
	//Operation: R[ra]<= R[rb] (+) R[rc]
	
	public static void xor(Instruction instruction){
		RegisterAddressingMode raMode= (RegisterAddressingMode) instruction;
		byte Rb= Reg.getRegister(raMode.getRegB());
		byte Rc= Reg.getRegister(raMode.getRegC());
		byte Ra= (byte)(~Rb&Rc|Rb&~Rc); //XOR operation: A'B + AB'
		byte location= Reg.getRegister(raMode.getRegA());
		Reg.setRegister(Ra, location);
	}
	
	//Operation: R[ra]<= (not)~R[rb]
	
	public static void not(Instruction instruction){
		RegisterAddressingMode raMode= (RegisterAddressingMode) Dissasembler.dissasembleInstruction();
		byte Rb= Reg.getRegister(raMode.getRegB());
		byte Ra= (byte)(~Rb); //OR operation: 
		byte location= Reg.getRegister(raMode.getRegA());
		Reg.setRegister(Ra, location);
	}
	
	//Operation: R[ra]<= - R[rb]
	
	public static void neg(Instruction instruction){
		RegisterAddressingMode raMode= (RegisterAddressingMode) Dissasembler.dissasembleInstruction();
		byte Rb= Reg.getRegister(raMode.getRegB());
		byte Ra= (byte)(~Rb+ 0x01); //NEG operation: 
		byte location= Reg.getRegister(raMode.getRegA());
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
