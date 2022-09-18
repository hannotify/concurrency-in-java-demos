import java.time.DayOfWeek;
import java.time.LocalDate;

public class Sundays {
    public static void main(String[] args) {
        long numberOfDays = numberOfDaysOnFirstOfMonthInPeriod(System.nanoTime(), -2022, 2022, DayOfWeek.SUNDAY);
        System.out.println(numberOfDays);
    }

    public static long numberOfDaysOnFirstOfMonthInPeriod(long start, int from, int to, DayOfWeek day) {
        long numberOfDays = 0;

        for (int year = from; year <= to; year++) {
            for (int month = 1; month <= 12; month++) {
                LocalDate currentDate = LocalDate.of(year, month, 1);

                if (currentDate.getDayOfWeek() == day) {
                    System.out.printf("%s is a %s.%n", currentDate, day);
                    numberOfDays++;
                }
            }
        }

        System.out.println((System.nanoTime() - start) / 1_000_000 + "ms / " + Thread.currentThread() + " is done.");
        return numberOfDays;
    }
}
