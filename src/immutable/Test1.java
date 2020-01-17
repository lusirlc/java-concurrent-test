package immutable;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 测试可变类在多线程下产生的安全问题
 */
public class Test1 {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(sdf.parse("2019-12-12"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
