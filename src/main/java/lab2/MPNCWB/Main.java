package lab2.MPNCWB;

public class Main {
    public static void main(String[] args) {
        int bufferSize = 5;

        Buffer buffer = new Buffer(bufferSize);
        Producer producer1 = new Producer(buffer, 1);
        Producer producer2 = new Producer(buffer, 2);
        Producer producer3 = new Producer(buffer, 3);

        Consumer consumer1 = new Consumer(buffer, 1);
        //Consumer consumer2 = new Consumer(buffer, 2);
        //Consumer consumer3 = new Consumer(buffer, 3);

        producer1.start();
        consumer1.start();
        producer2.start();
        //consumer2.start();
        producer3.start();
        //consumer3.start();

        try {
            producer1.join();
            consumer1.join();
            producer2.join();
            //consumer2.join();
            producer3.join();
            //consumer3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
