package multilock;

import tools.SleepTools;

/**
 * 上多把锁
 */
public class Test1 {
    public static void main(String[] args) {
        BigRoom bigRoom = new BigRoom();
        new Thread(() -> {
            bigRoom.sleep();
        },"小王").start();
        new Thread(() -> {
            bigRoom.study();
        },"小美").start();
    }
}

class BigRoom{
    private final Object studyRoom = new Object();
    private final Object bedRoom = new Object();

    public void sleep() {
        synchronized (bedRoom) {
            System.out.println(Thread.currentThread().getName()+":睡觉zzZ");
            SleepTools.second(2);
        }
    }

    public void study() {
        synchronized (studyRoom) {
            System.out.println(Thread.currentThread().getName()+":学习...");
            SleepTools.second(1);
        }
    }
}