package com.bank;

import java.util.List;
import java.util.Observable;

public class Account extends Observable{
   private String Name;
   private String uuid;
   private User u;
   double balance = 0.0;
   private List<Transaction> transactions;
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public Account(String name, User u, Bank theBank) {
	this.Name = name;
	this.uuid = theBank.getNewAccountUUID();
	this.u = u;
	System.out.println("The Account created with Account ID => " + this.uuid + " for the User named " + this.u.getFirstName());
}

 public void balanceChanged(){
   setChanged();
   notify();
 }

public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
	balanceChanged();
}
public String getUuid() {
	return uuid;
}
public void setUuid(String uuid) {
	this.uuid = uuid;
}
public User getUser() {
	return u;
}
public void addUser(User user) {
	this.u = user;
}
public List<Transaction> getTransactions() {
	return transactions;
}
public void setTransactions(List<Transaction> transactions) {
	this.transactions = transactions;
}
   
   
}
