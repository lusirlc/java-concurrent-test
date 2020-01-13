package safe;

/**
 * i++不是原子操作, 所以线程不安全
 */
public class UnsafeThread {
    static int num =0;

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                num++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
        Thread.sleep(100);
        System.out.println(num);//结果不会是20000,而是小与20000
    }
}
