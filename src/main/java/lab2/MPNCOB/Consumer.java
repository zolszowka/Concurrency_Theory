package lab2.MPNCOB;

public class Consumer extends Thread {
    int numberOfOperations;
    Buffer buffer;
    int id;

    public Consumer(Buffer buffer, int n, int id) {
        this.buffer = buffer;
        this.numberOfOperations = n;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfOperations; i++) {
            buffer.consume(id);
        }
    }
}
