package com.infosupport.concurjava.threadtypes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

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

    private static void printThreadInfo(Thread thread) {
        System.out.println(thread);
        System.out.println(thread.isDaemon());
        System.out.println(Thread.activeCount());
    }

    private static void calculatePrimes(long lowerLimit, long upperLimit) {
        final List<Long> primes = LongStream.rangeClosed(lowerLimit, upperLimit)
                .filter(ThreadTypes::isPrime)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        System.out.println(primes);

        System.out.println(System.nanoTime() + " / " + Thread.currentThread() + " is done.");
    }

    private static boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        }
        for (long i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}