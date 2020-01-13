package safe;


import tools.SleepTools;

/**
 * 类说明：演示Volatile的提供的可见性
 */
public class VolatileCase {
    private static boolean ready = false;
    private static int number = 0;

    //
    private static class PrintThread extends Thread{
        @Override
        public void run() {
            System.out.println("PrintThread is running.......");
            while (!ready) {
                /**
                 * 不能调用sleep方法，sleep方法会导致cpu进行上下文切换运行其他线程的程序，main线程对共享变量的操作生效后
                 * 再切换到改线程时会读取到主内存中的新值，导致无限循环终止
                 */
//                SleepTools.second(1);
            }//无限循环
            System.out.println("number = "+number);
        }
    }

    public static void main(String[] args) {
        new PrintThread().start();
        SleepTools.second(1);
        number = 51;
        ready = true;
        SleepTools.second(5);
        System.out.println("main is ended!");
    }
}
