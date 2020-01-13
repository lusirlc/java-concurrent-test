package base;

import tools.SleepTools;

/**
 * java线程的6中状态测试
 */
public class ThreadStateTest {
    public static void main (String[] args) {
        Thread t1 = new Thread();// New
        Thread t2 = new Thread(() -> {
            while (true) {
                // Runnable
            }
        });
        t2.start();
        Thread t3 = new Thread(() -> {
            synchronized (ThreadStateTest.class) {
                SleepTools.second(20);// Timed Waiting
            }
        });
        t3.start();
        Thread t4 = new Thread(() -> {
                try {
                    t3.join();// Waiting
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });
        t4.start();
        Thread t5 = new Thread(() -> {
            synchronized (ThreadStateTest.class) {
                // Blocked
            }
        });
        t5.start();
        Thread t6 = new Thread(() -> {
            // Terminated
        });
        t6.start();

        SleepTools.second(1);
        System.out.println("t1.getState() = " + t1.getState());
        System.out.println("t2.getState() = " + t2.getState());
        System.out.println("t3.getState() = " + t3.getState());
        System.out.println("t4.getState() = " + t4.getState());
        System.out.println("t5.getState() = " + t5.getState());
        System.out.println("t6.getState() = " + t6.getState());

    }
}
