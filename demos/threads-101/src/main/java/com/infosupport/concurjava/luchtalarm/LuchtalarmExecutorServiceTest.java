package com.infosupport.concurjava.luchtalarm;

import java.util.List;
import java.util.concurrent.*;

public class LuchtalarmExecutorServiceTest {
    private static List<Luchtalarm> testset = List.of(
            new Luchtalarm("Leusden", 15),
            new Luchtalarm("Veenendaal", 15),
            new Luchtalarm("Deurne", 10),
            new Luchtalarm("Rotterdam", 25),
            new Luchtalarm("Antwerpen", 20));

    public static void main(String[] args) {
        luchtalarmTestMetCompletionService();
    }

    private static void luchtalarmTestMetExecute() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        testset.forEach(l -> executorService.execute(l::test));
    }

    private static void luchtalarmTestMetSubmit() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (Luchtalarm l : testset) {
            var future = executorService.submit(l::testWithResult);

            while (!future.isDone()) {
                System.out.println(String.format("Test in %s is nog bezig...", l.getPlaats()));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("Test in %s is voltooid.", l.getPlaats()));
        }

        executorService.shutdown();
    }

    private static void luchtalarmTestMetCompletionService() {
        var executorservice = Executors.newCachedThreadPool();
        var completionService = new ExecutorCompletionService<String>(executorservice);

        testset.forEach(l -> completionService.submit(l::testWithResult));

        for (Luchtalarm luchtalarm : testset) {
            Future<String> futureResult = null;
            try {
                futureResult = completionService.take();
                System.out.println(String.format("Test in %s is voltooid.", futureResult.get()));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }

        executorservice.shutdown();
    }
}
