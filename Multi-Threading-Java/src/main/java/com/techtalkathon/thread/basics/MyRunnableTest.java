package com.techtalkathon.thread.basics;

public class MyRunnableTest {

	public static void main(String[] args) {

		MyRunnable myRunnable = new MyRunnable();
		Thread t1 = new Thread();
		Thread t2 = new Thread(myRunnable);
		
		String name = Thread.currentThread().getName();
		String name1 = t1.getName();
		String name2 = t2.getName();
		System.out.println(" name = "+name);
		System.out.println(" name1 = "+name1);
		System.out.println(" name2 = "+name2);
		
		Thread.currentThread().setName("Pavan Kalyan");
		System.out.println(" mainThreadName = "+Thread.currentThread().getName());


		
		/*
		 *		System.out.println(10/0);
	name = main
 	name1 = Thread-0
 	name2 = Thread-1
 	mainThreadName = Pavan Kalyan
	Exception in thread "Pavan Kalyan" java.lang.ArithmeticException: / by zero
	at com.techtalkathon.thread.basics.MyRunnableTest.main(MyRunnableTest.java:21)
		 */
		t1.start(); // thread created but no run method invokation
		t1.run();   // No Implementation
//		t1.start(); // Exception in thread "main" java.lang.IllegalThreadStateException

		t2.start(); // thread created & run method invoked
		t2.run();   // Run Method is called

		for (int i = 0; i < 10; i++) {
			System.out.println("Main Thread");
		}
	}

}
