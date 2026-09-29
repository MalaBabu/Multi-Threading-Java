package com.techtalkathon.thread.deadlock;

public class ClassA {

	public synchronized void methodA(ClassB b) {
		System.out.println("ClassA MethodA Method");
		b.TestB(); // Thread needs ClassB object lock to execute this method
	}

	public synchronized void TestA() {
		System.out.println("ClassA TestA Method");
	}

}
