package com.techtalkathon.thread.group;

public class ThreadGroupExample {

	public static void main(String[] args) {

		String threadGroupName = Thread.currentThread()
				.getThreadGroup().getName(); 
		String parentThreadGroupName = Thread.currentThread()
				.getThreadGroup().getParent().getName();
		System.out.println(threadGroupName); // main
		System.out.println(parentThreadGroupName); // system
		
		ThreadGroup group = new ThreadGroup("Parent Group");
		System.out.println(group.getParent().getName());
		System.out.println(group.getName());
//		group.setMaxPriority(6);
		ThreadGroup chileGroup = new ThreadGroup(group,"Child Group");
		
		System.out.println(chileGroup.getParent().getName());
		System.out.println(chileGroup.getName());
		
		Thread t1 = new Thread(()->{
			ThreadGroup cg = new ThreadGroup("customThread Group");
			System.out.println("Thread t1 => "+cg.getParent().getName());
			System.out.println(cg.getName());
		},"customThread");
		t1.start();
		
		System.out.println(group.getMaxPriority());
		System.out.println(chileGroup.getMaxPriority());


	}
}
