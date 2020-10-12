class CustomObjectLock {
    private String message = null;
    private final Object lock = new Object();

    public void newMessage(String x) {
        synchronized (lock) {
            message = x;
        }
    }

    public String getMessage() {
        synchronized (lock) {
            String temp = message;
            message = null;
            return temp;
        }
    }
}