package cas;

import tools.SleepTools;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 利用CAS机制实现加锁:
 * 当一个线程加锁成功后，另一个线程会一直尝试加锁（还会消耗CPU资源）
 */
public class LockCAS {
    // 0为未加锁状态，1为加锁状态
    private static AtomicInteger state = new AtomicInteger(0);
    public static void main(String[] args) {
        new Thread(() -> {
            lock();
            System.out.println(1);
            SleepTools.ms(500);
            unlock();
        },"t1").start();
        new Thread(() -> {
            lock();
            System.out.println(2);
            SleepTools.ms(500);
            unlock();
        },"t2").start();
    }

    private static void lock() {
        while (true) {
//            System.out.println(Thread.currentThread().getName()+":尝试加锁");
            // 加锁成功
            if (state.compareAndSet(0, 1)) {
                System.out.println(Thread.currentThread().getName()+":加锁成功");
                break;
            }
        }
    }
    private static void unlock() {
        state.set(0);
        System.out.println(Thread.currentThread().getName()+":解锁");
    }
}
