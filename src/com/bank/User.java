package com.bank;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class User {

	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String uuid;
	private byte pinHash[];
	private List<Account> accounts;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public byte[] getPinHash() {
		return pinHash;
	}
	public void setPinHash(byte[] pinHash) {
		this.pinHash = pinHash;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void addAccount(Account account) {
		this.accounts.add(account);
	}
	public User(String firstName, String lastName, String mobileNumber, String pincode, Bank theBank) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.uuid = theBank.getNewUserUUID();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pincode.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(1);
		} 
		this.pinHash = pinHash;
		this.accounts = new ArrayList<Account>();
		System.out.println("The user named " + firstName + " created with userID => " + this.uuid);
	}
	public boolean validatePin(String pinNumber) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(this.pinHash, md.digest(pinNumber.getBytes())) ;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}
	
}
