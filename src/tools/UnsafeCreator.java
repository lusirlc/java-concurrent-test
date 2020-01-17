package tools;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 获取unsafe对象的工具类
 */
public class UnsafeCreator {
    private static Unsafe unsafe;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static Unsafe getUnsafe() {
        return unsafe;
    }
}
