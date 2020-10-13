import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class FindMinimumAction extends RecursiveAction {
    private int[] data;

    public FindMinimumAction(int[] data) {
        this.data = data;
    }

    int computeMinimum() {
        int minimum = Integer.MAX_VALUE;

        for (int i : data) {
            if (i < minimum) {
                minimum = i;
            }
        }

        System.out.println("Found minimum: " + minimum);
        return minimum;
    }

    @Override
    protected void compute() {
        if (data.length < 5) {
            // BASE case.
            computeMinimum();
        } else {
            // FORK subtasks.
            FindMinimumAction firstHalf = new FindMinimumAction(Arrays.copyOfRange(data, 0, data.length/2));
            FindMinimumAction secondHalf = new FindMinimumAction(Arrays.copyOfRange(data, data.length/2, data.length));

            firstHalf.fork();
            secondHalf.fork();

            firstHalf.join();
            secondHalf.join();
        }
    }
}
