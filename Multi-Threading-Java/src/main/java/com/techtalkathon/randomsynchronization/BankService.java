package com.techtalkathon.randomsynchronization;

import java.util.HashMap;
import java.util.Map;

public class BankService extends Thread {

	private final Map<Integer, Integer> accounts = new HashMap<>();

	public synchronized void deposit(Integer accountId, Integer amount) {
//		synchronized (this) {
			Integer balance = accounts.get(accountId);
			balance = balance + amount;
			accounts.put(accountId, balance);
//		}
	}

	public Integer getBalance(Integer accountId) {
		return accounts.get(accountId);
	}

	public Map<Integer, Integer> getAccounts() {
		return accounts;
	}

}
