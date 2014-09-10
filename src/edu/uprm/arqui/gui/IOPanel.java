package edu.uprm.arqui.gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;

import edu.uprm.arqui.io.IOPorts;
import edu.uprm.arqui.util.NumberUtils;

/**
 * This class creates all the IO ports to be displayed in the MainGUI window
 * 
 * Created by eduardobreijo on 9/9/14.
 */
public class IOPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField keyboard;
	private JLabel keyboardLabel;
	private JTextField ASCIIDisplay;
	private JLabel ASCIIDisplayLabel;
	private JTextField hexDisplay;
	private JLabel hexDisplayLabel;
	private JTextField parallelIn;
	private JLabel parallelInLabel;
	private JTextField parallelOut;
	private JLabel parallelOutLabel;
	private Color backgroundColor;
	private IOPorts ports;
	
	/**
	 *  Create and initialize ports components to display in MainGUIWindow 
	 */
	public IOPanel() {
		initializeComponents();
	}
	
	/**
	 * Initialize each component, that is each port fields with the default value
	 */
	private void initializeComponents() {
		setLayout(new FlowLayout(FlowLayout.LEADING));
		setLocation(460, 30);
		setSize(100, 300);
		backgroundColor = new Color(255, 255, 255);
		
		keyboardLabel = new JLabel("Keyboard");
		keyboard = new JTextField(4);
		keyboard.setBackground(backgroundColor);
		keyboard.setDocument(new JTextFieldLimit(2));
		add(keyboardLabel);
		add(keyboard);
		
		ASCIIDisplayLabel = new JLabel("ASCII Display");
		ASCIIDisplay = new JTextField(8);
		ASCIIDisplay.setEditable(false);
		ASCIIDisplay.setBackground(backgroundColor);
		add(ASCIIDisplayLabel);
		add(ASCIIDisplay);
		
		hexDisplayLabel = new JLabel("Hex Display");
		hexDisplay = new JTextField(4);
		hexDisplay.setEditable(false);
		hexDisplay.setBackground(backgroundColor);
		add(hexDisplayLabel);
		add(hexDisplay);
		
		parallelInLabel = new JLabel("Parallel In");
		parallelIn = new JTextField(4);
		parallelIn.setBackground(backgroundColor);
		parallelIn.setDocument(new JTextFieldLimit(2));
		add(parallelInLabel);
		add(parallelIn);
		
		parallelOutLabel = new JLabel("Parallel Out");
		parallelOut = new JTextField(4);
		parallelOut.setEditable(false);
		parallelOut.setBackground(backgroundColor);
		add(parallelOutLabel);
		add(parallelOut);
		
		ports = new IOPorts();
		
		updatePorts();
		
	}
	
	/**
	 * Get the input from the keyboard field and set it in keyboard port
	 */
	public void getKeyboard() {
		String keyboardInput = keyboard.getText();
		if(keyboardInput.matches("[0-9A-Fa-f]{1,2}")) {
			ports.setKeyboard(Byte.parseByte(keyboardInput));
		} else {
			JOptionPane.showMessageDialog(this, "Incorrect Keyboard Input Data.");
		}
	}
	
	/**
	 * Set the ascii code in the ascii display
	 */
	public void setASCII() {
		ASCIIDisplay.setText(ports.getASCII());
	}
	
	/**
	 * Set the hexadecimal numbers in the hexadecimal display
	 */
	public void setHexDisplay() {
		hexDisplay.setText(ports.getHexDisplay());
	}
	/**
	 * Get the input from the parallel input field and set it in the parallel input port
	 */
	public void getParallelIn() {
		String parallelInput = parallelIn.getText();
		if(parallelInput.matches("[0-9A-Fa-f]{1,2}")) {
			ports.setParallelInput(Byte.parseByte(parallelInput));
		} else {
			JOptionPane.showMessageDialog(this, "Incorrect Parallel Input Data.");
		}
	}
	
	/**
	 * Set the parallel output in the parallel output field
	 */
	public void setParallelOut() {
		parallelOut.setText(Byte.toString(ports.getParallelOutput()));
	}
	
	/**
	 * Update the values of the ports
	 */
	public void updatePorts() {
		keyboard.setText(NumberUtils.intToHexString(ports.getKeyboard(), 2));
		ASCIIDisplay.setText(ports.getASCII());
		hexDisplay.setText(ports.getHexDisplay());
		parallelIn.setText(NumberUtils.intToHexString(ports.getParallelInput(), 2));
		parallelOut.setText(NumberUtils.intToHexString(ports.getParallelOutput(), 2));
	}
	
	/**
	 * This class limit the number of characters in a JTextField
	 * @author Eduardo
	 */
	class JTextFieldLimit extends PlainDocument {

		private static final long serialVersionUID = 1L;
		private int limit;
		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		JTextFieldLimit(int limit, boolean upper) {
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null)
				return;

			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}
	
}
