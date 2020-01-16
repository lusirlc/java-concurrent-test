package vlt;

import tools.SleepTools;

import java.util.concurrent.TimeUnit;

/**
 * 同步模式之Balking模式:
 * 一个线程发现已经有另外一个线程在做它相同的事时，那么本线程就无须再做了
 */
public class Test2 {
    public static void main(String[] args) {
        MonitorService monitor = new MonitorService();
        monitor.start();
        monitor.start();
        SleepTools.ms(2500);
        monitor.stop();
    }
}

class MonitorService {
    static volatile boolean flag;
    static volatile boolean staring;
    private Thread monitor;
    public void start() {
        synchronized (this) {
            if (staring) {//是否已经启动过
                return;
            }
            staring = true;
        }
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