package unsafe;

import cas.Account;
import sun.misc.Unsafe;
import tools.UnsafeCreator;

/**
 * 用Unsafe对象创建一个自己的AtomicInteger类
 */
public class TestMyAtomicInteger {
    public static void main(String[] args) {
        Account.execute(new MyAtomicInteger(10000));
    }
}

class MyAtomicInteger implements Account{

    private volatile int value;
    private static final long valueOffset;
    private static final Unsafe UNSAFE;
    static {
        UNSAFE = UnsafeCreator.getUnsafe();
        try {
            valueOffset = UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public MyAtomicInteger(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public void decrement(int amount) {
        while (true) {
            int prev = this.value;
            int next = prev - amount;
            if (UNSAFE.compareAndSwapInt(this,valueOffset,prev,next)) {
                break;
            }
        }
    }
    @Override
    public Integer getBalance() {
        return getValue();
    }

    @Override
    public void withDraw(Integer amount) {
        decrement(amount);
    }
}