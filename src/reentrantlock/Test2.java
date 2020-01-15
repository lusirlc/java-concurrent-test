package reentrantlock;

import tools.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可打断性：被动防止死等（意义也是为了减少死锁的发生）
 */
public class Test2 {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + ":尝试获取锁");
                // 如果没有线程竞争，可以获取锁
                // 如果有其他线程竞争，进入等待队列，在等待期间可以被其他线程打断从而退出等待队列
                lock.lockInterruptibly();
                // lock方法无法被打断，和synchronized关键字一样
//                lock.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + ":被打断，退出等待队列");
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
        SleepTools.second(1);
        t1.interrupt();
    }
}
