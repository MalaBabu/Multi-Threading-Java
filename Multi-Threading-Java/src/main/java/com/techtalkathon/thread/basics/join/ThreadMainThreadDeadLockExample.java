package com.techtalkathon.thread.basics.join;

public class ThreadMainThreadDeadLockExample {
	public static void main(String[] args) throws InterruptedException {
		Thread.currentThread().join();
	}

}
