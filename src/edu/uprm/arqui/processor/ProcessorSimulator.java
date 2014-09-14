package edu.uprm.arqui.processor;

import edu.uprm.arqui.gui.MainGUIWindow;
import edu.uprm.arqui.io.FileLoader;

import javax.swing.*;
import java.io.File;
import java.net.URL;

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
