package com.cognixia.jump.model;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cognixia.jump.utility.ConsolePrinterUtility;

public class Account {

	final static private String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[#?!@$%^&*-]).{8,}$";
	
	final static private String phoneNumberRegex = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
	
	
	
	static private ConsolePrinterUtility printer = ConsolePrinterUtility.getInstance();


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

	
	
	public static boolean checkValidPassword(String password, String regex) {

		Pattern p = Pattern.compile(regex);// . represents single character
		Matcher m = p.matcher(password);
		boolean isValid = m.matches();
		if (!isValid) {
			printer.printError("Password must have 8 characters with Lower, Upper, & Special");
		}

		return isValid;
	}
	
	public static boolean checkValidPhoneNumber(String phoneNumber, String regex) {

		Pattern p = Pattern.compile(regex);// . represents single character
		Matcher m = p.matcher(phoneNumber);
		boolean isValid = m.matches();
		if (!isValid) {
			printer.printError("Phone number must be a valid United States number.");
		}

		return isValid;
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

	public static String getPhonenumberregex() {
		return phoneNumberRegex;
	}

}
