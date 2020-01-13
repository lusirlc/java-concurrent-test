package pkupk;

import tools.SleepTools;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport.park()
 * LockSupport.unpark()
 * unpark()调用在park()前面，park()方法则会无效
 */
public class Test1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
//            SleepTools.second(2);
            System.out.println("park...");
            LockSupport.park();
            System.out.println("unpark...");
        }, "t1");
        t1.start();
        SleepTools.second(1);
        LockSupport.unpark(t1);
    }

}
