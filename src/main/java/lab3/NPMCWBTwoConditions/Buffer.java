package lab3.NPMCWBTwoConditions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final int bufferSize;
    private int counter = 0;

    ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();


    public Buffer(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public int getCounter() {
        return counter;
    }

    public void produce() {
        lock.lock();
        try {
            while (this.counter >= this.bufferSize) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.counter++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            while (this.counter <= 0) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.counter--;
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}
