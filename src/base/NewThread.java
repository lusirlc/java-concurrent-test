package base;

/**
 * 创建线程的两种方式
 */
public class NewThread {
    /**
     * 继承Tread
     */
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":I am extends Thread");
        }
    }

    /**
     * 实现Runnable
     */
    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":I am implements Runnable");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRunnable()).start();
        System.out.println(Thread.currentThread().getName() + ":I am main thread");
    }
}
