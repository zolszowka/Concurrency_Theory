package lab1;

public class Counter {
    private static int counter = 0;

    public static synchronized void increment() {
        counter++;
    }

    public static synchronized void decrement() {
        counter--;
    }

    public static int getCount() {
        return counter;
    }
}
