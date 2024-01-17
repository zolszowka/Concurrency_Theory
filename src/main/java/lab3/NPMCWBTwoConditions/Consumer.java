package lab3.NPMCWBTwoConditions;

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
        while (true) {
            buffer.consume();
            System.out.println("Consumer " + id + " consume");
        }
    }
}
