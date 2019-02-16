package com.bank;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Accordion;

public class MessagingService implements Observer{

	@Override
	public void update(Observable observable, Object object) {
		if(observable instanceof Account){
			Account account = (Account) observable;
			sendMessageNotification(account.getUser());
		}
	}

	private void sendMessageNotification(User user) {
		System.out.print("Message notification send to the user named " + user.getFirstName() + " with mobile number " + user.getMobileNumber());
	}

}
