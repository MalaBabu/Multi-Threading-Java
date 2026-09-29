package com.techtalkathon.thread.deadlock;

public class ClassB {

	public synchronized void methodB(ClassA a) {
		System.out.println("ClassB MethodB Method");
		a.TestA(); // Thread needs ClassB object lock to execute this method
	}

	public synchronized void TestB() {
		System.out.println("ClassB TestB Method");
	}

}
