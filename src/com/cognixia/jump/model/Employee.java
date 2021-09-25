// Author: Jakob Evans

// Assignment: Java Project 1
// Date: 7/19/21

package com.cognixia.jump.intermediatejava.JavaFinalProject_Employee;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Employee extends Person{

	private int workerID = EmployeeManagementSystem.getMaxID();

	private int salary;
	private String department;
	
	

	public Employee(String fName,String lName, String gender, int age, String email, String phoneNumber, int salary, String department) {

		
		super(fName, lName, gender, age, email, phoneNumber);
		this.workerID = workerID++;


		this.salary = salary;
		this.department = department;
	}

	

	public Employee() {
		super();
		this.salary = -1;
		this.department = "";
		
		
	}
	
	public void listAttributeNames() {
		System.out.println("(0) First Name\n(1) Last Name\n(2) Gender\n"
				+ "(3) Age\n(4) Email\n(5) Phone Number\n(6) Salary\n(7) Department" );
		
	}
	

	

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getId() {
		return workerID;
	}

	@Override
	public String toString() {
		return "Employee [workerID=" + workerID + ", First Name=" + super.getfName() + ", Last Name=" + super.getlName() + " Gender =" + super.getGender() + ", age=" + super.getAge() + 
				" Email=" + super.getEmail() + ", Email=" + super.getPhoneNumber() + ", salary=" + salary + ", department=" + department + "]";
	}

	public int getWorkerID() {
		return workerID;
	}

	public void setWorkerID(int workerID) {
		this.workerID = workerID;
	}


	

}