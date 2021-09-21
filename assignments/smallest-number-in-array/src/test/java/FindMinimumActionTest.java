import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class FindMinimumActionTest {
    @Test
    void name() {
        // Arrange
        ForkJoinPool pool = new ForkJoinPool();
        var findMinimumAction = new FindMinimumAction(IntStream.rangeClosed(1, 10).toArray());

        // Act
        long start = System.nanoTime();
        pool.execute(findMinimumAction);

        pool.shutdown();

        // Assert
        findMinimumAction.join();
        long end = System.nanoTime();
        System.out.format("Test took %dms.", (end - start) / 1_000_000);
    }
}