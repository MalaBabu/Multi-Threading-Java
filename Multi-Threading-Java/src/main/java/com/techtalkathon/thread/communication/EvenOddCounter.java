package com.techtalkathon.thread.communication;

public class EvenOddCounter implements Runnable {
	private int max = 20;
	private int num = 1;

	public static void main(String[] args) {

		Runnable r = new EvenOddCounter();

		Thread t1 = new Thread(r, "Odd");

		Thread t2 = new Thread(r, "Even");
		t1.start();
		t2.start();
	}

	public void run() {
//		System.out.println("Run Method");
		while (num <= max) {

			synchronized (this) {
				if (num % 2 == 1) {
					if (Thread.currentThread().getName().equals("Odd")) {
						System.out.println("Thread: " + Thread.currentThread().getName() + " Value: " + num);
						num++;
						this.notifyAll();
					} else {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				} else {
					if (Thread.currentThread().getName().equals("Even")) {
						System.out.println("Thread: " + Thread.currentThread().getName() + " Value: " + num);
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
}
