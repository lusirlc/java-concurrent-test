package base;

/**
 * join()方法: 插队执行, 阻塞调用此方法的线程(calling thread)进入 TIMED_WAITING 状态，直到线程t完成，此线程再继续
 */
public class JoinThread {
    static class T1 extends Thread {
        @Override
        public void run() {
                System.out.println(Thread.currentThread().getName() + ":开始执行任务");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":任务执行完毕");
        }
    }

    static class T2 extends Thread {
        @Override
        public void run() {
                System.out.println(Thread.currentThread().getName() + ":开始执行任务");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":任务执行完毕");
            }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.setName("线程1");
        t2.setName("线程2");
        t1.start();
        t1.join(); // 使用join方法之后,mian线程必须要等到t1线程执行完毕才能继续执行
        t2.start(); // t2在t1后调用, 如果t1使用了join方法, 可以达到t1,t2顺序执行的效果
        t2.join();
        System.out.println("main thread is finished");
    }
}
