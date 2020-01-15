package reentrantlock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁超时：主动防止死等
 */
public class Test3 {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":尝试获取锁");
            try {
                // 也支持打断
                if (!lock.tryLock(1, TimeUnit.SECONDS)) {
                    System.out.println(Thread.currentThread().getName() + ":没有获取到锁，返回");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + ":没有获取到锁，返回");
                return;
            }
            try {
                System.out.println(Thread.currentThread().getName() + ":获取到锁，执行任务");
            } finally {
                lock.unlock();
            }
        }, "t1");
        lock.lock();
        t1.start();
//        SleepTools.ms(200);
//        lock.unlock();
//        t1.interrupt();
    }
}
