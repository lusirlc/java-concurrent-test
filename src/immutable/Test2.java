package immutable;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * 不可变类不会产生线程安全问题
 */
public class Test2 {
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                TemporalAccessor parse = dtf.parse("2019-12-12");
                System.out.println(parse);
            }).start();

        }
    }
}
