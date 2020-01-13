package base;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Java是一个天生多线程的语言
 */
public class Main {
    public static void main(String[] args) {
        // 获取java虚拟机线程管理器
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 获取所有活动的线程信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("threadInfo.getThreadId() = " + threadInfo.getThreadId());
            System.out.println("threadInfo.getThreadName() = " + threadInfo.getThreadName());
        }
    }
}
