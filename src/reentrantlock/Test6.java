package reentrantlock;

import tools.SleepTools;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 条件变量
 */
public class Test6 {
    private static boolean hasCigarette;
    private static boolean hasTakeout;
    static Lock room = new ReentrantLock();
    static Condition waitCigaretteSet = room.newCondition();
    static Condition waitTakeoutSet = room.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {
            room.lock();
            try {
                while (!hasCigarette) {
                    System.out.println(Thread.currentThread().getName() + ":有烟吗");
                    try {
                        System.out.println(Thread.currentThread().getName() + ":没烟干不了活");
                        waitCigaretteSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println(Thread.currentThread().getName() + ":可以干活了");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                room.unlock();
            }

        }, "小王").start();
        new Thread(() -> {
            room.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ":有外卖吗");
                while (!hasTakeout) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ":没外卖干不了活");
                        waitTakeoutSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ":可以干活了");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                room.unlock();
            }

        }, "小美").start();
        new Thread(() -> {
            room.lock();
            try {
                SleepTools.second(1);
                System.out.println(Thread.currentThread().getName() + ":外卖来了");
                hasTakeout = true;
                waitTakeoutSet.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                room.unlock();
            }

        }, "外卖员").start();
        new Thread(() -> {
            room.lock();
            try {
                SleepTools.second(1);
                System.out.println(Thread.currentThread().getName() + ":烟来了");
                hasCigarette = true;
                waitCigaretteSet.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                room.unlock();
            }


        }, "送烟员").start();
    }
}
