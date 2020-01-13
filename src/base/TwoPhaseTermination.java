package base;

import java.util.concurrent.TimeUnit;

/**
 * 线程两阶段终止模式
 */
public class TwoPhaseTermination {

    public static void main(String[] args) throws InterruptedException {
        Monitor monitor = new Monitor();
        monitor.start();
        TimeUnit.SECONDS.sleep(2);
        monitor.stop();
    }
}

class Monitor {
    private Thread monitor;
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                if (current.isInterrupted()) {
                    System.out.println("线程被打断，料理后事");
                    break;
                }
                try {
                    // 间隔1s监控一次打断标记
                    TimeUnit.SECONDS.sleep(1);//sleep,wait,join会将打断标记清除
                    System.out.println("执行监控程序...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    current.interrupt();//需要重新打上标记
                }
            }
        });
        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }
}