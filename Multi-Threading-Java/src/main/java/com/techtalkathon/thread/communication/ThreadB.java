package com.techtalkathon.thread.communication;

public class ThreadB extends Thread {
	int total = 0;

	@Override
	public void run() {
		synchronized (this) {
			System.out.println("Child Thread starts calculation");
			for (int i = 1; i <= 100; i++) {
				total = total + i;
			}
			System.out.println("Child (ThreadB) Thread trying to give notification using notify() method");
			this.notify();
			System.out.println("ThreadB Run Method End");
		}
		// 1 crore lines of the code
		// i.e., Remaining Lines
	}
}
