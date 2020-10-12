public class SlowNumbers {
    public static void main(String[] args) throws InterruptedException {
        Thread slowNumbers = new Thread(() -> {
            for (int number = 0; number < 10; number++) {
                System.out.println(number);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted; discarding work...");
                    break;
                }
            }
        });

        slowNumbers.start();
        Thread.sleep(3000);

        slowNumbers.interrupt();
        Thread.sleep(1000);
    }
}
