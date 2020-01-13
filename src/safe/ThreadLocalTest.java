package safe;


/**
 * ThreadLocal:每个线程创建共享变量的副本，使各线程对变量的操作不会影响到其他线程对变量的操作，
 * 让各线程隔离
 */
public class ThreadLocalTest {
//    private static Integer num =0;
private static ThreadLocal<Integer> numLocal = new ThreadLocal<Integer>(){
    @Override
    protected Integer initialValue() {
        return 0;
    }
};
    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
//                num+=5;
//                System.out.println(Thread.currentThread().getName()+":"+num);
                Integer num = numLocal.get();
                num+=5;
                numLocal.set(num);
                System.out.println(Thread.currentThread().getName()+":"+numLocal.get());
            },"Thread-"+i);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
