package cas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 测试原子数组
 */
public class TestAtomicArray {
    public static void main(String[] args) {
        execute(
                () -> new int[10],
                (array) -> array.length,
                (array, index) -> array[index]++,
                array -> System.out.println(Arrays.toString(array))
        );

        execute(
                () -> new AtomicIntegerArray(10),
                (array) -> array.length(),
                (array, index) -> array.getAndIncrement(index),
                array -> System.out.println(array)
        );
    }

    /**
     * @param arraySupplier 提供者，无中生有    ()->结果
     * @param lengthFun     函数，一个参数一个结果 (参数)->结果    BiFunction(参数1,参数2)->结果
     * @param putConsumer   消费者，一个参数没有结果    (参数)->void  BiConsumer(参数1,参数2)->void
     * @param printConsumer
     * @param <T>
     */
    public static <T> void execute(
            Supplier<T> arraySupplier,
            Function<T, Integer> lengthFun,
            BiConsumer<T, Integer> putConsumer,
            Consumer<T> printConsumer) {
        List<Thread> ts = new ArrayList<>();
        T array = arraySupplier.get();
        Integer length = lengthFun.apply(array);
        for (Integer i = 0; i < length; i++) {
            ts.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    putConsumer.accept(array, j % length);
                }
            }));
        }
        ts.forEach(thread -> thread.start());
        ts.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        printConsumer.accept(array);
    }
}
