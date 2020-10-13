import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class BestPriceFinder {
    public static void main(String[] args) {
        CompletableFuture<Integer> priceAmazon = CompletableFuture.supplyAsync(supplyPrice(1, 8));
        //CompletableFuture<Integer> priceCoolblue = CompletableFuture.supplyAsync(supplyPrice(3, 11));
        CompletableFuture<Integer> priceVanDenBorre = CompletableFuture.supplyAsync(supplyPrice(6, 13));

        try {
            Integer bestPriceOverall = priceAmazon.thenCombine(priceVanDenBorre, Integer::min).get();
            System.out.printf("Best price is: %d", bestPriceOverall);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static Supplier<Integer> supplyPrice(int min, int max) {
        return () -> {
            int randomPrice = new Random().nextInt(max + 1) + min;

            System.out.printf("%s sleeps %d seconds%n", Thread.currentThread().getName(), randomPrice);

            try {
                TimeUnit.SECONDS.sleep(randomPrice);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return randomPrice;
        };
    }
}
