package com.techtalkathon.thread.basics.yield;

public class ThreadYieldExample implements Runnable {

	public static void main(String[] args) {
		ThreadYieldExample example = new ThreadYieldExample();
		Thread thread = new Thread(example);
		thread.start();
		
		for (int i = 0; i < 5; i++) {
			System.out.println("Main Thread ThreadName=" + Thread.currentThread().getName());
		}
		
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			Thread.yield();
			System.out.println("Child Thread ThreadName=" + Thread.currentThread().getName());
		}
	}

	// It does not guarantee that only other threads get executed 
	
}
