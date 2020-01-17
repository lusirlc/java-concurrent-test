package cas;

import tools.SleepTools;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS的ABA问题解决方案：
 * 在线程改动过后版本号+1，这样通过比较版本号就能知道是否有线程将原值修改
 */
public class TestABA2 {
    // 初始版本号：0
    static AtomicStampedReference<String> str = new AtomicStampedReference<>("A",0);
    public static void main(String[] args) {
        String prev = str.getReference();//获取值
        int stamp = str.getStamp();// 获取版本号
        System.out.println(stamp);
        other();
        SleepTools.second(1);

        System.out.println(str.getStamp());
        // 修改值后版本号+1
        System.out.println("A -> C:"+str.compareAndSet(prev,"C",stamp,stamp+1));
    }
    
    static void other(){

        new Thread(() -> {
            int stamp = str.getStamp();
            System.out.println("A -> B:"+str.compareAndSet("A","B",stamp,stamp+1));
        }).start();
        new Thread(() -> {
            int stamp = str.getStamp();
            System.out.println("B -> A:"+str.compareAndSet("B","A",stamp,stamp+1));
        }).start();
    }
}
