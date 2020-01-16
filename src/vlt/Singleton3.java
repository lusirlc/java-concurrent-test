package vlt;

/**
 * 单例模式（饿汉式）
 */
public class Singleton3 {
    private Singleton3() {

    }
    private static class LazyLoad{
        static final Singleton3 SINGLETON_3 = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return LazyLoad.SINGLETON_3;
    }
}
