package com.infosupport.concurjava.threadtypes;

public class ThreadTypes {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread());

        Thread importantThread = new Thread(() -> calculatePrimes(1, 100001));
        importantThread.setName("Important-Thread");
        importantThread.setPriority(Thread.MAX_PRIORITY);
        printThreadInfo(importantThread);

        Thread daemonThread = new Thread(() -> calculatePrimes(1, 100001));
        daemonThread.setDaemon(true);

        importantThread.start();
        daemonThread.start();

        printThreadInfo(daemonThread);
        if (daemonThread.isDaemon()) System.out.println("daemon thread");
    }

    private static void calculatePrimes(int i, int i1) {

    }

    private static void printThreadInfo(Thread thread) {
        System.out.println(thread);
        System.out.println(thread.isDaemon());
        System.out.println(Thread.activeCount());
    }
}