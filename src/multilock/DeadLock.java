package multilock;

import tools.SleepTools;

/**
 * 死锁：当两个线程互相持有对方的锁而导致线程处于阻塞状态从而导致无法向下运行任务
 * 解决方案：两个线程加锁顺序改成一样
 */
public class DeadLock {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName()+":获取到a锁，开始执行任务...");
                SleepTools.second(1);
                System.out.println(Thread.currentThread().getName()+":执行完a任务，尝试获取b锁");
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName()+":获取到b锁，开始执行任务...");
                    SleepTools.second(1);
                    System.out.println(Thread.currentThread().getName()+":执行完b任务，所有任务完成");
                }
            }
        },"a").start();

        new Thread(() -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName()+":获取到b锁，开始执行任务...");
                SleepTools.second(1);
                System.out.println(Thread.currentThread().getName()+":执行完b任务，尝试获取a锁");
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName()+":获取到a锁，开始执行任务...");
                    SleepTools.second(1);
                    System.out.println(Thread.currentThread().getName()+":执行完a任务，所有任务完成");
                }
            }
        },"b").start();
    }
}
