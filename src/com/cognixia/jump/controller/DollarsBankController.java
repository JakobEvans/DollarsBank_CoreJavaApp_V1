package com.cognixia.jump.controller;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
	
	private static Map<String,Customer> username_To_Customer = new HashMap<String, Customer>();

	private static Map<Integer,Account> customerId_To_Account = new HashMap<Integer, Account>();
	
	private static ArrayList<Customer> allCustomers_Accounts= new ArrayList<>();
	
	ConsolePrinterUtility printer = ConsolePrinterUtility.getInstance();
	
	private static DollarsBankController single_instance = null;
	
	
	private DollarsBankController(){
		
		
	}
	
	public void createNewAccount(Scanner scanner) {
		
	
		
		
		printer.printFormattedTextBox("Enter Details for New Account");		
		
		String options[] = printer.getNewAccountOptions();
		String userInput[] = new String[6];
		
		// print the options for creating account and store that input
		for(int i = 0; i < options.length; i++) {
			System.out.println(options[i]);
			
			userInput[i] = scanner.nextLine();
		}
		
		
		System.out.println("Printing the array:");
		System.out.println(Arrays.toString(userInput));
		
		// account has a savingsAccount
//		Account account = new Account(userInput[3],userInput[4],Double.parseDouble(userInput[5]), new SavingsAccount(Double.parseDouble(userInput[5])));
		Account account = new Account(userInput[3],userInput[4],Double.parseDouble(userInput[5]));

		//Customer has an account
		Customer customer = new Customer(userInput[0],userInput[1], userInput[2], account);

		System.out.println("\n\nThe account and customer object:");
		System.out.println(account.toString());
		System.out.println(customer.toString() +"\n");
		
		
		username_To_Customer.put(userInput[3], customer);
		customerId_To_Account.put(customer.getCustomerId(), account);
		
//		
//		System.out.println("Getting customer by username:");
//		System.out.println(username_To_Customer.get("d"));
//		
//		System.out.println();
//		System.out.println("Getting account by customerID:");
//		System.out.println(customerId_To_Account.get(customer.getCustomerId()));
//		
		
		allCustomers_Accounts.add(customer);
//		
//		System.out.println("\n\nAll accounts");
//		System.out.println((allCustomers_Accounts));
		
	}
	
	
	public boolean checkPassword(String password, String regex) {
		
		Pattern p = Pattern.compile(regex);//. represents single character  
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
		if(username_To_Customer.containsKey(username)) {
			
			currentCustomer = username_To_Customer.get(username);
			
			//check to see if password is correct
			if(password.equals(currentCustomer.getCustomerAccount().getPassword())) {
				loggedIn = true;
				
				int counter = 0;
				printer.printFormattedTextBox("WELCOME " +  username + "!!!");

				do {
					
					if(counter > 0) {
						printer.printFormattedTextBox("Logged in as [ " +  username + " ]");

						
					}
					
					printer.printChoices(printer.getOptionsSignedIn());
					
					printer.enterChoice(6);
					
					
					int choice = DollarsBankApplication.checkInt(scanner);
					
					switch(choice) {
					
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
				}
				while(loggedIn == true);
				
				
				
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
	
	public String getDateAndTime(){
		

	    Format formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");

	    String date = formatter.format(new Date());
	    
	    return date;

		
	}

	public void deposit(Customer currentCustomer, Scanner scanner) {
		printer.printFormattedTextBox("Deposit");
		
	
		
		System.out.println("\nYour current balance is : " + currentCustomer.getCustomerAccount().getSavings().getCurrentBalance());
		System.out.println("How much would you like to deposit?");
		String deposit = scanner.nextLine();
		
		
		Account currentAccount = currentCustomer.getCustomerAccount();
		
		
		
		currentAccount.getSavings().depositToAccount(Double.parseDouble(deposit));
		
		
		System.out.println("\nAfter the deposit your current balance is: " + currentAccount.getSavings().getCurrentBalance());

		System.out.println();		
		
		
		String date = getDateAndTime();
		
		
		currentAccount.addTransaction("\nDeposit of " + deposit + " in the account [ " + currentCustomer.getCustomerAccount().getUsername() + " ]");
		currentAccount.addTransaction("Balance - " + currentAccount.getSavings().getCurrentBalance() + " on " + date);

		
		
		
//		System.out.println("Printing Transactions");
//		for(int i = 0; i < currentAccount.getTransactions().size()-1; i++) {
//			System.out.println(currentAccount.getTransactions().get(i));
//		}

	}
	
	public void withdraw(Customer currentCustomer, Scanner scanner) {
		printer.printFormattedTextBox("Withdraw");
		
		Double currentBalance = currentCustomer.getCustomerAccount().getSavings().getCurrentBalance();
		
	
		Double withdraw;
		
		// keep asking to withdraw if the user has insufficient funds
		do{
			System.out.println("Your current balance is : " + currentBalance);
			System.out.println("How much would you like to withdraw?");
			withdraw = Double.parseDouble(scanner.nextLine());
			if(withdraw <= currentBalance) {
		
				currentCustomer.getCustomerAccount().getSavings().withdrawFromAccount(withdraw);
			}
			else {
				
				System.out.println("Insufficient funds in account for withdraw");
			}
		}
		while(withdraw > currentBalance);
		
		
		System.out.println("After the withdraw your current balance is: " + currentCustomer.getCustomerAccount().getSavings().getCurrentBalance());

		System.out.println();
		
	}
	
//	public void storeTransaction(String transaction) {
//		
//		currentCustomer.getCustomerAccount().addTransaction("dad");
//	}
	
	public void fundsTransfer(Customer currentCustomer, Scanner scanner) {
		printer.printFormattedTextBox("Funds Transfer");

		
	}
	
	public static DollarsBankController getInstance() {
		if(single_instance == null) {
			
			single_instance = new DollarsBankController();
		}
		return single_instance;
		
	}

	public static Map<String,Customer> getUsername_To_Customer() {
		return username_To_Customer;
	}

	public static void setUsername_To_Customer(Map<String,Customer> username_To_Customer) {
		DollarsBankController.username_To_Customer = username_To_Customer;
	}

	public static Map<Integer,Account> getCustomerId_To_Account() {
		return customerId_To_Account;
	}

	public static void setCustomerId_To_Account(Map<Integer,Account> customerId_To_Account) {
		DollarsBankController.customerId_To_Account = customerId_To_Account;
	}

	public static ArrayList<Customer> getAllCustomers_Accounts() {
		return allCustomers_Accounts;
	}

	public static void setAllCustomers_Accounts(ArrayList<Customer> allCustomers_Accounts) {
		DollarsBankController.allCustomers_Accounts = allCustomers_Accounts;
	}


}
