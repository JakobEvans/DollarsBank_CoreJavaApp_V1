package com.cognixia.jump.model;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

public class Account {

	final static private String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[#?!@$%^&*-]).{8,}$";

	private String username;

	private String password;

	private Double initialDeposit;

	private SavingsAccount savings = new SavingsAccount();

	private ArrayList<String> transactions;

	public ArrayList<String> getTransactions() {
		return transactions;
	}

	public void addTransaction(String transaction) {
		this.transactions.add(transaction);
	}

	public Account(String username, String password, Double initialDeposit) {
		super();
		this.username = username;
		this.password = password;
		this.initialDeposit = initialDeposit;

		this.savings.setCurrentBalance(initialDeposit);

		this.transactions = new ArrayList<>();

	}

	public Account() {
		super();
		this.username = "";
		this.password = "";
		this.initialDeposit = 0.0;
		this.savings = new SavingsAccount();

	}

	public SavingsAccount getSavings() {
		return savings;
	}

	public void setSavings(SavingsAccount savings) {
		this.savings = savings;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getInitialDeposit() {
		return initialDeposit;
	}

	public void setInitialDeposit(Double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", initialDeposit=" + initialDeposit
				+ ", savingsAccount=" + savings + "]";
	}

	public String getPasswordRegex() {
		return getPasswordregex();
	}

	public static String getPasswordregex() {
		return passwordRegex;
	}

}
