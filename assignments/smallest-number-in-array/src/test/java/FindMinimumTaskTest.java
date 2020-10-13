import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class FindMinimumTaskTest {

    @ParameterizedTest
    @ValueSource(ints = {10, 10_000_000, 100_000_000, 150_000_000})
    void testWithForkJoinPool(int upperLimit) {
        long start = System.nanoTime();
        // Arrange
        ForkJoinPool pool = new ForkJoinPool();
        var findMinimumTask = new FindMinimumTask(IntStream.rangeClosed(1, upperLimit).toArray());

        // Act
        pool.execute(findMinimumTask);

        pool.shutdown();

        // Assert
        assertThat(findMinimumTask.join()).isEqualTo(1);
        long end = System.nanoTime();
        System.out.format("Test took %dms.", (end - start) / 1_000_000);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 10_000_000, 100_000_000, 150_000_000})
    void testWithSynchronousImplementation(int upperLimit) {
        // Arrange
        long start = System.nanoTime();
        var findMinimumAction = new FindMinimumAction(IntStream.rangeClosed(1, upperLimit).toArray());

        // Act
        // Assert
        assertThat(findMinimumAction.computeMinimum()).isEqualTo(1);
        long end = System.nanoTime();
        System.out.format("Test took %dms.", (end - start) / 1_000_000);

    }
}