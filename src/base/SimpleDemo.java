package base;

import tools.SleepTools;

/**
 * 合理分工泡茶
 */
public class SimpleDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + "洗水壶");
            SleepTools.second(1);
            System.out.println(Thread.currentThread().getName() + ":" + "烧开水");
            SleepTools.second(3);
        }, "老王");
        t1.start();
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + "洗茶壶");
            SleepTools.second(1);
            System.out.println(Thread.currentThread().getName() + ":" + "洗茶杯");
            SleepTools.second(1);
            System.out.println(Thread.currentThread().getName() + ":" + "拿茶叶");
            SleepTools.second(1);
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + "泡茶喝");
        }, "小王");
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费" + (end - start) + "ms");
    }
}
