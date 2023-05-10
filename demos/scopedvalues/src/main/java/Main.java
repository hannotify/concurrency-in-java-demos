import com.infosupport.concurjava.scopedvalues.sync.AtomicReferenceSimpleDateFormat;
import com.infosupport.concurjava.scopedvalues.sync.ScopedValueSimpleDateFormat;
import com.infosupport.concurjava.scopedvalues.sync.ThreadLocalSimpleDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private static final SynchronizedSimpleDateFormat simpleDateFormat = new ScopedValueSimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            executorService.execute(new DateFormatter());
            executorService.execute(new DateFormatter());
        }
    }

    static class DateFormatter implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread run execution started for " + Thread.currentThread().getName());
                System.out.println("Date formatter pattern is  " + simpleDateFormat.toPattern());

                try {
                    System.out.println("Formatted date is " + simpleDateFormat.parse("2013-05-24 06:02:20"));
                    counter.incrementAndGet();

                    System.out.println("Counter: " + counter);
                } catch (ParseException pe) {
                    pe.printStackTrace();

                }

                System.out.println("=========================================================");
            }
        }
    }
}