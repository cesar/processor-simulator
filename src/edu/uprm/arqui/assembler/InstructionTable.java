package edu.uprm.arqui.assembler;

import edu.uprm.arqui.processor.Processor;

import java.security.ProviderException;
import java.util.HashMap;

/**
 * Created by cesarcruz on 9/8/14.
 */
public class InstructionTable {

    public static Specification getSpecification(int opcode){
        switch(opcode){
            case 0: return new Specification(Processor.DIRECT_ADDRESSING_MODE, "LD", Processor.DATA_MOVE_INSTRUCTION);

            case 1: return new Specification(Processor.DIRECT_ADDRESSING_MODE, "LDI", Processor.DATA_MOVE_INSTRUCTION);

            case 2: return new Specification(Processor.RELATIVE_ADDRESSING_MODE, "LDACC", Processor.DATA_MOVE_INSTRUCTION);

            case 3: return new Specification(Processor.DIRECT_ADDRESSING_MODE, "ST", Processor.DATA_MOVE_INSTRUCTION);

            case 4: return new Specification(Processor.RELATIVE_ADDRESSING_MODE, "STACC", Processor.DATA_MOVE_INSTRUCTION);

            case 5: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "ldr", Processor.DATA_MOVE_INSTRUCTION);

            case 6: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "str", Processor.DATA_MOVE_INSTRUCTION);

            case 7: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "add", Processor.ARITHMETIC_INSTRUCTION);

            case 8: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "sub", Processor.ARITHMETIC_INSTRUCTION);

            case 9: return new Specification(Processor.DIRECT_ADDRESSING_MODE, "adi", Processor.ARITHMETIC_INSTRUCTION);

            case 10: return new Specification(Processor.DIRECT_ADDRESSING_MODE, "sbi", Processor.ARITHMETIC_INSTRUCTION);

            case 11: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "and", Processor.LOGIC_INSTRUCTION);

            case 12: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "or", Processor.LOGIC_INSTRUCTION);

            case 13: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "xor", Processor.LOGIC_INSTRUCTION);

            case 14: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "not", Processor.LOGIC_INSTRUCTION);

            case 15: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "neg", Processor.LOGIC_INSTRUCTION);

            case 16: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "shr", Processor.LOGIC_INSTRUCTION);

            case 17: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "shl", Processor.LOGIC_INSTRUCTION);

            case 18: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "rtr", Processor.LOGIC_INSTRUCTION);

            case 19: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "rtl", Processor.LOGIC_INSTRUCTION);

            case 20: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "jmpr", Processor.CONTROL_PROGRAM_INSTRUCTION);

            case 21: return new Specification(Processor.RELATIVE_ADDRESSING_MODE, "jmpa", Processor.CONTROL_PROGRAM_INSTRUCTION);

            case 22: return new Specification(Processor.RELATIVE_ADDRESSING_MODE, "jcr", Processor.CONTROL_PROGRAM_INSTRUCTION);

            case 23: return new Specification(Processor.RELATIVE_ADDRESSING_MODE, "jca", Processor.CONTROL_PROGRAM_INSTRUCTION);

            case 24: return new Specification(Processor.DIRECT_ADDRESSING_MODE, "loop", Processor.CONTROL_PROGRAM_INSTRUCTION);

            case 25: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "gr", Processor.CONTROL_PROGRAM_INSTRUCTION);

            case 26: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "gre", Processor.CONTROL_PROGRAM_INSTRUCTION);

            case 27: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "eq", Processor.CONTROL_PROGRAM_INSTRUCTION);

            case 28: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "neq", Processor.CONTROL_PROGRAM_INSTRUCTION);

            case 29: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "nop", Processor.CONTROL_PROGRAM_INSTRUCTION);

            case 30: return new Specification(Processor.REGISTER_ADDRESSING_MODE, "stop", Processor.CONTROL_PROGRAM_INSTRUCTION);

            default: return null;
        }
    }

    static class Specification {
        /**
         * Addressing mode of instruction
         */
        private String addressingMode;

        /**
         * Mnemonic of instruction
         */
        private String mnemonic;

        /**
         * Type of instruction,
         */
        private String type;

        private Specification(String addressingMode, String mnemonic, String type){
            this.addressingMode = addressingMode;

            this.mnemonic = mnemonic;

            this.type = type;
        }


        /**
         * Get the addressing mode of an instruction
         * @return
         */
        public String getAddressingMode() {
            return addressingMode;
        }

        /**
         * Set the addressing mode of an instruction
         * @param addressingMode
         */
        public void setAddressingMode(String addressingMode) {
            this.addressingMode = addressingMode;
        }

        /**
         * Get the mnemonic of an instruction
         * @return
         */
        public String getMnemonic() {
            return mnemonic;
        }

        /**
         * Set the mnemonic of an instruction
         * @param mnemonic
         */
        public void setMnemonic(String mnemonic) {
            this.mnemonic = mnemonic;
        }

        /**
         * Get the type of an instruction
         * @return
         */
        public String getType() {
            return type;
        }

        /**
         * Set the type of an instruction
         * @param type
         */
        public void setType(String type) {
            this.type = type;
        }
    }
}
