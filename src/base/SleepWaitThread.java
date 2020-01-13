package base;

/**
 * 线程sleep()方法和wait()方法区别
 * sleep()是超时等待, 过了规定的超时时间会自动被唤醒回到线程的就绪状态
 * wait()方法是无限等待, 必须要手动进行唤醒
 * sleep()睡眠时，保持对象锁，仍然占有该锁,其他线程无法访问
 * wait()睡眠时，释放对象锁,其他线程可以访问
 */
public class SleepWaitThread {
    static Object lock = new Object();

    static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + ":抢到了锁");
                System.out.println(Thread.currentThread().getName() + ":开始执行任务");
                try {
//                    Thread.sleep(1000);
                    lock.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":任务执行完毕");
            }
        }
    }

    static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + ":抢到了锁");
                System.out.println(Thread.currentThread().getName() + ":开始执行任务");
                try {
//                    Thread.sleep(1000);
                    lock.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":任务执行完毕");
            }
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.setName("线程1");
        t2.setName("线程2");
        t1.start();
        t2.start();
    }
}
