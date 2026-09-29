package com.techtalkathon.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorTest {
	
	public static void main(String[] args) {
		
		System.out.println("Main Method - Starts");

		
//		ExecutorService executorService = Executors.newFixedThreadPool(3);
//		
//		for(int i=0;i<10;i++)
//		{
//			executorService.submit(()->{
//				
//				System.out.println(Thread.currentThread().getName());
//			});
//			
//		}
//
//		executorService.submit(()->{
//			
//			System.out.println(Thread.currentThread().getName());
//		});
//		
//		executorService.submit(()->{
//			
//			System.out.println(Thread.currentThread().getName());
//		});
//		
//		executorService.submit(()->{
//			
//			System.out.println(Thread.currentThread().getName());
//		});
//		executorService.submit(()->{
//			
//			System.out.println(Thread.currentThread().getName());
//		});
//		executorService.submit(()->{
//			
//			System.out.println(Thread.currentThread().getName());
//		});
//		executorService.submit(()->{
//			
//			System.out.println(Thread.currentThread().getName());
//		});
//		executorService.submit(()->{
//			
//			System.out.println(Thread.currentThread().getName());
//		});
//		executorService.submit(()->{
//			
//			System.out.println(Thread.currentThread().getName());
//		});
//		executorService.submit(()->{
//			
//			System.out.println(Thread.currentThread().getName());
//		});
//		
//		executorService.shutdown();
		
		executorServiceExample1();
		
		System.out.println("Main Method - Ends");

	}

	
	
	public static void executorServiceExample1() {
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		List<Integer>  name = new ArrayList<Integer>();
		
		Callable<Integer> runnable = () -> {
			System.out.println("Thread : " + Thread.currentThread().getName() + " --> Running the Logic");
			System.out.println("Thread : " + Thread.currentThread().getName() + " --> Completed the execution");
			Thread.sleep(10000);
			System.out.println("sleeping done");
			return 10;
		};
		for (int i = 1; i <= 10; i++) {
			Future<Integer> submit = executorService.submit(runnable);// Submitting the Runnable Job
			try {
				System.out.println("future get object");
				name.add(submit.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name);
		executorService.shutdown();
	}
}
