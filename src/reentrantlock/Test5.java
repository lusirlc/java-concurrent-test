package reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 * 公平锁并发效率没有非公平锁高
 */
public class Test5 {
    // 设置公平锁模式
    static Lock lock = new ReentrantLock(true);
}
