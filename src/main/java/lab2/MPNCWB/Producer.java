package lab2.MPNCWB;

public class Producer extends Thread {
    Buffer buffer;
    int id;

    public Producer(Buffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            buffer.produce();
            System.out.println("Producer " + id + " produce");
        }
    }
}
