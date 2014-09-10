package edu.uprm.arqui.gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
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
 * 
 * Created by eduardobreijo on 9/9/14.
 */
public class Registers extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField ir;
	private JLabel irLabel;
	private JTextField pc;
	private JLabel pcLabel;
	private JTextField[]registers;
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
		pc.setEditable(false);
		pc.setBackground(backgroundColor);
		add(pcLabel);
		add(pc);

		
		for (int i = 0; i < Processor.NUMBER_GPR; i++) {
			registersLabels[i] = new JLabel("R" + i);
			registers[i] = new JTextField(4);
			registers[i].setEditable(false);
			registers[i].setBackground(backgroundColor);
			add(registersLabels[i]);
			add(registers[i]);
		}
		
		updateSpecialRegisterValues();
		updateGeneralPurposeRegisterValues();
		
	}
	
	/**
	 * Set the value of the IR field
	 * @param value the new value
	 */
	public void setIR(String value) {
		ir.setText(value);
	}
	
	/**
	 * Set the value of the PC field
	 * @param value the new value
	 */
	public void setPC(String value) {
		pc.setText(value);
	}
	
	/**
	 * Set the value of register R0 field.
	 * @param data The new value of register R0.
	 */
	public void setR0(String data) {
		this.registers[0].setText(data);
	}

	/**
	 * Set the value of register R1 field.
	 * @param data The new value of register R1.
	 */
	public void setR1(String data) {
		this.registers[1].setText(data);
	}

	/**
	 * Set the value of register R2 field.
	 * @param data The new value of register R2.
	 */
	public void setR2(String data) {
		this.registers[2].setText(data);
	}

	/**
	 * Set the value of register R3 field.
	 * @param data The new value of register R3.
	 */
	public void setR3(String data) {
		this.registers[3].setText(data);
	}

	/**
	 * Set the value of register R4 field.
	 * @param data The new value of register R4.
	 */
	public void setR4(String data) {
		this.registers[4].setText(data);
	}

	/**
	 * Set the value of register R5 field.
	 * @param data The new value of register R5.
	 */
	public void setR5(String data) {
		this.registers[5].setText(data);
	}

	/**
	 * Set the value of register R6 field.
	 * @param data The new value of register R6.
	 */
	public void setR6(String data) {
		this.registers[6].setText(data);
	}

	/**
	 * Set the value of register R7 field.
	 * @param data The new value of register R7.
	 */
	public void setR7(String data) {
		this.registers[7].setText(data);
	}

	/**
	 * Update the values of the special registers fields.
	 */
	public void updateSpecialRegisterValues() {
		//ir.setText(NumberUtils.intToHexString(instructionRegister.getValue(), 4));
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