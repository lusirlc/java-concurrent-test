package base;

/**
 * java 线程的停止
 * 1. stop()方法: 强行停止, 任务可能运行到关键时刻, 导致重大事故, 不推荐,
 * 2. interrupt()方法: 打停止标记, 可以检测到停止标记后退出任务, 不会强制停止任务的执行
 */
public class StopThread {
    static class StopRunnable implements Runnable {
        @Override
        public void run() {
            for (; ; ) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":work start");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + ":work end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // stop方法即使任务被强制停止了, 但是最终还是会走finally块中的方法
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":finally work");
                }
            }
        }
    }

    static class InterruptThread extends Thread {
        @Override
        public void run() {
            for (; ; ) {
                if (isInterrupted()) { // 当检测到停止标记时退出循环执行任务
                    System.out.println(isInterrupted());
                    break;
                }
                try {
                    System.out.println(Thread.currentThread().getName() + ":work start");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + ":work end");
                    System.out.println(isInterrupted());
                    System.out.println(Thread.interrupted());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread stop = new Thread(new StopRunnable(),"StopRunnable");
        Thread interrupt = new InterruptThread();
        interrupt.setName("InterruptThread");
        stop.start();
        interrupt.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop.stop();
        interrupt.interrupt();
    }
}
