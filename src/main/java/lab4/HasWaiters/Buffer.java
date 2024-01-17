package lab4.HasWaiters;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final int bufferSize;
    private int counter = 0;
    ReentrantLock lock = new ReentrantLock();
    private final Condition firstProducer = lock.newCondition();
    private final Condition restProducers = lock.newCondition();
    private final Condition firstConsumer = lock.newCondition();
    private final Condition restConsumers = lock.newCondition();


    public Buffer(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public int getCounter() {
        return counter;
    }

    public void produce(int portion) throws InterruptedException {
        lock.lock();
        try {
            while (lock.hasWaiters(firstProducer)) {
                restProducers.await();
            }
            while (this.counter + portion > this.bufferSize) {
                firstProducer.await();
            }
            this.counter += portion;
            restProducers.signal();
            firstConsumer.signal();
        } finally {
            lock.unlock();
        }
    }

    public void consume(int portion) throws InterruptedException {
        lock.lock();
        try {
            while (lock.hasWaiters(firstConsumer)) {
                restConsumers.await();
            }
            while (this.counter - portion < 0) {
                firstConsumer.await();
            }
            this.counter -= portion;
            restConsumers.signal();
            firstProducer.signal();
        } finally {
            lock.unlock();
        }
    }
}
