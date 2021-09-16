import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class BestPriceFinder {
    public static void main(String[] args) {
        CompletableFuture<Integer> priceAmazon = CompletableFuture.supplyAsync(supplyPrice("Amazon", 1, 8));
        CompletableFuture<Integer> priceCoolblue = CompletableFuture.supplyAsync(supplyPrice("Coolblue", 3, 11));
        CompletableFuture<Integer> priceVanDenBorre = CompletableFuture.supplyAsync(supplyPrice("Vandenborre" , 2, 13));

        try {
            Integer bestPriceOverall = priceAmazon.thenCombine(priceVanDenBorre, Integer::min).thenCombine(priceCoolblue, Integer::min).get();
            System.out.printf("Best price is: %d", bestPriceOverall);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static Supplier<Integer> supplyPrice(String store, int min, int max) {
        return () -> {
            int randomPrice = ThreadLocalRandom.current().nextInt(min, max + 1);

            System.out.printf("Retrieving price at %s...%n", store);

            try {
                TimeUnit.SECONDS.sleep(store.length());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.printf("Price at %s is %d.%n", store, randomPrice);
            return randomPrice;
        };
    }
}
