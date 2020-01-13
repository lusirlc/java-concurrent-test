package base;

/**
 * 守护线程特性:
 * 其他线程的任务完成了, 不管自己的任务有没有完成, 会被强制结束;
 * 如果其他线程没有完成, 不管自己的任务有没有完成, 都将进行等待
 */
public class DaemonThread {
    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+":work start");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+":work end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 如果守护线程强制结束, 则finally块中的代码不一定会被执行,因为cpu时间片停止分配了
                System.out.println(Thread.currentThread().getName()+":finally work");
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.setDaemon(true);//将线程设置为守护线程
        thread.start();
        try {
            System.out.println(Thread.currentThread().getName()+":work start");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName()+":work end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
