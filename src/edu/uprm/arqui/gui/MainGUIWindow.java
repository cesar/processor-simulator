package edu.uprm.arqui.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import edu.uprm.arqui.io.FileLoader;
import edu.uprm.arqui.processor.Processor;

public class MainGUIWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel lowerCenterPanel;
    private JPanel lowerLeftPanel;
    private JPanel lowerRightPanel;

    private JButton loadFile;
    private JButton run;
    private JButton step;
    private JButton exit;

    private JFileChooser fc;

    private MemoryTable memoryTable;
    //private FileTable displayFile;
    private Registers registers;
    private IOPanel ioPanelPorts;

    private Processor processor;

    /**
     * Create and initialize a new RISC Microprocessor Simulator Windows
     */
    public MainGUIWindow() {
        initializeGUI();
    }

    /**
     * Initialize the MainGUIWindow components
     */
    private void initializeGUI() {

        initializeProcessor();

        loadFile();

        this.lowerCenterPanel = new JPanel();
        this.lowerLeftPanel = new JPanel();
        this.lowerRightPanel = new JPanel();
        this.loadFile = new JButton("Load File");
        this.run = new JButton("Run");
        this.step = new JButton("Step");
        this.exit = new JButton("Exit");
        this.fc = new JFileChooser();
        this.memoryTable = new MemoryTable();
        this.registers = new Registers();
        this.ioPanelPorts = new IOPanel();

        setLayout(null);
        add(registers);
        add(memoryTable);
        add(ioPanelPorts);

        setLowerLeftPanel();
        add(lowerLeftPanel);
        setLowerCenterPanel();
        add(lowerCenterPanel);
        setLowerRightPanel();
        add(lowerRightPanel);

        this.loadFile.addActionListener(this);
        this.run.addActionListener(this);
        this.step.addActionListener(this);
        this.exit.addActionListener(this);

        setFrame();

    }

    /**
     * Initialize the processor
     */
    private void initializeProcessor() {
        processor = new Processor();
    }

    /**
     * Load program sequence into memory
     */
    private void loadFile() {

        File file = new File("/Users/cesarcruz/Documents/IdeaProjects/ProSimR/src/edu/uprm/arqui/processor/program_sequence.txt");

        FileLoader loader = FileLoader.getInstance();

        loader.loadFile(file);
    }

    /**
     * Set MainGUI window size, title, location and parameters.
     */
    private void setFrame() {
        setTitle("RISC Microprocessor Simulator");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Set "Load" button position in the MainGUI window.
     */
    private void setLowerLeftPanel() {
        this.lowerLeftPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.lowerLeftPanel.setLocation(125, 325);
        this.lowerLeftPanel.setSize(100, 50);

        this.lowerLeftPanel.add(loadFile);
    }

    /**
     * Set "Run" and "Step" buttons position in the MainGUI window.
     */
    private void setLowerCenterPanel() {
        this.lowerCenterPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.lowerCenterPanel.setLocation(240, 325);
        this.lowerCenterPanel.setSize(150, 50);

        this.lowerCenterPanel.add(run);
        this.lowerCenterPanel.add(step);
    }

    /**
     * Set "Exit" button position in the MainGUI window
     */
    private void setLowerRightPanel() {
        this.lowerRightPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.lowerRightPanel.setLocation(390, 325);
        this.lowerRightPanel.setSize(100, 50);

        this.lowerRightPanel.add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadFile) {
            fc.showOpenDialog(this);
            if (fc.getSelectedFile() != null) {
                // TODO: Implement logic for opening and reading the file. Update the fields
            }
        } else if (e.getSource() == run) {
                ioPanelPorts.getKeyboard();

                ioPanelPorts.getParallelIn();

                processor.setRun(true);

                while(processor.isRunning()){

                    processor.step();

                    memoryTable.updateMemory();

                    registers.updateGeneralPurposeRegisterValues();

                    registers.updateSpecialRegisterValues();

                }



        } else if (e.getSource() == step) {

            ioPanelPorts.getKeyboard();

            ioPanelPorts.getParallelIn();

            processor.step();

            memoryTable.updateMemory();

            registers.updateGeneralPurposeRegisterValues();

            registers.updateSpecialRegisterValues();

        } else if (e.getSource() == exit) {
            int result = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit the application?",
                    "Exit Application",
                    JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                this.dispose();
            }
        }
    }

}
