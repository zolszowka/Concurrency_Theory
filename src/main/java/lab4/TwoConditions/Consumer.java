package lab4.TwoConditions;

import java.util.ArrayList;

public class Consumer extends Thread {
    int portion;
    Buffer buffer;
    int id;
    ArrayList<Integer> countConsumes;

    public Consumer(Buffer buffer, int n, int id, ArrayList<Integer> countConsumes) {
        this.buffer = buffer;
        this.portion = n;
        this.id = id;
        this.countConsumes = countConsumes;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public void run() {
        while (true) {
            //int portion = getRandomNumber(0, maxPortionSize);
            buffer.consume(portion);
            //System.out.println("Consumer " + id+ " consume");
            this.countConsumes.set(id, this.countConsumes.get(id) + 1);
            System.out.println("Number of consumes: " + this.countConsumes);
        }
    }
}
