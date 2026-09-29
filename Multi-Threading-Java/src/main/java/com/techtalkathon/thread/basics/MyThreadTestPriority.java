package com.techtalkathon.thread.basics;

public class MyThreadTestPriority {
	
	public static void main(String[] args) {

		System.out.println(Thread.currentThread().getPriority());
		Thread.currentThread().setPriority(10);
		MyRunnable myRunnable = new MyRunnable();
		Thread t0 = new Thread(myRunnable);
		t0.setPriority(2);
		Thread t1 = new Thread(myRunnable);
		t0.setPriority(4);
		t0.start();
		t1.start();
		System.out.println("Main Method");
	}

}
