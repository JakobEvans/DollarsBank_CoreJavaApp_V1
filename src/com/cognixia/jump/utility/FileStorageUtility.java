package com.cognixia.jump.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//import com.cognixia.jump.application.CustomerManagementSystem;

public class FileStorageUtility {
	
	// read the text file and create Customer objects for hashMap
	public static void readFile() {

		
		File file = new File("resources/data.txt");
		FileReader fileReader = null;
		BufferedReader br = null;
		
		
		try {
			fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			
			br = Files.newBufferedReader(Paths.get("resources/data.txt"));
			Stream<String> lines = br.lines();
			// read .txt and convert 
//			lines.skip(1).forEach(a -> CustomerManagementSystem.readOldCustomerData(a));
			
		} catch(FileNotFoundException e) {
			System.out.println("*** FILE NOT FOUND EXCEPTION ***");
			e.printStackTrace();
		}
		 catch(NumberFormatException e) {
				System.out.println("*** NumberFormatException ***");
				e.printStackTrace();
			}
		catch(NullPointerException e) {
			System.out.println("*** NULL POINTER EXCEPTION ***");
			e.printStackTrace();
		}
		catch(IOException e) {
			System.out.println("*** IO EXCEPTION ***");
			e.printStackTrace();
		} catch(Exception e) {
			System.out.println("*** GENERAL EXCEPTION ***");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				br.close();
//				System.out.println("SUCCESSFULLY closed file reader stream");
			} 
			catch(FileNotFoundException e) {
				System.out.println("ERR: File not founbd");

			}
			catch(IOException e) {
//				System.out.println("ERR: FAILED to close file reader stream");
				e.printStackTrace();
			}
			
		}
		
	}
}
