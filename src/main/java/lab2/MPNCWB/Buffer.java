package lab2.MPNCWB;

public class Buffer {
    private int bufferSize;
    private int counter = 0;

    public Buffer(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public int getCounter() {
        return counter;
    }

    public void produce() {
        synchronized (this) {
            while (this.counter >= this.bufferSize) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.counter++;
            this.notify();
        }
    }

    public void consume() {
        synchronized (this) {
            while (this.counter <= 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.counter--;
            this.notify();
        }
    }
}
