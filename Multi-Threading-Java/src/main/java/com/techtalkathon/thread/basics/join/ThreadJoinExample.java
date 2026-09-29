package com.techtalkathon.thread.basics.join;

public class ThreadJoinExample implements Runnable {

	public static void main(String[] args) throws InterruptedException {
		ThreadJoinExample example = new ThreadJoinExample();
		Thread childThread = new Thread(example);
		childThread.start();

		childThread.join(); // main Thread waits until childThread completes
		for (int i = 0; i < 5; i++) {
			System.out.println("Main Thread ThreadName=" + Thread.currentThread().getName());
		}

	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Child Thread ThreadName=" + Thread.currentThread().getName());
		}
	}


}

/* Output always
 * --------------
Child Thread ThreadName=Thread-0
Child Thread ThreadName=Thread-0
Child Thread ThreadName=Thread-0
Child Thread ThreadName=Thread-0
Child Thread ThreadName=Thread-0
Main Thread ThreadName=main
Main Thread ThreadName=main
Main Thread ThreadName=main
Main Thread ThreadName=main
Main Thread ThreadName=main
 */
