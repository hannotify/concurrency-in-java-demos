package com.github.hannotify.concurrencyinjavademos.memoryinconsistency;

public class MemoryInconsistency {
    private static boolean pitstop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread teamRadio = new Thread(() -> {
            while (!pitstop) {}
            System.out.println("[TEAM-RADIO] Box, box.");

            while (pitstop) {}
            System.out.println("[TEAM-RADIO] Pitstop end, release clutch.");
        });

        teamRadio.start();

        Thread.sleep(1000);
        System.out.println("[TEAM] Pitstop time.");
        pitstop = true;

        Thread.sleep(1000);

        System.out.println("[TEAM] Pitstop end.");
        pitstop = false;
    }
}
