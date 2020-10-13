import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(8);

        DirectoryProcessor cloudStorage = new DirectoryProcessor(
                new File("/Users/hanno/cloud-storage"),
                "gif");
        DirectoryProcessor development = new DirectoryProcessor(
                new File("/Users/hanno/development"),
                "kt");

        pool.execute(cloudStorage);
        pool.execute(development);

        do {
            System.out.format("******************************************%n");
            System.out.format("Main: Parallelism: %d%n", pool.getParallelism());
            System.out.format("Main: Active Threads: %d%n", pool.getActiveThreadCount());
            System.out.format("Main: Task Count: %d%n", pool.getQueuedTaskCount());
            System.out.format("Main: Steal Count: %d%n", pool.getStealCount());
            System.out.format("******************************************%n");

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (!cloudStorage.isDone() && !development.isDone());

        pool.shutdown();

        var resultsCloudStorage = cloudStorage.join();
        System.out.printf("cloud-storage: %d files found.%n", resultsCloudStorage.size());
//        System.out.println(resultsCloudStorage);
        var resultsDev = development.join();
        System.out.printf("development: %d files found.%n", resultsDev.size());
//        System.out.println(resultsDev);
    }
}
