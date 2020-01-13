package base;

/**
 * java 线程的挂起（暂停)和继续
 *  1.suspend()方法: suspend() 在导致线程暂停的同时，并不会去释放任何锁资源。
 *  其他线程都无法访问被它占用的锁。直到对应的线程执行 resume() 方法后，
 *  被挂起的线程才能继续，从而其它被阻塞在这个锁的线程才可以继续执行。
 *  2.resume()方法: 如果 resume() 操作出现在 suspend() 之前执行，那么线程将一直处于挂起状态，同时一直占用锁，这就产生了死锁。
 *  而且，对于被挂起的线程，它的线程状态居然还是 Runnable。
 */
public class SuspendResumeThread {
    static Object lock = new Object();

    static class TestTread extends Thread {
        public TestTread(String name){
            super.setName(name);
        }
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName()+":抢到了锁");
                System.out.println(Thread.currentThread().getName()+":开始执行任务");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":任务执行完毕");
            }
        }
    }

    public static void main(String[] args) {
        TestTread t1 = new TestTread("线程1");
        TestTread t2 = new TestTread("线程2");
        t1.start();
        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.suspend();// t1线程挂起,不会释放锁资源,t2线程不会运行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.resume();// t1线程继续
    }

}
