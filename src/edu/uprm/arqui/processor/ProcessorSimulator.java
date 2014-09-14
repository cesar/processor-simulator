package edu.uprm.arqui.processor;

import edu.uprm.arqui.gui.MainGUIWindow;

import javax.swing.*;

/**
 * Created by cesarcruz on 9/13/14.
 */
public class ProcessorSimulator {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainGUIWindow mainGUI = new MainGUIWindow();
                mainGUI.setVisible(true);
            }
        });
    }
}
