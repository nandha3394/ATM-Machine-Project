package com.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.org.apache.bcel.internal.generic.BALOAD;

public class Bank {

	private String name;
	private List<Account> accounts;
	private List<User> users;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void addAccounts(Account account) {
		this.accounts.add(account);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
	public Bank(String name) {
		super();
		this.name = name;
		this.accounts = new ArrayList<Account>();
		this.users = new ArrayList<User>();
	}

	public String getNewUserUUID() {
		int length = 6;
		String userUUID = "";
		Random r = new Random();
		boolean nonUnique = false;
		for(int i=0; i<length; i++){
		 userUUID += ((Integer)r.nextInt(10)).toString();	
		}
		do{
		 for(User user : this.users){
	       if(user.getUuid().compareTo(userUUID) == 0){
	    	  nonUnique = true; 
	       } else {
	    	   nonUnique = false;
	    	   break;
	       }
		 }
		} while(nonUnique);
		return userUUID;
	}

	public String getNewAccountUUID() {
		int length = 10;
		String accountUUID = "";
		Random r = new Random();
		boolean nonUnique = false;
		for(int i=0; i<length; i++){
		 accountUUID += ((Integer)r.nextInt(10)).toString();	
		}
		do{
		 for(Account account : this.accounts){
	       if(account.getUuid().compareTo(accountUUID) == 0){
	    	  nonUnique = true; 
	       } else {
	    	   nonUnique = false;
	    	   break;
	       }
		 }
		} while(nonUnique);
		return accountUUID;

	}
	
	public User addUser(String firstName, String lastName, String mobileNumber, String pinNumber){
		User newUser = new User(firstName, lastName, mobileNumber, pinNumber, this);
		Account account = new Account("Savings Account", newUser, this);
		account.addObserver(new MessagingService());
		this.addAccounts(account);
		newUser.addAccount(account);
		this.users.add(newUser);
		return newUser;
	}

	public User validateUserLogin(String userId, String pinNumber) {
	    for(User user : this.users){
	      if((user.getUuid().compareTo(userId) == 0) && user.validatePin(pinNumber)){
	    	return user;  
	      }
	    }
		return null;
	}

	public double getMyBalance(User user) {
		Account account = user.getAccounts().get(0);
		return account.getBalance();
	}

	public double depositAmount(User user, double amount) {
		Account account = user.getAccounts().get(0);
		account.setBalance(account.balance + amount);
		return account.balance;
	}

	public double withDrawAmount(User authUser, double amount) {
		Account account = authUser.getAccounts().get(0);
		if(account.getBalance() >= amount){
		  account.setBalance(account.balance - amount);	
		} else {
		  System.err.println("Insufficient funds");	
		}
		return account.balance;
	}

}
