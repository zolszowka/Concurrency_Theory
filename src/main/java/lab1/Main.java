package lab1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numberOfThreadsI = 10;
        int numberOfThreadsD = 10;
        int numberOfIterations = 1000;

        ArrayList<Thread> threads = new ArrayList();
        for (int i = 0; i < numberOfThreadsI; i++) {
            Thread threadIncrement = new Increment(numberOfIterations);
            threads.add(threadIncrement);
        }

        for (int i = 0; i < numberOfThreadsD; i++) {
            Thread threadDecrement = new Decrement(numberOfIterations);
            threads.add(threadDecrement);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Counter: " + Counter.getCount());
    }
}
