package com.techtalkathon.randomsynchronization;

public class HashMapWithoutSynchronizationDemo {

    public static void main(String[] args) throws InterruptedException {

        BankService bankService = new BankService();

        bankService.getAccounts().put(1001, 10_000);

        Thread thread1 = new Thread(() -> {

            for (int i = 0; i < 1000; i++) {
                bankService.deposit(1001, 10);
            }

        }, "Thread-1");


        Thread thread2 = new Thread(() -> {

            for (int i = 0; i < 1000; i++) {
                bankService.deposit(1001, 10);
            }

        }, "Thread-2");


        thread1.start();
        thread2.start();

        thread1.join(); // main thread wait until t1 thread complete to check final balance
        thread2.join(); // main thread wait untill t2 thread complete to check final balance
        // Main Thread waits untill both the threads complete then check final balance otherwise balance will be 10000
        System.out.println(
                "Final Balance: " +
                bankService.getBalance(1001)
        );
    }
}
