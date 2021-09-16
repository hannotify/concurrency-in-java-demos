import java.util.concurrent.*;

public class TextBuilder {
    public static void main(String[] args) {
        CompletableFuture<String> textBuilder = CompletableFuture
                .supplyAsync((() -> "Beste Hanno,"), Executors.newFixedThreadPool(3))
                .thenApplyAsync(aanhef -> String.format("%s%n%nHoe gaat het met je vandaag?", aanhef))
                .thenApplyAsync(brief -> String.format("%s%n%nGroeten, Hanno", brief))
                .thenApplyAsync(brief -> String.format("Leusden, 13 oktober 2020%n%n%s", brief));

        String result = null;
        try {
            result = textBuilder.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
