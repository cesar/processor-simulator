package edu.uprm.arqui.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.uprm.arqui.io.FileLoader;
import edu.uprm.arqui.memory.Memory;
import edu.uprm.arqui.util.NumberUtils;


/**
 * This class creates a Memory Table to be displayed in the MainGUI window
 * <p>
 * Created by eduardobreijo on 9/9/14.
 */
public class MemoryTable extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JLabel memoryLabel;
    private String columnNames[];
    private String data[][];
    private static Memory memory;

    /**
     * Create and initialize a new Table for the memory in the MainGUI window
     */
    public MemoryTable() {
        initializeComponents();
    }

    /**
     * Initialize the components with the default values of the table
     */
    private void initializeComponents() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setLocation(160, 25);
        setSize(250, 260);

        memoryLabel = new JLabel("Memory");
        createColumns();

        FileLoader loader = FileLoader.getInstance();

        if(loader.isFileLoaded()){
            updateMemory();
        } else{
            createData();
        }

        model = new DefaultTableModel(data, columnNames) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(200, 230));
        add(memoryLabel);
        add(scrollPane);

        memory = Memory.getInstance();

        updateMemory();

    }

    /**
     * Create the columns of the table
     */
    private void createColumns() {
        columnNames = new String[2];
        columnNames[0] = "Address";
        columnNames[1] = "Data";
    }

    /**
     * Initialize the table with default values
     */
    private void createData() {
        data = new String[256][2];
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    data[i][j] = NumberUtils.intToHexString(i, 2);
                } else {
                    data[i][j] = NumberUtils.intToHexString(0, 2);
                }
            }
        }
    }

    /**
     * Update the values of the table.
     */
    public void updateMemory() {
        for (int i = 0; i < 256; i++) {
            data[i][1] = NumberUtils.intToHexString(memory.getByte(i), 2);
        }
        model.setDataVector(data, columnNames);
        table.revalidate();
        model.fireTableDataChanged();
    }
}
