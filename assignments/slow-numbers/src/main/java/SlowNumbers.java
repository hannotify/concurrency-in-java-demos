public class SlowNumbers {
    public static void main(String[] args) throws InterruptedException {
        Thread slowNumbers = new Thread(() -> { // 3.
            for (int number = 0; number < 10; number++) {

                System.out.println(number); // 1.

                try {
                    Thread.sleep(1000); // 2.
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted; discarding work...");
                    break; // 7.
                }
            }
        });

        slowNumbers.start(); // 4.
        Thread.sleep(3000); // 4.

        slowNumbers.interrupt(); // 5.
        Thread.sleep(1000); // 6.
    }
}
