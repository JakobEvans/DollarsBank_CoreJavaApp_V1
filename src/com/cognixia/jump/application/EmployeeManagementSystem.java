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


public class EmployeeManagementSystem{
	
	
	private static int numberEmployees = 0; 
	
	
	private static Map<Integer,Employee> allEmployees = new HashMap<Integer, Employee>();

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
	
	public static void readOldEmployeeData (String currentEmployeeData){
		
//		System.out.println("\n\nReading data from fi:");
		
		String[] employeeData = currentEmployeeData.split("\\s+"); 
		
		ArrayList<String> finalData = new ArrayList<>();

		int tempID = 0; 
		for (int j = 0; j < employeeData.length; j++) {
			// we dont initialize a employee with ID
			if(j == 0) {
				// need to call the setter because constructor doesnt have ID
				tempID = Integer.parseInt(employeeData[j]);
				continue;
			}
			else {
				String temp = new String();
				temp = employeeData[j];
				temp = temp.trim();
				
				finalData.add(temp);
			}
		}
		
		Employee tempEmployee = new Employee(finalData.get(0),finalData.get(1),finalData.get(2) ,
				Integer.parseInt(finalData.get(3)),finalData.get(4), finalData.get(5), Integer.parseInt(finalData.get(6)), finalData.get(7));
		
		// make sure to set id reciveved from .txt
		tempEmployee.setWorkerID(tempID);
		
		addEmployee(tempEmployee);


		
	}
	
	
	// saves employee data back to .txt file for future use
	public static void saveEmployeeData (){
		
		String[] header = {"ID", "First_Name", "Last_Name", "Gender", "Age", "Email", "Phone", "Salary", "Department"};
		
		StringBuilder stringBuild = new StringBuilder();
		
		for (int i = 0; i < header.length; i++) {
			stringBuild.append(header[i] + "	");

		}
		stringBuild.append("\n");
		
		try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("resources/data.txt")))){
					
					for(Entry<Integer, Employee> currentEntry : allEmployees.entrySet()) {
						
						
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
	
	
	
public static void listEmployeesByDepartment(int department) {
	
	 
	 String [] allDepartments = {"SALES", "IT", "SOFTWARE", "MANAGEMENT"};

	 
	 Employee [] temp = allEmployees.values().stream().filter(e -> e.getDepartment().equals(allDepartments[department])).toArray(size -> new Employee[size]);
	
	  
	 System.out.println("*** The employees in the " + allDepartments[department] + " Department ***");
	 for (int i = 0; i < temp.length; i++) {
		 System.out.println(temp[i].toString());
	 }

	
}
	
public static void updateEmployee(int key) {
		
		Scanner scan = new Scanner(System.in);
		Employee tempEmployee = allEmployees.get(key);
		
		System.out.println(tempEmployee.toString());
		
		System.out.println("\n\nWhich attribute would you like to update?");
		tempEmployee.listAttributeNames();
		
		int choice = EMSRunner.checkInt(scan);
		
		
		String userStringInput = "";
		int userIntInput = 0;
		
		// switch for specific attribute you want to change
		switch(choice) {
			case(0):
				System.out.println("\nPlease enter your string for First Name:\n");
			
				userStringInput = scan.nextLine();
				
//				userStringInput = "CHecking to see if it works";// TO REMOVE
				tempEmployee.setfName(userStringInput);
				break;
			case(1):
				System.out.println("\nPlease enter your string for Last Name:\n");

				userStringInput = scan.nextLine();
				tempEmployee.setlName(userStringInput);
				break;

			case(2):
				System.out.println("\nPlease enter your string for Gender:\n");

				userStringInput = scan.nextLine();
				tempEmployee.setGender(userStringInput);	
				break;

				
			case(3):
				System.out.println("\nPlease enter your int for Age:\n");

				userIntInput = EMSRunner.checkInt(scan);
				
				tempEmployee.setAge(userIntInput);
				break;

				
			case(4):
				System.out.println("\nPlease enter your string for Email:\n");

				userStringInput = scan.nextLine();
				tempEmployee.setEmail(userStringInput);
				break;

			case(5):
				System.out.println("\nPlease enter your string for Phone Number\n");

				userStringInput = scan.nextLine();
				tempEmployee.setPhoneNumber(userStringInput);
				break;

			case(6):
				System.out.println("\nPlease enter your int for Salary:\n");

				userIntInput = EMSRunner.checkInt(scan);
				// eat the \n
				tempEmployee.setSalary(userIntInput);
				break;

			case(7):
				System.out.println("\nPlease enter your string for Department:\n");

				userStringInput = scan.nextLine();
				tempEmployee.setDepartment(userStringInput);
				break;
			default:
				System.out.println("Hit default");


		
		}
		
		
		
	}
	
	public static void addEmployee(Employee employee) {
		
		
		maxID++;
		
		allEmployees.put(maxID, employee);
		
		numberEmployees++;
		
		
		
	}
	
	
	public static void deleteEmployee(int key) {
		getAllEmployees().remove(key);
			
	}
	
	// Finds the Highest ID considering the data.txt files previous employees
	public static void checkHighestEmployeeID() {
		
		int maxID = Collections.max(getAllEmployees().keySet());

        Map.Entry<Integer, Employee> entryWithMaxKey = null;
        
        if(getAllEmployees().size() > 0) {

			for(Entry<Integer, Employee> currentEntry : getAllEmployees().entrySet()) {
					
		            if (entryWithMaxKey == null || currentEntry.getKey().compareTo(entryWithMaxKey.getKey()) > 0) {
		  
		                entryWithMaxKey = currentEntry;
		            }
		    }
		  
	        // Return the entry with highest key
//			System.out.println("\n\nTHE HIGHEST KEY IS ---> "  + entryWithMaxKey.getKey());
				
			maxID = entryWithMaxKey.getKey();

        }// ID starts at 0 because there is no employee data in map
        else {
        	maxID = 0;
        }
	}
	
	
	
	public static void printEmployees() {

	    System.out.print(String.format("%-3s %-14s %-14s %-7s %-4s %-24s %-12s %-7s %-7s\n","ID","First_Name","Last_Name", "Gender", "Age", "Email", "Phone_Num", "Salary", "Department"));

		for(Entry<Integer, Employee> temp : getAllEmployees().entrySet()) {
			
		    System.out.print(String.format("%-3s %-14s %-14s %-7s %-4s %-24s %-12s %-7s %-7s\n",
		    		temp.getValue().getWorkerID(),temp.getValue().getfName(),temp.getValue().getlName(), 
		    		temp.getValue().getGender(), temp.getValue().getAge(), temp.getValue().getEmail(), 
		    		temp.getValue().getPhoneNumber(), temp.getValue().getSalary(), temp.getValue().getDepartment()));
		}		
		
		

		
	}

	public static void printNumEmployees() {
		
		System.out.println("The number of Employees is : " + numberEmployees);
		
	}

	public static Map<Integer,Employee> getAllEmployees() {
		return allEmployees;
	}

	public static void setAllEmployees(Map<Integer,Employee> allEmployees) {
		EmployeeManagementSystem.allEmployees = allEmployees;
	}

	
	

	
	


}
