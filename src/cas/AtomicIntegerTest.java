package cas;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    public static void main(String[] args) {
        AtomicInteger num = new AtomicInteger(0);
        System.out.println(num.updateAndGet(operand -> {
            if (operand == 0) {
                operand = 1;
            } else {
                operand = 2;
            }
            return operand;
        }));
        System.out.println(num);

        System.out.println(num.getAndUpdate(operand -> 10));
        System.out.println(num);
    }
}
