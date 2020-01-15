package sequence;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock来控制线程执行顺序
 */
public class Test2 {
    static Lock lock = new ReentrantLock();
    static Condition c1 = lock.newCondition();
    static boolean flag;
    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            while (!flag) {
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                System.out.println(1);
            }finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(2);
                flag = true;
                c1.signal();
            }finally {
                lock.unlock();
            }
        }).start();
    }
}
