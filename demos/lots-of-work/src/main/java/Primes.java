import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class Primes {
    public static void main(String[] args) {
        calculatePrimes(System.nanoTime(), 1, 30_000_001);
    }

    public static void calculatePrimes(long start, long lowerLimit, long upperLimit) {
        final List<Long> primes = LongStream.rangeClosed(lowerLimit, upperLimit)
                .filter(Primes::isPrime)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        System.out.println(primes);

        System.out.println((System.nanoTime() - start) / 1_000_000 + "ms / " + Thread.currentThread() + " is done.");
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
