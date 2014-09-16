package edu.uprm.arqui.gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.uprm.arqui.processor.Processor;
import edu.uprm.arqui.register.InstructionRegister;
import edu.uprm.arqui.register.MainRegisters;
import edu.uprm.arqui.register.ProgramCounterRegister;
import edu.uprm.arqui.util.NumberUtils;

/**
 * This class creates all the registers fields and labels to be
 * displayed in the MainGUI window
 * <p>
 * Created by eduardobreijo on 9/9/14.
 */
public class Registers extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField ir;
    private JLabel irLabel;
    private JTextField pc;
    private JLabel pcLabel;
    private JTextField[] registers;
    private JLabel[] registersLabels;
    private Color backgroundColor;
    private static InstructionRegister instructionRegister;
    private static ProgramCounterRegister programCounterRegister;
    private static MainRegisters mainRegisters;

    /**
     * Create and initialize registers to display in MainGUIWindow
     */
    public Registers() {
        initializeComponents();
    }

    /**
     * Initialize each component, that is each register fields with the default value
     */
    private void initializeComponents() {
        setLayout(new FlowLayout(FlowLayout.LEADING));
        setLocation(30, 30);
        setSize(80, 300);

        registers = new JTextField[8];
        registersLabels = new JLabel[8];
        backgroundColor = new Color(255, 255, 255);

        instructionRegister = InstructionRegister.getInstance();
        irLabel = new JLabel(" IR ");
        ir = new JTextField(4);
        ir.setEditable(false);
        ir.setBackground(backgroundColor);
        add(irLabel);
        add(ir);

        programCounterRegister = ProgramCounterRegister.getInstance();
        pcLabel = new JLabel("PC");
        pc = new JTextField(4);
        pc.setBackground(backgroundColor);
        add(pcLabel);
        add(pc);


        for (int i = 0; i < Processor.NUMBER_GPR; i++) {
            registersLabels[i] = new JLabel("R" + i);
            registers[i] = new JTextField(4);
            registers[i].setBackground(backgroundColor);
            add(registersLabels[i]);
            add(registers[i]);
        }

        updateSpecialRegisterValues();
        updateGeneralPurposeRegisterValues();

    }
    
    
    /**
     * Get the input from the pc field and set it to the program counter object
     */
    public void getProgramCounterText() {
        String pcString = pc.getText();
        if (pcString.matches("[0-9A-Fa-f]{3}")) {
            programCounterRegister.setPc(NumberUtils.getSignedValueOf(Integer.parseInt(pcString, 16), 3, Processor.PC_SIZE - 1, Processor.PC_SIZE));
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect Input Data.");
        }
    }

    /**
     * Get the input from the R0 field and set it to the R0 main registers object
     */
    public void getR0() {
        String r0 = registers[0].getText();
        if (r0.matches("[0-9A-Fa-f]{2}")) {
            mainRegisters.setRegister((byte)Integer.parseInt(r0, 16), 0);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect Input Data.");
        }
    }
    
    /**
     * Get the input from the R1 field and set it to the R1 main registers object
     */
    public void getR1() {
        String r1 = registers[1].getText();
        if (r1.matches("[0-9A-Fa-f]{2}")) {
        	 mainRegisters.setRegister((byte)Integer.parseInt(r1, 16), 1);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect Input Data.");
        }
    }
    
    /**
     * Get the input from the R2 field and set it to the R2 main registers object
     */
    public void getR2() {
        String r2 = registers[2].getText();
        if (r2.matches("[0-9A-Fa-f]{2}")) {
        	 mainRegisters.setRegister((byte)Integer.parseInt(r2, 16), 2);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect Input Data.");
        }
    }
    
    /**
     * Get the input from the R3 field and set it to the R3 main registers object
     */
    public void getR3() {
        String r3 = registers[3].getText();
        if (r3.matches("[0-9A-Fa-f]{2}")) {
        	 mainRegisters.setRegister((byte)Integer.parseInt(r3, 16), 3);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect Input Data.");
        }
    }
    
    /**
     * Get the input from the R4 field and set it to the R4 main registers object
     */
    public void getR4() {
        String r4 = registers[4].getText();
        if (r4.matches("[0-9A-Fa-f]{2}")) {
        	 mainRegisters.setRegister((byte)Integer.parseInt(r4, 16), 4);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect Input Data.");
        }
    }
    
    /**
     * Get the input from the R5 field and set it to the R5 main registers object
     */
    public void getR5() {
        String r5 = registers[5].getText();
        if (r5.matches("[0-9A-Fa-f]{2}")) {
        	 mainRegisters.setRegister((byte)Integer.parseInt(r5, 16), 5);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect Input Data.");
        }
    }
    
    /**
     * Get the input from the R6 field and set it to the R6 main registers object
     */
    public void getR6() {
        String r6 = registers[6].getText();
        if (r6.matches("[0-9A-Fa-f]{2}")) {
        	 mainRegisters.setRegister((byte)Integer.parseInt(r6, 16), 6);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect Input Data.");
        }
    }
    
    /**
     * Get the input from the R7 field and set it to the R7 main registers object
     */
    public void getR7() {
        String r7 = registers[7].getText();
        if (r7.matches("[0-9A-Fa-f]{2}")) {
        	 mainRegisters.setRegister((byte)Integer.parseInt(r7, 16), 7);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect Input Data.");
        }
    }
    
    /**
     * Update the values of the special registers fields.
     */
    public void updateSpecialRegisterValues() {
        ir.setText(NumberUtils.intToHexString(instructionRegister.getValue(), 4));
        pc.setText(NumberUtils.intToHexString(programCounterRegister.getPc(), 3));
    }

    /**
     * Update the values of each general purpose register field.
     */
    public void updateGeneralPurposeRegisterValues() {
        mainRegisters = MainRegisters.getInstance();
        for (int i = 0; i < Processor.NUMBER_GPR; i++) {
            registers[i].setText(NumberUtils.intToHexString(mainRegisters.getRegister(i), 2));
        }
    }

}