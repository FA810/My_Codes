package com.fabio.connectors;

public class UserWallet {
	String username;
	int balance;
	int lastTransaction;
	String balanceVersion;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getLastTransaction() {
		return lastTransaction;
	}
	public void setLastTransaction(int lastTransaction) {
		this.lastTransaction = lastTransaction;
	}
	public String getBalanceVersion() {
		return balanceVersion;
	}
	public void setBalanceVersion(String balanceVersion) {
		this.balanceVersion = balanceVersion;
	}	
}
