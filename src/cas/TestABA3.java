package cas;

import tools.SleepTools;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS的ABA问题解决方案：
 * 在线程改动过后标记为更改过，但不知道更改过几次
 */
public class TestABA3 {
    // 初始版本号：0
    static AtomicMarkableReference<String> str = new AtomicMarkableReference<>("A",false);
    public static void main(String[] args) {
        String prev = str.getReference();//获取值
        other();
        SleepTools.second(1);

        // 修改值后版本号+1
        System.out.println("A -> C:"+str.compareAndSet(prev,"C",false,true));
    }
    
    static void other(){

        new Thread(() -> {
            System.out.println("A -> B:"+str.compareAndSet("A","B",false,true));
        }).start();
        new Thread(() -> {
            System.out.println("B -> A:"+str.compareAndSet("B","A",false,true));
        }).start();
    }
}
