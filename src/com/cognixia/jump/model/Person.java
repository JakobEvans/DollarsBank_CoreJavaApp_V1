
// Author: Jakob Evans

// Assignment: Java Project 1
// Date: 7/19/21

package com.cognixia.jump.model;

abstract class Person {
	
	
	private String fName;
	private String lName;

	private String gender;
	private int age;
	private String email;
	
	private String phoneNumber;


	
	
	
	public Person () {
		
		System.out.println("Default constructor()");
		
	}
	
	public Person (String fName,String lName, String gender, int age, String email, String phoneNumber) {
			
			this.fName = fName;
			this.lName = lName;

			this.gender = gender;
			this.age = age;
			this.email = email;
			this.phoneNumber = phoneNumber;

	}
	
	
//	public void checkEmployment() {
//		
//		if(isEmployed) {
//			
//			System.out.println(fName + "is currently employed at the company.");
//		}
//		else {
//			System.out.println(lName + "is NOT currently employed at the company.");
//
//		}
//		
//	}



	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
//
//	public static boolean isEmployed() {
//		return isEmployed;
//	}
//
//	public static void setEmployed(boolean isEmployed) {
//		Person.isEmployed = isEmployed;
//	}

//	public boolean isEmployed() {
//		return isEmployed;
//	}
//
//	public void setEmployed(boolean isEmployed) {
//		this.isEmployed = isEmployed;
//	}
//	
	

}
