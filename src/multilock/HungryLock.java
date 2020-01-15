package multilock;

/**
 * 饥饿：某个线程长期获得不到CPU资源，无法执行任务
 */
public class HungryLock {
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
        new Philosopher(c1,c5,"阿基米德").start();
    }
}
