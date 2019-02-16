package com.bank;

import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {
		Bank bank = new Bank("HDFC Bank");
		bank.addUser("xxx", "abc", "89", "1234");
		Scanner sc = new Scanner(System.in);
		String userId, pinNumber;
		User authUser;
		
		do{
		  System.out.println("Welcome to " + bank.getName());
		  System.out.println("Please enter your user id");
		  userId = sc.next();
		  System.out.println("Please enter your pin number");
		  pinNumber = sc.next();
		  authUser = bank.validateUserLogin(userId, pinNumber); 
		  if(authUser != null){
			System.out.println("Welcome Mr/Mrs " + authUser.getFirstName());
			printMainMenu(sc, bank, authUser);
		  } else {
			System.err.println("Invalid combination of user id/pin combination");  
		  }
		}while(authUser==null);
	}

	public static void printMainMenu(Scanner sc, Bank bank, User authUser) {
		int choice;
		System.out.println("Please select your transaction");
		System.out.println("1.Balance Enquiry");
		System.out.println("2.Withdrawl");
		System.out.println("3.Deposit");
		System.out.println("4.Transfer Amount");
		System.out.println("5.Quit");
		
		System.out.println("Please enter your choice");
		choice = sc.nextInt();
		if(choice>=1 || choice<=5){
		  switch(choice){
		  case 1:
			  double balance = bank.getMyBalance(authUser);
			  System.out.println("Your balance is " + balance);
			  anotherTransaction(sc, bank, authUser);
			  break;
		  case 2:
			  System.out.println("Enter the amount to withdraw ");
			  double withdrawAmount = sc.nextDouble();
			  double currentBalanceAfterWithdraw = bank.withDrawAmount(authUser, withdrawAmount);
			  System.out.println("You have withdrawn the amount of: " + withdrawAmount + " and your current balance is " + currentBalanceAfterWithdraw);
			  anotherTransaction(sc, bank, authUser);
			  break;
		  case 3:
			  System.out.println("Enter the amount to deposit ");
			  double depositAmount = sc.nextDouble();
			  double currentBalanceAfterDeposit = bank.depositAmount(authUser, depositAmount);
			  System.out.println("You have deposited the amount of: " + depositAmount + " and your current balance is " + currentBalanceAfterDeposit);
			  anotherTransaction(sc, bank, authUser);
			  break;
		  case 4:
			  //transferAmount();
			  break;
		  case 5:
			  quitMyTransaction(bank);
			  break;
		  default:
			  System.out.println("Please enter the valid option");
			  break;
		  }
		}else {
		 System.out.println("Please select the valid option");	
		}
	}

	public static void anotherTransaction(Scanner sc, Bank bank, User authUser) {
        System.out.println("Do you want another transaction ? Please enter 1 else 0 to quit");
        int choice = sc.nextInt();
        if(choice == 0 || choice == 1){
          if(choice == 1){
        	 printMainMenu(sc, bank, authUser); 
          } else {
        	 quitMyTransaction(bank); 
          }
        } else {
          System.err.println("Invalid option, please select a valid option");
          anotherTransaction(sc, bank, authUser);
        }
	}

	public static void quitMyTransaction(Bank bank) {
		System.out.println("Thanks for using " + bank.getName());
		System.exit(1);
	}

}
