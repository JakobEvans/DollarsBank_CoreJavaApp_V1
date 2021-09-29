
package com.cognixia.jump.application;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.stream.Stream;

import com.cognixia.jump.controller.DollarsBankController;
import com.cognixia.jump.model.Customer;
import com.cognixia.jump.utility.ConsolePrinterUtility;

public class DollarsBankApplication {
	
	private static ConsolePrinterUtility consolePrinter = ConsolePrinterUtility.getInstance();



	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
	
		DollarsBankController controller = DollarsBankController.getInstance();

		consolePrinter.printFormattedTextBox("DOLLARSBANK Welcomes you!");

		int userChoice;

		
//		CustomerManagementSystem.checkHighestCustomerID();
		do {
			
				consolePrinter.printMainMenu();
				consolePrinter.enterChoice(3);
				// check for integer input (anything other than regex --> 1-9* rejected
				userChoice = checkInt(scan);

				switch(userChoice) {
					case 1:
						controller.createNewAccount(scan);
						
						break;
						
					case 2:
						controller.login(scan);
						break;
					case 3: 
						System.out.println("Exiting the program...");
						break;
					default:
						consolePrinter.printError("Please enter a choice from above.");
						
				}
				
			}
			while(userChoice != 3);
		
		scan.close();
		System.exit(0);


	}


	
//	
//	// output map collection into csv file
//	public static void collectionToCSV(File csv, Map<Integer,oldCustomer> allCustomers) {
//
//		String[] header = {"ID", "First_Name", "Last_Name", "Gender", "Age", "Email", "Phone", "Salary", "Department"};
//		
//		StringBuilder stringBuild = new StringBuilder();
//		
//		// make headers
//		for (int i = 0; i < header.length; i++) {
//			stringBuild.append(header[i] + ",");
//		}
//		
//		stringBuild.append("\n");
//
//		//make rows
//		try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(csv)))){
//			
//			for(Entry<Integer, oldCustomer> currentEntry : allCustomers.entrySet()) {
//				// build string while adhering to CSV format
//				stringBuild.append(Integer.toString(currentEntry.getValue().getId()) + ",");
//				stringBuild.append(currentEntry.getValue().getfName() + ",");
//				stringBuild.append(currentEntry.getValue().getlName() + ",");
//				stringBuild.append(currentEntry.getValue().getGender() + ",");
//				stringBuild.append(Integer.toString(currentEntry.getValue().getAge()) + ",");
//				stringBuild.append(currentEntry.getValue().getEmail() + ",");
//				stringBuild.append(currentEntry.getValue().getPhoneNumber() + ",");
//				stringBuild.append(Integer.toString(currentEntry.getValue().getSalary()) + ",");
//				stringBuild.append(currentEntry.getValue().getDepartment() + ",");
//
//				stringBuild.append("\n");
//			}
//			writer.write(stringBuild.toString());
//            writer.close();
//
//			
//		}
//		catch(IOException e) {
//			System.out.println("*** IO EXCEPTION ***");
//			e.printStackTrace();
//		} 		
//		finally {
//
//			//				pw.close();
////			System.out.println("SUCCESSFULLY closed file reader stream");
//		}		
//    }		
		
	
		
	
	// Catch non integer input, -> must make some tweaks
	public static int checkInt(Scanner sc) {

		int currentInput = 0;
		boolean correctInput = false;
		
		while(correctInput == false) {
			try {
//				System.out.println("\nInput your choice: "); // DEBUG
				currentInput = sc.nextInt();
				correctInput = true;
				return currentInput;		

			}
			catch(InputMismatchException exception) {
				consolePrinter.printError("Please enter a choice from above.");
			}
			finally {
				sc.nextLine();
				
			}
		}
		return currentInput;		
	}

	

}
	
	

