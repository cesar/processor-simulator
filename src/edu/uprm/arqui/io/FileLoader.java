package edu.uprm.arqui.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import edu.uprm.arqui.memory.Memory;
import edu.uprm.arqui.processor.Processor;


public class FileLoader {

    private static Memory memory = null;
    private static FileLoader instance;
    private boolean fileValid;
    private boolean fileLoaded;
    private boolean fileTooLong;
    private BufferedReader bufferedFile;

    /**
     * Instantiates a new File loader.
     */
    private FileLoader() {
        this.fileValid = false;
        this.fileLoaded = false;
        this.fileTooLong = false;
        this.bufferedFile = null;
        memory = Memory.getInstance();
    }

    /**
     * Lazy instantiation for FileLoader
     *
     * @return Instance of FileLoader class
     */
    public static FileLoader getInstance() {
        if (instance == null) {
            return new FileLoader();
        }
        return instance;
    }

    /**
     * Getter for memory
     */
    public static Memory getMemory() {
        return memory;
    }

    public static void setMemory(Memory memory) {
        FileLoader.memory = memory;
    }

    public void loadFile(File file) {
        if (file.isFile()) {
            try {
                this.bufferedFile = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                this.fileLoaded = false;
                return;
            }
            this.fileLoaded = true;
            loadInstruction();
        } else {
            this.fileLoaded = false;
        }
    }

    public boolean isFileValid() {
        return this.fileValid;
    }

    public boolean isFileLoaded() {
        return this.fileLoaded;
    }

    public boolean isFileTooLong() {
        return this.fileTooLong;
    }


    /**
     * Loads instructions in memory at even addresses in big endian format.
     */
    private void loadInstruction() {
        boolean containsErrors = false;

        boolean hasStopInstruction = false;

        try {

            int count = 0;

            String instructionString;

            boolean instructionValid;

            while ((instructionString = bufferedFile.readLine()) != null) {

                instructionString = instructionString.trim();

                instructionValid = instructionString.matches("[0-9A-Fa-f]{4}");//instruction is hex and 4 characters

                if (instructionValid && count <= Processor.MEMORY_SIZE) {

                    String byte1 = instructionString.substring(0, 2);//load memory in bytes

                    String byte2 = instructionString.substring(2, 4);

                    int data1 = (Integer.parseInt(byte1, 16));

                    int data2 = (Integer.parseInt(byte2, 16));

                    memory.setDataAt(count, (byte) data1);

                    count++;

                    memory.setDataAt(count, (byte) data2);

                    if (count == 256) {//last even memory location

                        break;

                    }
                    count++;

                    continue;
                }
                if (!instructionValid && count <= Processor.MEMORY_SIZE) {

                    containsErrors = true;

                } else {

                    fileTooLong = true;

                    System.err.println("File too long, ignoring instructions after the th line.");

                    break;

                }

            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            fileValid = hasStopInstruction && !containsErrors;

            try {

            	bufferedFile.close();

            	bufferedFile = null;

            } catch (IOException e) {

            	e.printStackTrace();

            }
        }

    }

}