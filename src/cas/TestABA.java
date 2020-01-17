package cas;

import tools.SleepTools;

import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS的ABA问题：
 * 在进行CAS操作时，另外有其他的线程将原先的值进行过修改，只不过后面又将值改回来了，这时候比较的线程是
 * 感知不到原来的值是被修改过的，它只是判断原先的值是不是一样
 */
public class TestABA {
    static AtomicReference<String> str = new AtomicReference<>("A");
    public static void main(String[] args) {
        other();
        SleepTools.second(1);
        System.out.println("A -> C:"+str.compareAndSet("A","C"));
    }

    static void other(){
        new Thread(() -> {
            System.out.println("A -> B:"+str.compareAndSet("A","B"));
        }).start();
        new Thread(() -> {
            System.out.println("B -> A:"+str.compareAndSet("B","A"));
        }).start();
    }
}
