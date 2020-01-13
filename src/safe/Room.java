package safe;

public class Room {
    private int count = 0;

    public void increment() {
        synchronized (this) {
            count++;
        }
    }
    public void decrement() {
        synchronized (this) {
            count--;
        }
    }

    public int getCount() {
        synchronized (this) {
            return count;
        }
    }
}
