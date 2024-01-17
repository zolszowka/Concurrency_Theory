package lab5.ThreeLocks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final int bufferSize;
    private int counter = 0;
    ReentrantLock producerLock = new ReentrantLock();
    ReentrantLock consumerLock = new ReentrantLock();
    ReentrantLock insideLock = new ReentrantLock();
    private final Condition commonCond = insideLock.newCondition();


    public Buffer(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public int getCounter() {
        return counter;
    }

    public void produce(int portion) throws InterruptedException {
        producerLock.lock();
        try {
            insideLock.lock();
            while (this.counter + portion > this.bufferSize) {
                commonCond.await();
            }
            this.counter += portion;
            commonCond.signal();
            insideLock.unlock();
        } finally {
            producerLock.unlock();
        }
    }

    public void consume(int portion) throws InterruptedException {
        consumerLock.lock();
        try {
            insideLock.lock();
            while (this.counter - portion < 0) {
                commonCond.await();
            }
            this.counter -= portion;
            commonCond.signal();
            insideLock.unlock();
        } finally {
            consumerLock.unlock();
        }
    }
}
