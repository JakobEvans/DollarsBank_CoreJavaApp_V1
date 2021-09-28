package com.cognixia.jump.model;

public class SavingsAccount {
	
	
	
	
	
	private Double currentBalance;
	
	
	
	
	
	public SavingsAccount(Double currentBalance) {
		super();
		this.currentBalance = currentBalance;
	}
	public SavingsAccount() {
		super();
		this.currentBalance = 0.0;

	
	}
	public void depositToAccount(Double amount) {
			
			this.currentBalance = currentBalance + amount;
			
	}
	
	public void withdrawFromAccount(Double amount) {
		
		this.currentBalance = currentBalance - amount;
		
	}


	@Override
	public String toString() {
		return "SavingsAccount [currentBalance=" + currentBalance + "]";
	}
	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}


	
	
	
	
	

}
