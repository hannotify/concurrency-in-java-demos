import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class FindMinimumTask extends RecursiveTask<Integer> {
    private int[] data;

    public FindMinimumTask(int[] data) {
        this.data = data;
    }

    Integer computeMinimum() {
        int minimum = Integer.MAX_VALUE;

        for (int i : data) {
            if (i < minimum) {
                minimum = i;
            }
        }

        //System.out.println("Found minimum: " + minimum);
        return minimum;
    }

    @Override
    protected Integer compute() {
        if (data.length < 5) {
            // BASE case.
            return computeMinimum();
        } else {
            // FORK subtasks.
            FindMinimumTask firstHalf = new FindMinimumTask(Arrays.copyOfRange(data, 0, data.length/2));
            FindMinimumTask secondHalf = new FindMinimumTask(Arrays.copyOfRange(data, data.length/2, data.length));

            firstHalf.fork();
            secondHalf.fork();

            return Integer.min(firstHalf.join(), secondHalf.join());
        }
    }
}
