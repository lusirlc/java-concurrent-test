package safe;

public class RoomTest {
    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.increment();
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.decrement();
            }
        });
        t2.start();
        t1.join();
        t2.join();
        System.out.println("room.getCount() = " + room.getCount());
    }
}
