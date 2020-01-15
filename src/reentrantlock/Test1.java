package reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入性
 */
public class Test1 {
    static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println("I am main");
            m1();
        } catch (Exception e) {
            lock.unlock();
        }
    }

    public static void m1() {
        lock.lock();
        try {
            System.out.println("I am m1");
            m2();
        } catch (Exception e) {
            lock.unlock();
        }
    }

    public static void m2() {
        lock.lock();
        try {
            System.out.println("I am m2");
        } catch (Exception e) {
            lock.unlock();
        }
    }
}
