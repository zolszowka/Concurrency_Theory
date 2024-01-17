package lab5.ThreeLocks;

import java.util.ArrayList;

public class Producer extends Thread {
    Buffer buffer;
    int maxPortionSize;
    int id;
    public ArrayList<Integer> countProduces;

    public Producer(Buffer buffer, int n, int id, ArrayList<Integer> countProduces) {
        this.buffer = buffer;
        this.maxPortionSize = n;
        this.id = id;
        this.countProduces = countProduces;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min + 1);
    }

    @Override
    public void run() {
        while (true) {
            int portion = getRandomNumber(0, maxPortionSize);
            try {
                buffer.produce(portion);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //System.out.println("Producer " + id + " produce");
            this.countProduces.set(id, this.countProduces.get(id) + 1);
            System.out.println("Number of produces: " + this.countProduces);
        }
    }
}
