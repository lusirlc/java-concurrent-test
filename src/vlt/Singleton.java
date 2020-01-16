package vlt;

/**
 * 单例模式（懒汉式）双重检查锁定
 */
public class Singleton {
//   private static Singleton singleton = null;//指令重排序可能会发生构造方法还没有完成就返回了引用地址
    private static volatile Singleton singleton = null;
    private Singleton() {

    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                singleton = new Singleton();
            }
        }
        return singleton;
    }
}
