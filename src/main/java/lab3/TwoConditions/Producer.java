package lab3.TwoConditions;

import java.util.ArrayList;

public class Producer extends Thread {
    int portion;
    Buffer buffer;
    int id;
    public ArrayList<Integer> countProduces;

    public Producer(Buffer buffer, int n, int id, ArrayList<Integer> countProduces) {
        this.buffer = buffer;
        this.portion = n;
        this.id = id;
        this.countProduces = countProduces;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public void run() {
        while (true) {
            //int portion = getRandomNumber(0, maxPortionSize);
            buffer.produce(portion);
            //System.out.println("Producer " + id + " produce");
            this.countProduces.set(id, this.countProduces.get(id) + 1);
            System.out.println("Number of produces: " + this.countProduces);
        }
    }
}
