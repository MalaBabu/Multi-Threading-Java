package com.techtalkathon.synchronizationclassblock;

public class Display {

	public void wish(String name) {
		// 1 lakh ++ code
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				System.out.print("Good Morning: ");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(name);
			}
		}
		// 1 lakh ++ code
	}

}
