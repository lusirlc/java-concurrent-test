package vlt;

import tools.SleepTools;

import java.util.concurrent.TimeUnit;

/**
 * 两阶段终止模式（使用volatile关键字）
 */
public class Test1 {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        monitor.start();
        SleepTools.ms(2500);
        monitor.stop();
    }
}

class Monitor {
    static volatile boolean flag;
    private Thread monitor;
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                if (flag) {
                    System.out.println("线程被打断，料理后事");
                    break;
                }
                try {
                    // 间隔1s监控一次打断标记
                    TimeUnit.SECONDS.sleep(1);//sleep,wait,join会将打断标记清除
                    System.out.println("执行监控程序...");
                } catch (InterruptedException e) {
                }
            }
        });
        monitor.start();
    }

    public void stop() {
        flag = true;
        monitor.interrupt();//为了尽快料理后事
    }
}