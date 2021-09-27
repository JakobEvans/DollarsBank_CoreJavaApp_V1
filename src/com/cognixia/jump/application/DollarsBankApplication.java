
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

import com.cognixia.jump.model.Customer;

public class DollarsBankApplication {


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
	
		System.out.println("***** Welcome to the Customer Management System ******\n\n");
		System.out.println("Reading data.txt file to grab current Customers!");
	
		// read data from file and create the Customers
		readFile(); 
		
		CustomerManagementSystem.checkHighestCustomerID();
		
		File csvFile = new File("resources/csvData.csv");
		
		// add recently read files to csv!
		collectionToCSV(csvFile, CustomerManagementSystem.getAllCustomers());
		
		int userChoice = 7;
		while (userChoice > 6 || userChoice != 6) {
			
			String userInput = ""; 
			

			
			System.out.println("\nWhat would you like to do?\n(1) Add a Customer\n(2) Delete an Customer\n"
					+ "(3) Display all Customers\n(4) Update Customer\n(5) Display Customers by Department\n(6) Quit and save to CSV");

			// check for integer input (anything other than regex --> 1-9* rejected
			userChoice = checkInt(scan);

			switch(userChoice) {
				case 1:
					System.out.println("You chose to add an Customer.\nEnter space seperated data for Customer in the "
							+ "following format.\nFIRST_NAME	LAST_NAME GENDER AGE EMAIL PHONENUMBER SALARY DEPARTMENT\\n");

					
					
					
					
					boolean isFlag = true;
					Customer tempCustomer = null;
					while (isFlag) {
						try {
							userInput = scan.nextLine();
							
							String[] CustomerData = userInput.split("\\s+"); 
						// set each attribute to corresponding value ( just split string by white sapce)
							tempCustomer = new Customer(CustomerData[0],CustomerData[1],CustomerData[2],Integer.parseInt(CustomerData[3]),
								CustomerData[4],CustomerData[5],Integer.parseInt(CustomerData[6]),CustomerData[7]);
							isFlag = false;
						}
						catch(ArrayIndexOutOfBoundsException e) {
							System.out.println("Please enter the correct value for the 8 attributes");
						}
						catch(NumberFormatException e) {
							System.out.println("Please enter integer values for Age and Salary");
						}
					}
					// get the maxID based off of .txt input  
					int maxID = CustomerManagementSystem.getMaxID();
					
					//new max needs to be updated for new Customer
					maxID++;
					tempCustomer.setWorkerID(maxID);
					
					CustomerManagementSystem.addCustomer(tempCustomer);
					// update CSV file
					collectionToCSV(csvFile, CustomerManagementSystem.getAllCustomers());
					break;
					
				case 2:
					System.out.println("\nYou chose to delete an Customer: ");
					CustomerManagementSystem.printCustomers();

					System.out.println("\nPlease select the Customer ID to delete it. ");
					// user ID row to be deleted
					userChoice = checkInt(scan);
					//pass the unique key
					CustomerManagementSystem.deleteCustomer(userChoice);
					// update CSV
					collectionToCSV(csvFile, CustomerManagementSystem.getAllCustomers());
					break;
				
				case 3: 
					System.out.println("\n\nPrinting Customer Info table\n\n");
					CustomerManagementSystem.printCustomers();
					break;
				case 4:
					CustomerManagementSystem.printCustomers();
					System.out.println("\n\nPlease select the ID of the Customer you want to update:");
					int key = checkInt(scan);

					CustomerManagementSystem.updateCustomer(key);
//					CustomerManagementSystem.updateCustomer(key, scan);

					break;
					// Display by department Customers
				case 5:
					
					System.out.println("\n\nEnter the department number to see its Customers:");
					System.out.println("\n(0) SALES\n(1) IT\n(2) SOFTWARE\n(3) MANAGEMENT \n\n");
					
					userChoice = checkInt(scan);
					CustomerManagementSystem.listCustomersByDepartment(userChoice);
					
				case 6:
					System.out.println("\n\nExiting\n\n");
					CustomerManagementSystem.saveCustomerData();
					collectionToCSV(csvFile, CustomerManagementSystem.getAllCustomers());

					System.exit(0);
		
					break;
				default: 
					System.out.println("\n\nPlease enter one of the valid choices\n\n");
			}
		}
		
		scan.close();
		
		
	}
	
	
	
	
	
	// output map collection into csv file
	public static void collectionToCSV(File csv, Map<Integer,Customer> allCustomers) {

		String[] header = {"ID", "First_Name", "Last_Name", "Gender", "Age", "Email", "Phone", "Salary", "Department"};
		
		StringBuilder stringBuild = new StringBuilder();
		
		// make headers
		for (int i = 0; i < header.length; i++) {
			stringBuild.append(header[i] + ",");
		}
		
		stringBuild.append("\n");

		//make rows
		try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(csv)))){
			
			for(Entry<Integer, Customer> currentEntry : allCustomers.entrySet()) {
				// build string while adhering to CSV format
				stringBuild.append(Integer.toString(currentEntry.getValue().getId()) + ",");
				stringBuild.append(currentEntry.getValue().getfName() + ",");
				stringBuild.append(currentEntry.getValue().getlName() + ",");
				stringBuild.append(currentEntry.getValue().getGender() + ",");
				stringBuild.append(Integer.toString(currentEntry.getValue().getAge()) + ",");
				stringBuild.append(currentEntry.getValue().getEmail() + ",");
				stringBuild.append(currentEntry.getValue().getPhoneNumber() + ",");
				stringBuild.append(Integer.toString(currentEntry.getValue().getSalary()) + ",");
				stringBuild.append(currentEntry.getValue().getDepartment() + ",");

				stringBuild.append("\n");
			}
			writer.write(stringBuild.toString());
            writer.close();

			
		}
		catch(IOException e) {
			System.out.println("*** IO EXCEPTION ***");
			e.printStackTrace();
		} 		
		finally {

			//				pw.close();
//			System.out.println("SUCCESSFULLY closed file reader stream");
		}		
    }		
		
	
		
	
	// Catch non integer input, -> must make some tweaks
	public static int checkInt(Scanner sc) {

		int currentInput = 0;
		boolean correctInput = false;
		
		while(correctInput == false) {
			try {
				System.out.println("\nInput your choice: "); // DEBUG
				currentInput = sc.nextInt();
				correctInput = true;
				return currentInput;		

			}
			catch(InputMismatchException exception) {
				System.out.println("\n\nPlease enter an integer.\n\n");
			}
			finally {
				sc.nextLine();
				
			}
		}
		return currentInput;		
	}
	
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
			lines.skip(1).forEach(a -> CustomerManagementSystem.readOldCustomerData(a));
			
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
