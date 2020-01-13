package safe;

/**
 * @see Room
 * @see RoomTest
 * synchronzied关键字保证线程安全（面向对象思想改进，将共享资源保护起来。@see Room.class RoomTest.class)
 */
public class SafeThread {
    static int num =0;
    static Object lock = new Object();
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 10000; i++) {
                    num++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
        Thread.sleep(100);
        System.out.println(num);//结果是20000
    }
}
