package lab4.TwoConditions;

import java.util.ArrayList;

public class Main {
    public static ArrayList<Thread> producersThreads = new ArrayList<>();
    public static ArrayList<Thread> consumersThreads = new ArrayList<>();

    public static void main(String[] args) {
        int numberOfProducers = 5;
        int numberOfConsumers = 5;
        int bufferSize = 10;
        //int maxPortionSize = 5;
        ArrayList<Integer> countProduces = new ArrayList<>();
        ArrayList<Integer> countConsumes = new ArrayList<>();

        Buffer buffer = new Buffer(bufferSize);

        // Producers
        for (int i = 0; i < numberOfProducers; i++) {
            Producer producer = new Producer(buffer, 1, i, countProduces);
            producersThreads.add(producer);
            countProduces.add(0);
        }
        Producer producer = new Producer(buffer, 5, numberOfProducers, countProduces);
        producersThreads.add(producer);
        countProduces.add(0);


        // Consumers
        for (int i = 0; i < numberOfConsumers; i++) {
            Consumer consumer = new Consumer(buffer, 1, i, countConsumes);
            consumersThreads.add(consumer);
            countConsumes.add(0);
        }
        Consumer consumer = new Consumer(buffer, 5, numberOfConsumers, countConsumes);
        consumersThreads.add(consumer);
        countConsumes.add(0);

        for (Thread t : producersThreads) {
            t.start();
        }

        for (Thread t : consumersThreads) {
            t.start();
        }

        try {
            for (Thread t : producersThreads) {
                t.join();
            }

            for (Thread t : consumersThreads) {
                t.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
