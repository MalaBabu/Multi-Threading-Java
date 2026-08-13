package com.techtalkathon.staticmethosynchronization;

public class MyThread extends Thread {

	private Display display;
	private String name;

	public MyThread(Display display, String name) {
		this.display = display;
		this.name = name;
	}

	@Override
	public void run() {
		display.wish(name); // or (  Display.wish(name)  )
	}

}
