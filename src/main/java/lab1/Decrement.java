package lab1;

public class Decrement extends Thread {
    private int numberOfIterations;

    public Decrement(int n) {
        this.numberOfIterations = n;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.numberOfIterations; i++) {
            Counter.decrement();
        }
    }

}
