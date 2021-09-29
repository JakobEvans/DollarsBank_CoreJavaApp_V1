
// Author: Jakob Evans

// Assignment: Java Project 1
// Date: 7/19/21

package com.cognixia.jump.model;

import java.util.ArrayList;

public class Customer {

	private int customerId;

	private static int maxCustomerId = 1;

	private String name;

	private String address;

	private String phoneNumber;

	private Account customerAccount;

	public Customer(String name, String address, String phoneNumber, Account customerAccount) {
		super();

		this.customerId = maxCustomerId++;
		this.name = name;
		this.address = address;

		this.phoneNumber = phoneNumber;
		this.customerAccount = customerAccount;

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Customer() {
		super();
		this.customerId = maxCustomerId++;
		this.address = "N/A";

		this.name = "Null";
		this.phoneNumber = "0000000000";
		this.customerAccount = new Account();

	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Account getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(Account customerAccount) {
		this.customerAccount = customerAccount;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + " name=" + name + ", address=" + address + ", phoneNumber="
				+ phoneNumber + ", customerAccount=" + customerAccount + "]";
	}

	public String customerInformation() {
		return "\nCustomerId = " + customerId + "\nCustomer Name = " + name + "\nCustomer Address = " + address
				+ "\nCustomer Phone Number = " + phoneNumber + "\nAccount Username = " + customerAccount.getUsername()
				+ "\nAccount Password = " + customerAccount.getPassword() + "\nAccount Initial Deposit = "
				+ customerAccount.getInitialDeposit() + "\nCurrent Savings Account Balance: "
				+ customerAccount.getSavings().getCurrentBalance() + "\n";
	}
	

}
