import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.LongStream;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        System.out.println("# of processors: " + Runtime.getRuntime().availableProcessors());

        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<List<Long>> completionService = new ExecutorCompletionService<>(executorService);

        for (int i = 1; i <= 5; i++) {
            completionService.submit(() -> calculatePalindromicPrimes(1, 1_000_001));
        }

        // ander werk

        for (int i = 1; i <= 5; i++) {
            try {
                final Future<List<Long>> futureNextCompletedTask = completionService.take();
                System.out.println("Resultaat beschikbaar");
                System.out.println(futureNextCompletedTask.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }

    private static List<Long> calculatePalindromicPrimes(long lowerLimit, long upperLimit) {
        long start = System.nanoTime();

        final List<Long> primes = LongStream.rangeClosed(lowerLimit, upperLimit)
                .filter(ExecutorServiceDemo::isPrime)
                .filter(l -> (l + "").equals(new StringBuilder(l + "").reverse().toString()))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        System.out.println(Thread.currentThread() + " is done (took " + (System.nanoTime() - start) / 1_000_000 + " ms.)");
        return primes;
    }

    private static void calculatePrimes(long lowerLimit, long upperLimit) {
        long start = System.nanoTime();

        final List<Long> primes = LongStream.rangeClosed(lowerLimit, upperLimit)
                .filter(ExecutorServiceDemo::isPrime)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        //System.out.println(primes);

        System.out.println(Thread.currentThread() + " is done (took " + (System.nanoTime() - start) / 1_000_000 + " ms.)");
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
