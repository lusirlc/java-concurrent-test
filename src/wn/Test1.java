package wn;

import tools.SleepTools;

/**
 * 线程的等待唤醒机制
 * wait()
 * notify()
 * notifyAll()
 */
public class Test1 {
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (Test1.class) {
                System.out.println(Thread.currentThread().getName()+":"+"执行...");
                try {
                    Test1.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":"+"执行其他...");
            }

        },"t1").start();

        new Thread(()->{
            synchronized (Test1.class) {
                System.out.println(Thread.currentThread().getName()+":"+"执行...");
                try {
                    Test1.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":"+"执行其他...");
            }

        },"t2").start();

        SleepTools.second(1);
        System.out.println(Thread.currentThread().getName()+":唤醒其他线程");
        synchronized (Test1.class) {
//            Test1.class.notify();//随机唤醒一个
        Test1.class.notifyAll();//全部唤醒
        }
    }
}
