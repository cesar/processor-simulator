package edu.uprm.arqui.processor;

import edu.uprm.arqui.assembler.Dissasembler;
import edu.uprm.arqui.assembler.addressingmodes.Instruction;
import edu.uprm.arqui.instructions.ArithmeticInstructions;
import edu.uprm.arqui.instructions.ControlProgramInstructions;
import edu.uprm.arqui.instructions.DataMoveInstructions;
import edu.uprm.arqui.instructions.LogicInstructions;
import edu.uprm.arqui.io.IOPorts;
import edu.uprm.arqui.memory.Memory;
import edu.uprm.arqui.register.InstructionRegister;
import edu.uprm.arqui.register.MainRegisters;
import edu.uprm.arqui.register.ProgramCounterRegister;

import java.util.List;

/**
 * Created by cesarcruz on 9/8/14.
 */
public class Processor {

    public static int OPCODE_SIZE = 5;

    public static int GENERAL_REGISTER_SIZE = 8;

    public static int NUMBER_GPR = 8;

    public static int MEMORY_CELL_SIZE = 8;

    public static int MEMORY_SIZE = 256;
    
    public static int IR_SIZE = 16;

    public static int PC_SIZE = 11;

    public static String DIRECT_ADDRESSING_MODE = "direct";

    public static String REGISTER_ADDRESSING_MODE = "register";

    public static String RELATIVE_ADDRESSING_MODE = "relative";

    public static String ARITHMETIC_INSTRUCTION = "arithmetic";

    public static String LOGIC_INSTRUCTION = "logic";

    public static String CONTROL_PROGRAM_INSTRUCTION = "control";

    public static String DATA_MOVE_INSTRUCTION = "data_move";

    private Memory memory;
    
    private MainRegisters registers;
    
    private InstructionRegister ir;
    
    private ProgramCounterRegister pc;
    
    private IOPorts ports;
    
    private Instruction instruction;
    
    private boolean running;
    

    public Processor() {
    	initializeComponents();
    }
    
    private void initializeComponents() {
    	memory = Memory.getInstance();
    	registers = MainRegisters.getInstance();
    	ir = InstructionRegister.getInstance();
    	pc = ProgramCounterRegister.getInstance();
    	ports = new IOPorts();
    	running = false;
    }
    
    public void step() {
    	fetch();
    	decodeAndExecute();

    }

    
    private void fetch() {
    	ir.getNextInstruction();
    	pc.increaseCounter();
    }
    
    private void decodeAndExecute() throws UnsupportedOperationException {
    	instruction = Dissasembler.dissasembleInstruction(ir.getValue());
    	String instructionType = instruction.getInstructionType();
    	
    	if(instructionType.equals(ARITHMETIC_INSTRUCTION)) {
    		this.executeArithmetic();
    	} else if(instructionType.equals(LOGIC_INSTRUCTION)) {
    		this.executeLogic();
    	} else if(instructionType.equals(CONTROL_PROGRAM_INSTRUCTION)) {
    		this.executeControlProgram();
    	} else if(instructionType.equals(DATA_MOVE_INSTRUCTION)) {
    		this.executeDataMove();
    	} else {
    		throw new UnsupportedOperationException("Operation is not supported");
    	}
    }
    
    private void executeArithmetic() {
    	switch (instruction.getMnemonic()) {
		case "add":
			ArithmeticInstructions.add(instruction);
			break;
		case "sub":
			ArithmeticInstructions.sub(instruction);
			break;
		case "adi":
			ArithmeticInstructions.adi(instruction);
			break;
		case "sbi":
			ArithmeticInstructions.sbi(instruction);
			break;
		default:
			break;
		}
    }
    
    private void executeLogic() {
    	switch (instruction.getMnemonic()) {
		case "and":
			LogicInstructions.and(instruction);
			break;
		case "or":
			LogicInstructions.or(instruction);
			break;
		case "xor":
			LogicInstructions.xor(instruction);
			break;
		case "not":
			LogicInstructions.not(instruction);
			break;
		case "neg":
			LogicInstructions.neg(instruction);
			break;
		case "shr":
			LogicInstructions.shr(instruction);
			break;
		case "shl":
			LogicInstructions.shl(instruction);
			break;
		case "rtr":
			LogicInstructions.rtr(instruction);
			break;
		case "rtl":
			LogicInstructions.rtl(instruction);
			break;
		default:
			break;
		}
    }
    
    private void executeControlProgram() {
    	switch (instruction.getMnemonic()) {
		case "jmpr":
			ControlProgramInstructions.jmpr(instruction);
			break;
		case "jmpa":
			ControlProgramInstructions.jmpa(instruction);
			break;
		case "jcr":
			ControlProgramInstructions.jcr(instruction);
			break;
		case "jca":
			ControlProgramInstructions.jca(instruction);
			break;
		case "loop":
			ControlProgramInstructions.loop(instruction);
			break;
		case "gr":
			ControlProgramInstructions.gr(instruction);
			break;
		case "gre":
			ControlProgramInstructions.gre(instruction);
			break;
		case "eq":
			ControlProgramInstructions.eq(instruction);
			break;
		case "neq":
			ControlProgramInstructions.neq(instruction);
			break;
		case "nop":
			ControlProgramInstructions.nop();
			break;
		case "stop":
			ControlProgramInstructions.stop(this); // Passing the context
			break;
		default:
			break;
		}
    }
    
    private void executeDataMove() {
    	switch (instruction.getMnemonic()) {
		case "ld":
			DataMoveInstructions.ld(instruction);
			break;
		case "ldi":
			DataMoveInstructions.ldi(instruction);
			break;
		case "ldacc":
			DataMoveInstructions.ldacc(instruction);
			break;
		case "st":
			DataMoveInstructions.st(instruction);
			break;
		case "stacc":
			DataMoveInstructions.stacc(instruction);
			break;
		case "ldr":
			DataMoveInstructions.ldr(instruction);
			break;
		case "str":
			DataMoveInstructions.str(instruction);
			break;
		default:
			break;
		}
    }
    
    public void setRun(boolean value) {
    	running = value;
    }
    
    public boolean isRunning() {
    	return running;
    }
    
}
