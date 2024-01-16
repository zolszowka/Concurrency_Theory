package lab1;

public class Increment extends Thread {
    private int numberOfIterations;

    public Increment(int n) {
        this.numberOfIterations = n;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.numberOfIterations; i++) {
            Counter.increment();
        }
    }

}
