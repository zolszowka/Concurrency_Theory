package lab2.MPNCWB;

public class Consumer extends Thread {
    Buffer buffer;
    int id;

    public Consumer(Buffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            buffer.consume();
            System.out.println("Consumer " + id + " consume");
        }
    }
}
