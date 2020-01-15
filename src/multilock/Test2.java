package multilock;

import tools.SleepTools;

/**
 * 5个哲学家就餐问题（死锁）
 */
public class Test2 {

    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");
        new Philosopher(c1,c2,"苏格拉底").start();
        new Philosopher(c2,c3,"柏拉图").start();
        new Philosopher(c3,c4,"亚里士多德").start();
        new Philosopher(c4,c5,"赫拉克利特").start();
        new Philosopher(c5,c1,"阿基米德").start();
    }
}

/**
 * 哲学家类
 */
class Philosopher extends Thread{
    Chopstick left;
    Chopstick right;

    public Philosopher(Chopstick left, Chopstick right, String name) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            // 尝试获取左筷子
            synchronized (left) {
                // 尝试获取右筷子
                synchronized (right) {
                    eat();
                }
            }
        }
    }
    private void eat() {
        System.out.println(Thread.currentThread().getName()+":eat...");
        SleepTools.second(1);
    }
}

/**
 * 筷子类
 */
class Chopstick{
    String name;

    public Chopstick(String name) {
        this.name = name;
    }
}