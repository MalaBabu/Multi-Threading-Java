package com.techtalkathon.thread.basics.join;

public class ThreadJoinMainThreadDeadLockExample implements Runnable {

	static Thread mt;
	public static void main(String[] args) throws InterruptedException {
		
		ThreadJoinMainThreadDeadLockExample.mt = Thread.currentThread();
		ThreadJoinMainThreadDeadLockExample example = new ThreadJoinMainThreadDeadLockExample();
		Thread childThread = new Thread(example);
		childThread.start();

		childThread.join(); // main Thread waits until childThread completes
		for (int i = 0; i < 5; i++) {
			System.out.println("Main Thread ThreadName=" + Thread.currentThread().getName());
		}

	}

	@Override
	public void run() {
		try {
			mt.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 5; i++) {
			System.out.println("Child Thread ThreadName=" + Thread.currentThread().getName());
		}
	}


}

/* Output always
 * --------------
Dead Lock ===> Main thread calling Child thread --> then Child thread calling Main Thread
 */
