package com.cognixia.jump.controller;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cognixia.jump.application.DollarsBankApplication;
import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.Customer;
import com.cognixia.jump.model.SavingsAccount;
import com.cognixia.jump.utility.ConsolePrinterUtility;

public class DollarsBankController {

	private static Map<String, Customer> username_To_Customer = new HashMap<String, Customer>();

	private static Map<Integer, Account> customerId_To_Account = new HashMap<Integer, Account>();

	private static ArrayList<Customer> allCustomers_Accounts = new ArrayList<>();

	ConsolePrinterUtility printer = ConsolePrinterUtility.getInstance();

	private static DollarsBankController single_instance = null;

	private DollarsBankController() {

	}

	public void createNewAccount(Scanner scanner) {

		String date = getDateAndTime();

		printer.printFormattedTextBox("Enter Details for New Account");

		String options[] = printer.getNewAccountOptions();
		String userInput[] = new String[5];

		// grab all data except initialDeposit
		// print the options for creating account and store that input
		for (int i = 0; i < options.length - 1; i++) {
			System.out.println(options[i]);
			userInput[i] = scanner.nextLine();

		}

		// print initialDeposit option
		System.out.println(options[5]);
		Double initialDeposit = checkDouble(scanner);

		// account has a savingsAccount, will initialize within account constructor
		Account currentAccount = new Account(userInput[3], userInput[4], initialDeposit);

		// currentCustomer has an account
		Customer currentCustomer = new Customer(userInput[0], userInput[1], userInput[2], currentAccount);

		// Maps for grabbing needed user
		username_To_Customer.put(userInput[3], currentCustomer);
		customerId_To_Account.put(currentCustomer.getCustomerId(), currentAccount);
		// store that customer in memory
		allCustomers_Accounts.add(currentCustomer);

		printer.successMessage("Successful deposit of " + initialDeposit);

		currentAccount.addTransaction("\nInitial deposit of " + initialDeposit + " in the account [ "
				+ currentCustomer.getCustomerAccount().getUsername() + " ]");
		currentAccount.addTransaction("Balance - " + currentAccount.getSavings().getCurrentBalance() + " on " + date);

	}

	public Double checkDouble(Scanner scanner) {
		boolean isDouble = false;
		Double initialDeposit = 0.0;

		do {

			try {
				initialDeposit = Double.parseDouble(scanner.nextLine());
				isDouble = true;
			} catch (NumberFormatException e) {
				printer.printError("Please enter a number.");
			}
		} while (isDouble == false);
		return initialDeposit;
	}

	public boolean checkPassword(String password, String regex) {

		Pattern p = Pattern.compile(regex);// . represents single character
		Matcher m = p.matcher(password);
		boolean isValid = m.matches();

		return isValid;
	}

	public void login(Scanner scanner) {

		printer.printFormattedTextBox("Enter Login Details");
		System.out.println("Username :");
		String username = scanner.nextLine();
		System.out.println("Password :");
		String password = scanner.nextLine();

		Customer currentCustomer;

		boolean loggedIn = false;

		// if the username exists in the map
		if (username_To_Customer.containsKey(username)) {

			currentCustomer = username_To_Customer.get(username);

			// check to see if password is correct
			if (password.equals(currentCustomer.getCustomerAccount().getPassword())) {
				loggedIn = true;

				int counter = 0;
				printer.printFormattedTextBox("WELCOME " + username + "!!!");

				printCurrentBalance(currentCustomer);

				do {

					// Change home screen after initial log in
					if (counter > 0) {

						printer.printFormattedTextBox("Logged in as [ " + username + " ]");

						printCurrentBalance(currentCustomer);

					}

					// print all options for signed in user
					printer.printChoices(printer.getOptionsSignedIn());
					// print green choices
					printer.enterChoice(6);

					int choice = DollarsBankApplication.checkInt(scanner);

					switch (choice) {

					case 1:
						deposit(currentCustomer, scanner);

						break;
					case 2:

						withdraw(currentCustomer, scanner);

						break;
					case 3:

						fundsTransfer(currentCustomer, scanner);
						break;
					case 4:

						last5Transactions(currentCustomer, scanner);
						break;
					case 5:

						displayCustomerInformation(currentCustomer, scanner);

						break;

					case 6:
						loggedIn = false;
						break;

					default:
						printer.printError("Please select from the allowed input...\n\n");
						break;

					}
					counter++;
				} while (loggedIn == true);

			}

			else {

				printer.printError("Invalid Credentials. Try Again!");
			}

		}
		// if the user doesnt exist in that map
		else {
			printer.printError("\nThe Username '" + username + "' does not exist!");
		}

	}

	private void printCurrentBalance(Customer currentCustomer) {

		Double currentBalance = currentCustomer.getCustomerAccount().getSavings().getCurrentBalance();

		if (currentBalance <= 10.0) {
			printer.printError("Balance Low: " + currentBalance);

		} else {
			printer.successMessage("Your current balance is : " + currentBalance);

		}

	}

	private void displayCustomerInformation(Customer currentCustomer, Scanner scanner) {
		printer.printFormattedTextBox("Customer Information");

		System.out.println(currentCustomer.customerInformation());
	}

	private void last5Transactions(Customer currentCustomer, Scanner scanner) {

		printer.printFormattedTextBox("5 Most Recent Transactions");
		ArrayList<String> temp = currentCustomer.getCustomerAccount().getTransactions();

		for (String transaction : temp) {
			System.out.println(transaction);
		}
		System.out.println();
	}

	public String getDateAndTime() {

		Format formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");

		String date = formatter.format(new Date());

		return date;

	}

	public void deposit(Customer currentCustomer, Scanner scanner) {
		printer.printFormattedTextBox("Deposit");

		System.out.println(
				"\nYour current balance is : " + currentCustomer.getCustomerAccount().getSavings().getCurrentBalance());
		System.out.println("How much would you like to deposit?");
		String deposit = scanner.nextLine();

		Account currentAccount = currentCustomer.getCustomerAccount();

		currentAccount.getSavings().depositToAccount(Double.parseDouble(deposit));

		System.out.println(
				"\nAfter the deposit your current balance is: " + currentAccount.getSavings().getCurrentBalance());

		System.out.println();

		String date = getDateAndTime();

		currentAccount.addTransaction("\nDeposit of " + deposit + " in the account [ "
				+ currentCustomer.getCustomerAccount().getUsername() + " ]");
		currentAccount.addTransaction("Balance - " + currentAccount.getSavings().getCurrentBalance() + " on " + date);

	}

	public void withdraw(Customer currentCustomer, Scanner scanner) {
		printer.printFormattedTextBox("Withdraw");

		String date = getDateAndTime();

		Account currentAccount = currentCustomer.getCustomerAccount();

		Double currentBalance = currentAccount.getSavings().getCurrentBalance();

		Double withdraw;

		// keep asking to withdraw if the user has insufficient funds
		do {
			System.out.println("Your current balance is : " + currentBalance);
			System.out.println("How much would you like to withdraw?");
			withdraw = checkDouble(scanner);

			// withdraw only if possible
			if (isWithdrawPossible(currentCustomer, withdraw)) {
				currentCustomer.getCustomerAccount().getSavings().withdrawFromAccount(withdraw);

			}

		} while (withdraw > currentBalance);

		System.out.println("\nAfter the withdraw your current balance is: "
				+ currentCustomer.getCustomerAccount().getSavings().getCurrentBalance());

		System.out.println();

		currentAccount.addTransaction("\nWithdraw of " + withdraw + " from the account [ "
				+ currentCustomer.getCustomerAccount().getUsername() + " ]");
		currentAccount.addTransaction("Balance - " + currentAccount.getSavings().getCurrentBalance() + " on " + date);

	}

	public boolean isWithdrawPossible(Customer currentCustomer, Double withdraw) {

		Double currentBalance = currentCustomer.getCustomerAccount().getSavings().getCurrentBalance();
		if (withdraw == 0.0) {

			printer.printError("Insufficient funds in account for withdraw, your balance is 0.0");

		} else if (withdraw <= currentBalance) {

			return true;
		} else {
			printer.printError("Insufficient funds in account for withdraw, your balance is " + currentBalance);
		}
		return false;
	}

	public void fundsTransfer(Customer currentCustomer, Scanner scanner) {
		printer.printFormattedTextBox("Funds Transfer");
		String date = getDateAndTime();

		System.out.println("\nWhat is the username of the person you want to transfer money to?");
		String otherUsername = scanner.nextLine();

		// if the current customers user name is the same as the other username, print
		// message
		if (currentCustomer.getCustomerAccount().getUsername().equals(otherUsername)) {
			printer.printError("You cannot transfer money to your self!");
		} else if (username_To_Customer.containsKey(otherUsername)) {

			printer.successMessage("Successfully found user!");

			Double transferAmount = 0.0;

			Account currentCustomerAccount = currentCustomer.getCustomerAccount();
			String currentCustomerUserName = currentCustomerAccount.getUsername();

			Customer otherCustomer = username_To_Customer.get(otherUsername);
			Account otherCustomerAccount = otherCustomer.getCustomerAccount();

			printCurrentBalance(currentCustomer);

			System.out.println("How much money would you like to transfer to " + otherUsername + "?");
			transferAmount = checkDouble(scanner);

			if (isWithdrawPossible(currentCustomer, transferAmount)) {

				// withdraw from current customer saving account
				currentCustomerAccount.getSavings().withdrawFromAccount(transferAmount);

				// deposit into the other customers savings account
				otherCustomerAccount.getSavings().depositToAccount(transferAmount);

				printer.successMessage("Successfully transfered " + transferAmount + " to the account with username [ "
						+ otherUsername + " ]");

				// add a transaction for making transfer to another customer
				currentCustomerAccount.addTransaction("\nTransfer of " + transferAmount + " to the account [ "
						+ otherUsername + " ] from your account [ " + currentCustomer.getCustomerAccount().getUsername()
						+ " ]");
				currentCustomerAccount.addTransaction(
						"Balance - " + currentCustomerAccount.getSavings().getCurrentBalance() + " on " + date);

				// add a transaction for receiving transfer on other customer
				otherCustomerAccount.addTransaction("\nRevieved transfer of " + transferAmount + " from the account [ "
						+ currentCustomerUserName + " ] to your account [ " + otherUsername + " ]");
				otherCustomerAccount.addTransaction(
						"Balance - " + otherCustomerAccount.getSavings().getCurrentBalance() + " on " + date);

			}

		} else {
			printer.printError("User was not found.");

		}

	}

	public static DollarsBankController getInstance() {
		if (single_instance == null) {

			single_instance = new DollarsBankController();
		}
		return single_instance;

	}

	public static Map<String, Customer> getUsername_To_Customer() {
		return username_To_Customer;
	}

	public static void setUsername_To_Customer(Map<String, Customer> username_To_Customer) {
		DollarsBankController.username_To_Customer = username_To_Customer;
	}

	public static Map<Integer, Account> getCustomerId_To_Account() {
		return customerId_To_Account;
	}

	public static void setCustomerId_To_Account(Map<Integer, Account> customerId_To_Account) {
		DollarsBankController.customerId_To_Account = customerId_To_Account;
	}

	public static ArrayList<Customer> getAllCustomers_Accounts() {
		return allCustomers_Accounts;
	}

	public static void setAllCustomers_Accounts(ArrayList<Customer> allCustomers_Accounts) {
		DollarsBankController.allCustomers_Accounts = allCustomers_Accounts;
	}

}
