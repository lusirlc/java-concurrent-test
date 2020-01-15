package sequence;

/**
 * 使用wait,notify来实现多个线程的顺序执行
 * 需求：用3个线程循环5次打印abc
 */
public class Test4 {
    public static void main(String[] args) {
        WaitNotify waitNotify = new WaitNotify(1, 5);
        new Thread(() -> {
            waitNotify.print("a",1,2);
        }).start();
        new Thread(() -> {
            waitNotify.print("b",2,3);
        }).start();
        new Thread(() -> {
            waitNotify.print("c",3,1);
        }).start();
    }
}

class WaitNotify{
    private int flag;
    private int loopNum;

    public WaitNotify(int flag, int loopNum) {
        this.flag = flag;
        this.loopNum = loopNum;
    }

    public void print(String printStr, int waitFlag, int nextFlag) {
        for (int i = 0; i < loopNum; i++) {
            synchronized (this) {
                while (flag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(printStr);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }


}