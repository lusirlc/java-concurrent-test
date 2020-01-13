package multilock;

import tools.SleepTools;

/**
 * 活锁：当两个线程改变对方结束的条件从而导致两个线程一直运行下去
 * 解决方法：增加随机随便时间任务完成时间错开
 */
public class LiveLock {
    static volatile int count = 10;
    static final Object lock = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            while (count > 0) {
                SleepTools.ms(200);
                count--;
                System.out.println(Thread.currentThread().getName() + ":" + count);
            }
        }, "t1").start();

        new Thread(() -> {
            while (count < 20) {
                SleepTools.ms(200);
                count++;
                System.out.println(Thread.currentThread().getName() + ":" + count);
            }
        }, "t2").start();
    }

}
