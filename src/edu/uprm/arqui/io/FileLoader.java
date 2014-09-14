package edu.uprm.arqui.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import edu.uprm.arqui.memory.Memory;
import edu.uprm.arqui.processor.Instruction;


public class FileLoader {

	private boolean fileValid;
	private boolean fileLoaded;
	private boolean fileTooLong;
	private BufferedReader bufferedFile;
	private static final int FILE_INSTRUCTION_LIMIT = 128;
	private static Memory memory = Memory.getInstance();

	/**
	 * Instantiates a new File loader.
	 * @param file The file to be read
	 */
	public FileLoader(File file) {
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

	/**
	 * Getter for memory
	 */
	public static Memory getMemory() {
		return memory;
	}

	public static void setMemory(Memory memory) {
		FileLoader.memory = memory;
	}

	public boolean isFileValid(){
		return fileValid;
	}
	public boolean isFileLoaded() {
		return fileLoaded;
	}

	public boolean isFileTooLong() {
		return fileTooLong;
	}


	/**
	 * Loads instructions in memory at even addresses in big endian format.
	 */
	private void loadInstruction(){
		boolean containsErrors = false;
		boolean hasStopInstruction = false;
		try {
			int count = 0;
			String instructionString;
			boolean instructionValid;
			while ((instructionString = bufferedFile.readLine()) != null) {
				System.out.println(instructionString);
				instructionValid = instructionString.matches("[0-9A-Fa-f]{4}");//instruction is hex and 4 characters
				if (instructionValid && count < FILE_INSTRUCTION_LIMIT) {
					Instruction instruction = new Instruction(instructionString);
						String byte1= instructionString.substring(0,2);//load memory in bytes
						String byte2= instructionString.substring(2,4);
						int data1= (Integer.parseInt(byte1,16));
						int data2= (Integer.parseInt(byte2,16));
						memory.setDataAt(count,(byte)data1);
						count=count+0x02;
						memory.setDataAt(count,(byte)data2);
						if(count==0xFE){//last even memory location
							break;
						}
						count=count+0x02;
				if (instruction.isStopInstruction()) {
						hasStopInstruction = true;
						break;
						}
					
				} else if(!instructionValid && count < FILE_INSTRUCTION_LIMIT) {
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
			if (bufferedFile != null) {
				try {
					bufferedFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}