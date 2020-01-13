package wn;

import tools.SleepTools;

/**
 * 线程保护暂停模式
 */
public class Test3 {
    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+":获取结果");
            System.out.println(guardedObject.getResponse(1000));
        },"t1").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+":产生结果");
            SleepTools.second(2);
            guardedObject.setResponse(new Object());
        },"t2").start();
    }
}

/**
 * 监视对象
 */
class GuardedObject {
    // 结果
    private Object response;

    /**
     * 获取结果
     * @return
     */
    public Object getResponse() {
        synchronized (this) {
            while (response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    /**
     * 设置超时等待
     * @param timeout
     * @return
     */
    public Object getResponse(long timeout) {
        synchronized (this) {
            long begin = System.currentTimeMillis();
            long passed = 0;
            while (response == null) {
                long wait = timeout - passed;
                if (wait <= 0) {
                    break;
                }
                try {
                    this.wait(wait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passed = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    /**
     * 设置结果
     * @param response
     */
    public void setResponse(Object response){
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}