package sequence;

/**
 * 使用wait,notify机制来控制线程运行次序
 */
public class Test1 {
    static Object lock = new Object();
    static boolean flag;
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                while (!flag) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(1);
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println(2);
                flag = true;
                lock.notify();
            }
        }).start();
    }
}
