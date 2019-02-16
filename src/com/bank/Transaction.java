package com.bank;

import java.util.Date;
import java.util.Observable;

public class Transaction extends Observable {
   double amount;
   Date timeStamp;
}
