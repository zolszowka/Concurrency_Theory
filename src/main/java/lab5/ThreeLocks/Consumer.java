package lab5.ThreeLocks;

import java.util.ArrayList;

public class Consumer extends Thread {
    Buffer buffer;
    int maxPortionSize;
    int id;
    ArrayList<Integer> countConsumes;

    public Consumer(Buffer buffer, int n, int id, ArrayList<Integer> countConsumes) {
        this.buffer = buffer;
        this.maxPortionSize = n;
        this.id = id;
        this.countConsumes = countConsumes;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min + 1);
    }

    @Override
    public void run() {
        while (true) {
            int portion = getRandomNumber(0, maxPortionSize);
            try {
                buffer.consume(portion);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //System.out.println("Consumer " + id + " consume" + portion);
            this.countConsumes.set(id, this.countConsumes.get(id) + 1);
            System.out.println("Number of consumes: " + this.countConsumes);
        }
    }
}
