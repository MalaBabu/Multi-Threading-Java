package com.techtalkathon.thread.deadlock;

public class DeadLockExample {

	public static void main(String[] args) {

		ClassA a = new ClassA();

		ClassB b = new ClassB();

		Thread t1 = new Thread(() -> {
			a.methodA(b);
		});

		Thread t2 = new Thread(() -> {
			b.methodB(a);
		});

		t1.start();
		t2.start();
	}

}
