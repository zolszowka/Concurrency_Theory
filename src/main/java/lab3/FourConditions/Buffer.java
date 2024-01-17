package lab3.FourConditions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final int bufferSize;
    private int counter = 0;
    boolean hasFirstProducer = false;
    boolean hasFirstConsumer = false;
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
            while (hasFirstProducer) {
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
            while (hasFirstConsumer) {
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
