import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Livelock {
    private final Lock firstLock = new ReentrantLock();
    private final Lock secondLock = new ReentrantLock();

    public static void main(String[] args) {
        var livelock = new Livelock();

        Thread hanno = new Thread(livelock::hannoMove);
        Thread bas = new Thread(livelock::basMove);

        hanno.start();
        bas.start();
    }

    private void hannoMove() {
        while (true) {
            if (firstLock.tryLock()) {
                System.out.println("Hanno has acquired the first lock!");
                sleep(150);

                if (secondLock.tryLock()) {
                    System.out.println("Hanno has acquired the second lock!");
                    System.out.println("-- Hanno walks down the alleyway. -- \\o/");

                    System.out.println("Releasing the second lock.");
                    secondLock.unlock();
                    return;
                } else {
                    System.out.println("Hanno has failed to acquire the second lock.");
                }

                System.out.println("Releasing the first lock.");
                firstLock.unlock();
            }
        }
    }

    private void basMove() {
        while (true) {
            if (secondLock.tryLock()) {
                System.out.println("Bas has acquired the second lock!");
                sleep(150);

                if (firstLock.tryLock()) {
                    System.out.println("Bas has acquired the first lock!");
                    System.out.println("-- Bas walks down the alleyway. -- \\o/");

                    System.out.println("Releasing the first lock.");
                    firstLock.unlock();
                    return;
                } else {
                    System.out.println("Bas has failed to acquire the first lock.");
                }

                System.out.println("Releasing the second lock.");
                secondLock.unlock();
            }
        }
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
