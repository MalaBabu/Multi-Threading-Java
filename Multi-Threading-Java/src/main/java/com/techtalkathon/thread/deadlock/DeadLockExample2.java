package com.techtalkathon.thread.deadlock;

public class DeadLockExample2 {

	public static void main(String[] args) {
		
		DeadLockExample2 deadLock1 = new DeadLockExample2();
		DeadLockExample2 deadLock2 = new DeadLockExample2();
		
		Thread t1 = new Thread(()->{
			
			synchronized (deadLock1) {
				System.out.println("with-in thread1 sync block of deadLock1");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (deadLock2) {
					System.out.println("with-in thread1 sync block of deadLock2");

				}

			}
		});
		
		Thread t2 = new Thread(()->{
			
			synchronized (deadLock2) {
				System.out.println("with-in thread2 sync block of deadLock2");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (deadLock1) {
					System.out.println("with-in thread2 sync block of deadLock1");

				}

			}
		});
		
		t1.start();
		t2.start();
		
		System.out.println("t1 - "+t1.getState());
		System.out.println("t2 - "+t2.getState());
		
	}
	
	/*
	
	1. Thread1 waits for lock deadLock2 .. which is aquired by Thread2 
	2. Thread2 waits for lock deadLock1 .. which is aquired by Thread1 
	
	This is what called as Dead lock situation where two threads waiting for each other.
	
	---------------------------------------------------------------------------------
	
	To Prevent this issue we can go for proper ordering in the locking
	
	a1 -> a2 or a2 -> a1
	
	example :
	
		Thread t1 = new Thread(()->{
			
			synchronized (deadLock1) {
				System.out.println("with-in thread1 sync block of deadLock1");
				
				synchronized (deadLock2) {
					System.out.println("with-in thread2 sync block of deadLock1");

				}

			}
		});
		
		
		Thread t2 = new Thread(()->{
			
			synchronized (deadLock1) {
				System.out.println("with-in thread2 sync block of deadLock1");
				
				synchronized (deadLock2) {
					System.out.println("with-in thread2 sync block of deadLock2");
				}
			}
		});
		

	OUTPUT 
	========
	t1 - RUNNABLE
	t2 - BLOCKED
	with-in thread1 sync block of a1
	with-in thread1 sync block of a2
	with-in thread2 sync block of a2
	with-in thread2 sync block of a1
	
	*/

}
