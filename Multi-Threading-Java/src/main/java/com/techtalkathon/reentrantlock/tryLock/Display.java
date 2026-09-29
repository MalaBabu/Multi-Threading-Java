package com.techtalkathon.reentrantlock.tryLock;

import java.util.concurrent.locks.ReentrantLock;

public class Display {
	ReentrantLock lock = new ReentrantLock();

	public void wish(String name) {
		// 1 lakh ++ code
		
		
		System.out.println(lock.isLocked());
		System.out.println(lock.hasQueuedThreads());
		System.out.println(lock.isFair());
		System.out.println(lock.getHoldCount());
		System.out.println(lock.isHeldByCurrentThread());
		System.out.println(lock.getQueueLength());
		
		if(lock.tryLock()) {
			for (int i = 0; i < 10; i++) {
				System.out.print("Good Morning: ");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(name);
			}
			
			lock.unlock();
		}else {
			System.out
					.println(Thread.currentThread().getName() + " ...... Unable to aquire the lock for current Thread");
		}

		// 1 lakh ++ code
		
	}
}
