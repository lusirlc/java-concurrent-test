package safe;

import tools.SleepTools;

/**
 * volatile关键字原理:JMM内存模型, 总线MESI缓存一致性协议
 * 其不能保证原子性,只能保证可见性（总线嗅探，立即刷新主内存的值，本地内存失效）
 * 和有序性（内存屏障禁止CPU指令重排）， 如num++操作, 运算和赋值并不是原子性的
 */
public class VolatileTest {
    private static volatile boolean flag = false; // 存在主内存的变量, 默认初始化为false

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("waiting data...");
                while (!flag);
                System.out.println("=========success");
            }
        }).start();
        SleepTools.second(1);//保证前面的线程开始执行了
        new Thread(new Runnable() {
            @Override
            public void run() {
                prepareDate();
            }
        }).start();
    }

    private static void prepareDate() {
        System.out.println("preparing data...");
        SleepTools.second(1);
        flag = true;// 如果生效则之前线程的无限循环会终止
        System.out.println("prepare date end...");
    }

}
