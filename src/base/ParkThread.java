package base;

import tools.SleepTools;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport.park()方法：类似wait()方法
 * LockSupport.unpark(Thread thread)方法：指定唤醒某个线程，与notify()随机唤醒和notifyAll()全部唤醒不同
 */
public class ParkThread {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            System.out.println("park...");
            LockSupport.park();
            System.out.println("unpark...");
            System.out.println("打断状态："+Thread.currentThread().isInterrupted());
            LockSupport.park();// park只有在线程没有打断标记的情况下起作用
            System.out.println("unpark...");
        });
        t.start();
        SleepTools.second(1);
        t.interrupt();
    }
}
