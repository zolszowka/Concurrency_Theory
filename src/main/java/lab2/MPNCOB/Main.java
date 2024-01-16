package lab2.MPNCOB;

public class Main {
    public static void main(String[] args) {
        int numberOfIterations = 1000;

        Buffer buffer = new Buffer(0);
        Producer producer1 = new Producer(buffer, numberOfIterations, 1);
        Consumer consumer1 = new Consumer(buffer, numberOfIterations, 1);
        Consumer consumer2 = new Consumer(buffer, numberOfIterations, 2);

        producer1.start();
        consumer1.start();
        consumer2.start();

        try {
            producer1.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
