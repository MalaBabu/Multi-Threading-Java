package com.techtalkathon.thread.communication;

public class ThreadA {
	public static void main(String[] args) throws InterruptedException {
		ThreadB b = new ThreadB();
		b.start();
//		b.join();            ---> Not Recommended
		Thread.sleep(1000);  //---> Not Recommended
		synchronized (b) {
			System.out.println("Main Thread trying to call wait() method");
			b.wait(1000);
			System.out.println("Main Thread got Notification");
			System.out.println(b.total);
		}
	}
}
