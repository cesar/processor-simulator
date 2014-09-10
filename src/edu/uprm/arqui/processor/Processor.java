package edu.uprm.arqui.processor;

/**
 * Created by cesarcruz on 9/8/14.
 */
public class Processor {

    public static int OPCODE_SIZE = 5;
    
    public static int GENERAL_REGISTER_SIZE = 8;
    
    public static int NUMBER_GPR = 8;

    public static int MEMORY_CELL_SIZE = 8;

    public static int MEMORY_SIZE = 256;

    public static String DIRECT_ADDRESSING_MODE = "direct";

    public static String REGISTER_ADDRESSING_MODE = "register";

    public static String RELATIVE_ADDRESSING_MODE = "relative";

    public static String ARITHMETIC_INSTRUCTION = "arithmetic";

    public static String LOGIC_INSTRUCTION = "logic";

    public static String CONTROL_PROGRAM_INSTRUCTION = "control";

    public static String DATA_MOVE_INSTRUCTION = "data_move";
    
    public static int IR_SIZE = 16;
    
    
}
