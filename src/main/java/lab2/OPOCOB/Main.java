package lab2.OPOCOB;

public class Main {
    public static void main(String[] args) {
        int numberOfIterations = 1000;
        Buffer buffer = new Buffer(0);
        Producer producer1 = new Producer(buffer, numberOfIterations, 1);
        Consumer consumer1 = new Consumer(buffer, numberOfIterations, 1);

        producer1.start();
        consumer1.start();
    }
}
