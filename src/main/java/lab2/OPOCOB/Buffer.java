package lab2.OPOCOB;

public class Buffer {
    int counter;

    public Buffer(int counter) {
        this.counter = counter;
    }

    public synchronized void produce() {
        while (counter == 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        counter++;
        notify();
    }

    public synchronized void consume() {
        while (counter == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        counter--;
        notify();

    }
}
