package com.techtalkathon.thread.communication;

public class NumberCounterWithFiveThreads {
	private int max = 20;
	private int num = 1;
	private int threadCount = 5;

	public static void main(String[] args) {

		NumberCounterWithFiveThreads numberCounterWithFiveThreads = new NumberCounterWithFiveThreads();
		for (int i = 1; i <= 5; i++) {
			final int threadNumber = i;
			Thread thread = new Thread(() -> {
				numberCounterWithFiveThreads.print(threadNumber);
			}, "Thread" + threadNumber);
			thread.start();
		}
	}

	public void print(int threadNumber) {
		while (num <= max) {
			synchronized (this) {
				if ((num - 1) % threadCount == (threadNumber - 1)) {
					System.out.println(Thread.currentThread().getName() + " Value: " + num);
					num++;
					this.notifyAll();
				} else {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
}
