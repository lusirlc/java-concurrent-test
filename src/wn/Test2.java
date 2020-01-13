package wn;

import tools.SleepTools;

/**
 * wait/notify使用
 */
public class Test2 {
    private static boolean hasCigarette;
    private static boolean hasTakeout;
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (Test2.class) {
                while (true) {
                    if (!hasCigarette) {
                        System.out.println(Thread.currentThread().getName() + ":有烟吗");
                        try {
                            System.out.println(Thread.currentThread().getName() + ":没烟干不了活");
                            Test2.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else {
                        System.out.println(Thread.currentThread().getName() + ":可以干活了");
                        break;
                    }
                }
            }

        },"小王").start();
        new Thread(() -> {
            synchronized (Test2.class) {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + ":有外卖吗");
                    if (!hasTakeout) {
                        try {
                            System.out.println(Thread.currentThread().getName() + ":没外卖干不了活");
                            Test2.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName()+":可以干活了");
                        break;
                    }
                }
            }

        },"小美").start();
        new Thread(() -> {
            synchronized (Test2.class) {
                SleepTools.second(1);
                System.out.println(Thread.currentThread().getName()+":外卖来了");
                hasTakeout = true;
                Test2.class.notifyAll();
            }

        },"外卖员").start();
        new Thread(() -> {
            synchronized (Test2.class) {
                SleepTools.second(2);
                System.out.println(Thread.currentThread().getName()+":烟来了");
                hasCigarette = true;
                Test2.class.notifyAll();
            }

        },"送烟员").start();
    }
}
