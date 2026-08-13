package com.techtalkathon.nonstaticmethosynchronization;

public class SerializedDemo {
	
	public static void main(String[] args) {
		
		System.out.println("\n***************** Two Threads on two different Objects *****************\n");
		twoThreadOnTwoObjects();
		
		System.out.println("\n***************** Two Threads on one Object *****************\n");
		twoThreadOnOneObject();

	}
	
	
	
	public static void twoThreadOnTwoObjects() {
		Display d1 = new Display();
		Display d2 = new Display();

		MyThread t1 = new MyThread(d1, "Dhoni");
		MyThread t2 = new MyThread(d2, "YuvRaj");

		t1.start();
		t2.start();
	}
	

	public static void twoThreadOnOneObject() {
		Display d = new Display();

		MyThread t1 = new MyThread(d, "Dhoni");
		MyThread t2 = new MyThread(d, "YuvRaj");

		t1.start();
		t2.start();
	}

}


