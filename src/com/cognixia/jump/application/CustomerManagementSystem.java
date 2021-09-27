// Author: Jakob Evans

// Assignment: Java Project 1
// Date: 7/19/21

package com.cognixia.jump.application;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.cognixia.jump.model.Customer;


public class CustomerManagementSystem{
	
	
	private static int numberCustomers = 0; 
	
	
	private static Map<Integer,Customer> allCustomers = new HashMap<Integer, Customer>();

	private static int maxID;
	
	
	public enum Department {
	    SALES,
	    IT,
	    SOFTWARE,
	    MANAGEMENT,
	    
	}
	
	
	
	public static int getMaxID() {
		
		return maxID;
	}
	
	public static void readOldCustomerData (String currentCustomerData){
		
//		System.out.println("\n\nReading data from fi:");
		
		String[] CustomerData = currentCustomerData.split("\\s+"); 
		
		ArrayList<String> finalData = new ArrayList<>();

		int tempID = 0; 
		for (int j = 0; j < CustomerData.length; j++) {
			// we dont initialize a Customer with ID
			if(j == 0) {
				// need to call the setter because constructor doesnt have ID
				tempID = Integer.parseInt(CustomerData[j]);
				continue;
			}
			else {
				String temp = new String();
				temp = CustomerData[j];
				temp = temp.trim();
				
				finalData.add(temp);
			}
		}
		
		Customer tempCustomer = new Customer(finalData.get(0),finalData.get(1),finalData.get(2) ,
				Integer.parseInt(finalData.get(3)),finalData.get(4), finalData.get(5), Integer.parseInt(finalData.get(6)), finalData.get(7));
		
		// make sure to set id reciveved from .txt
		tempCustomer.setWorkerID(tempID);
		
		addCustomer(tempCustomer);


		
	}
	
	
	// saves Customer data back to .txt file for future use
	public static void saveCustomerData (){
		
		String[] header = {"ID", "First_Name", "Last_Name", "Gender", "Age", "Email", "Phone", "Salary", "Department"};
		
		StringBuilder stringBuild = new StringBuilder();
		
		for (int i = 0; i < header.length; i++) {
			stringBuild.append(header[i] + "	");

		}
		stringBuild.append("\n");
		
		try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("resources/data.txt")))){
					
					for(Entry<Integer, Customer> currentEntry : allCustomers.entrySet()) {
						
						
						stringBuild.append(Integer.toString(currentEntry.getValue().getId()) + "	");
						stringBuild.append(currentEntry.getValue().getfName() + "	");
						stringBuild.append(currentEntry.getValue().getlName() + "	");
						stringBuild.append(currentEntry.getValue().getGender() + "	");
						stringBuild.append(Integer.toString(currentEntry.getValue().getAge()) + "	");
						stringBuild.append(currentEntry.getValue().getEmail() + "	");
						stringBuild.append(currentEntry.getValue().getPhoneNumber() + "	");
						stringBuild.append(Integer.toString(currentEntry.getValue().getSalary()) + "	");
						stringBuild.append(currentEntry.getValue().getDepartment() + "	");

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
//					System.out.println("SUCCESSFULLY closed file reader stream");
				}		
		
		
		
	}
	
	
	
public static void listCustomersByDepartment(int department) {
	
	 
	 String [] allDepartments = {"SALES", "IT", "SOFTWARE", "MANAGEMENT"};

	 
	 Customer [] temp = allCustomers.values().stream().filter(e -> e.getDepartment().equals(allDepartments[department])).toArray(size -> new Customer[size]);
	
	  
	 System.out.println("*** The Customers in the " + allDepartments[department] + " Department ***");
	 for (int i = 0; i < temp.length; i++) {
		 System.out.println(temp[i].toString());
	 }

	
}
	
public static void updateCustomer(int key) {
		
		Scanner scan = new Scanner(System.in);
		Customer tempCustomer = allCustomers.get(key);
		
		System.out.println(tempCustomer.toString());
		
		System.out.println("\n\nWhich attribute would you like to update?");
		tempCustomer.listAttributeNames();
		
		int choice = DollarsBankApplication.checkInt(scan);
		
		
		String userStringInput = "";
		int userIntInput = 0;
		
		// switch for specific attribute you want to change
		switch(choice) {
			case(0):
				System.out.println("\nPlease enter your string for First Name:\n");
			
				userStringInput = scan.nextLine();
				
//				userStringInput = "CHecking to see if it works";// TO REMOVE
				tempCustomer.setfName(userStringInput);
				break;
			case(1):
				System.out.println("\nPlease enter your string for Last Name:\n");

				userStringInput = scan.nextLine();
				tempCustomer.setlName(userStringInput);
				break;

			case(2):
				System.out.println("\nPlease enter your string for Gender:\n");

				userStringInput = scan.nextLine();
				tempCustomer.setGender(userStringInput);	
				break;

				
			case(3):
				System.out.println("\nPlease enter your int for Age:\n");

				userIntInput = DollarsBankApplication.checkInt(scan);
				
				tempCustomer.setAge(userIntInput);
				break;

				
			case(4):
				System.out.println("\nPlease enter your string for Email:\n");

				userStringInput = scan.nextLine();
				tempCustomer.setEmail(userStringInput);
				break;

			case(5):
				System.out.println("\nPlease enter your string for Phone Number\n");

				userStringInput = scan.nextLine();
				tempCustomer.setPhoneNumber(userStringInput);
				break;

			case(6):
				System.out.println("\nPlease enter your int for Salary:\n");

				userIntInput = DollarsBankApplication.checkInt(scan);
				// eat the \n
				tempCustomer.setSalary(userIntInput);
				break;

			case(7):
				System.out.println("\nPlease enter your string for Department:\n");

				userStringInput = scan.nextLine();
				tempCustomer.setDepartment(userStringInput);
				break;
			default:
				System.out.println("Hit default");


		
		}
		
		
		
	}
	
	public static void addCustomer(Customer Customer) {
		
		
		maxID++;
		
		allCustomers.put(maxID, Customer);
		
		numberCustomers++;
		
		
		
	}
	
	
	public static void deleteCustomer(int key) {
		getAllCustomers().remove(key);
			
	}
	
	// Finds the Highest ID considering the data.txt files previous Customers
	public static void checkHighestCustomerID() {
		
		int maxID = Collections.max(getAllCustomers().keySet());

        Map.Entry<Integer, Customer> entryWithMaxKey = null;
        
        if(getAllCustomers().size() > 0) {

			for(Entry<Integer, Customer> currentEntry : getAllCustomers().entrySet()) {
					
		            if (entryWithMaxKey == null || currentEntry.getKey().compareTo(entryWithMaxKey.getKey()) > 0) {
		  
		                entryWithMaxKey = currentEntry;
		            }
		    }
		  
	        // Return the entry with highest key
//			System.out.println("\n\nTHE HIGHEST KEY IS ---> "  + entryWithMaxKey.getKey());
				
			maxID = entryWithMaxKey.getKey();

        }// ID starts at 0 because there is no Customer data in map
        else {
        	maxID = 0;
        }
	}
	
	
	
	public static void printCustomers() {

	    System.out.print(String.format("%-3s %-14s %-14s %-7s %-4s %-24s %-12s %-7s %-7s\n","ID","First_Name","Last_Name", "Gender", "Age", "Email", "Phone_Num", "Salary", "Department"));

		for(Entry<Integer, Customer> temp : getAllCustomers().entrySet()) {
			
		    System.out.print(String.format("%-3s %-14s %-14s %-7s %-4s %-24s %-12s %-7s %-7s\n",
		    		temp.getValue().getWorkerID(),temp.getValue().getfName(),temp.getValue().getlName(), 
		    		temp.getValue().getGender(), temp.getValue().getAge(), temp.getValue().getEmail(), 
		    		temp.getValue().getPhoneNumber(), temp.getValue().getSalary(), temp.getValue().getDepartment()));
		}		
		
		

		
	}

	public static void printNumCustomers() {
		
		System.out.println("The number of Customers is : " + numberCustomers);
		
	}

	public static Map<Integer,Customer> getAllCustomers() {
		return allCustomers;
	}

	public static void setAllCustomers(Map<Integer,Customer> allCustomers) {
		CustomerManagementSystem.allCustomers = allCustomers;
	}

	
	

	
	


}
