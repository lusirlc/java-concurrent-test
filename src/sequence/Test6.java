package sequence;

import java.util.concurrent.locks.LockSupport;

public class Test6 {
    static Thread t1;
    static Thread t2;
    static Thread t3;
    public static void main(String[] args) {
        ParkUnpark parkUnpark = new ParkUnpark(5);
        t1 = new Thread(() -> {
            parkUnpark.print("a",t2);
        });
        t2 = new Thread(() -> {
            parkUnpark.print("b",t3);
        });
        t3 = new Thread(() -> {
            parkUnpark.print("c",t1);
        });
        t1.start();
        t2.start();
        t3.start();
        LockSupport.unpark(t1);
    }
}

class ParkUnpark{
    private int loopNum;

    public ParkUnpark(int loopNum) {
        this.loopNum = loopNum;
    }

    public void print(String printStr, Thread next) {
        for (int i = 0; i < loopNum; i++) {
            LockSupport.park();
            System.out.print(printStr);
            LockSupport.unpark(next);
        }
    }
}