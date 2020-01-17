package cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 测试原子累加器，效率比AtomicLong的累加方法高
 * 原因：线程竞争的时候，设置多个累加单元，最后将结果汇总，这样减少了CAS重试失败，从而提高性能
 */
public class TestLongAdder {
    public static void main(String[] args) {
        execute(
                () -> new AtomicLong(0),
                adder -> adder.getAndIncrement()
        );
        execute(
                () -> new LongAdder(),
                adder -> adder.increment()
        );
    }

    private static <T> void execute(Supplier<T> adderSupplier, Consumer<T> action) {
        List<Thread> ts = new ArrayList<>();
        T adder = adderSupplier.get();
        for (int i = 0; i < 4; i++) {
            ts.add(new Thread(() -> {
                for (int j = 0; j < 500000; j++) {
                    action.accept(adder);
                }
            }));
        }
        long start = System.nanoTime();
        ts.forEach(t -> t.start());
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(adder + ",cost:" + (end - start) / 1000_000);
    }
}
