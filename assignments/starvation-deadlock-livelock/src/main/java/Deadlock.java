public class Deadlock {
    private final Object firstLock = new Object();
    private final Object secondLock = new Object();

    public static void main(String[] args) {
        var deadlock = new Deadlock();

        Thread hanno = new Thread(deadlock::hannoMove);
        Thread bas = new Thread(deadlock::basMove);

        hanno.start();
        bas.start();
    }

    private void hannoMove() {
        synchronized (firstLock) {
            System.out.println("Hanno has acquired the first lock!");

            synchronized (secondLock) {
                System.out.println("Hanno has acquired the second lock!");
                System.out.println("-- Hanno walks down the alleyway. -- \\o/");
            }
            System.out.println("Hanno has released the second lock!");
        }
        System.out.println("Hanno has released the first lock!");
    }

    private void basMove() {
        synchronized (secondLock) {
            System.out.println("Bas has acquired the second lock!");

            synchronized (firstLock) {
                System.out.println("Bas has acquired the first lock!");
                System.out.println("-- Bas walks down the alleyway. -- \\o/");
            }
            System.out.println("Bas has released the first lock!");
        }
        System.out.println("Bas has released the second lock!");
    }

//    private void basMove2() {
//        synchronized (firstLock) {
//            System.out.println("Bas has acquired the first lock!");
//
//            synchronized (secondLock) {
//                System.out.println("Bas has acquired the second lock!");
//                System.out.println("-- Bas walks down the alleyway. -- \\o/");
//            }
//            System.out.println("Bas has released the second lock!");
//        }
//        System.out.println("Bas has released the first lock!");
//    }
}
