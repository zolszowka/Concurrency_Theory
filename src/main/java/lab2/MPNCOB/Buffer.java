package lab2.MPNCOB;

public class Buffer {
    private int counter;

    public Buffer(int counter) {
        this.counter = counter;
    }

    public synchronized void produce(int id) {
        System.out.println("Producer " + id + " trying to produce");
        while (counter == 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        counter++;
        System.out.println("Producer " + id + " produce" + " Buffer value: " + this.counter);
        notify();
    }

    public int getCounter() {
        return this.counter;
    }

    public synchronized void consume(int id) {
        System.out.println("Consumer " + id + " trying to consume");
        while (counter == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        counter--;
        System.out.println("Consumer " + id + " consume " + " Buffer value: " + this.counter);
        notify();

    }
}
