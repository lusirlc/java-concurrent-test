package sequence;

import java.util.concurrent.locks.LockSupport;

/**
 * 使用LockSupport来实现线程顺序执行
 */
public class Test3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            System.out.println(1);
        }, "t1");
        t1.start();
        new Thread(() -> {
            System.out.println(2);
            LockSupport.unpark(t1);
        }).start();
    }
}
