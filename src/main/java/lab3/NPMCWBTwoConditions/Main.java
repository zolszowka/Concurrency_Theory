package lab3.NPMCWBTwoConditions;

public class Main {
    public static void main(String[] args) {
        int numberOfIterations = 1000;

        Buffer buffer = new Buffer(5);
        Producer producer1 = new Producer(buffer, numberOfIterations, 1);
        Producer producer2 = new Producer(buffer, numberOfIterations, 2);
        Producer producer3 = new Producer(buffer, numberOfIterations, 3);

        Consumer consumer1 = new Consumer(buffer, numberOfIterations, 1);
        Consumer consumer2 = new Consumer(buffer, numberOfIterations, 2);
        Consumer consumer3 = new Consumer(buffer, numberOfIterations, 3);

        producer1.start();
        consumer1.start();
        producer2.start();
        consumer2.start();
        producer3.start();
        consumer3.start();

        try {
            producer1.join();
            consumer1.join();
            producer2.join();
            consumer2.join();
            producer3.join();
            consumer3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
