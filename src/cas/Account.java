package cas;

import java.util.ArrayList;
import java.util.List;

public interface Account {
   /**
    * 获取余额
    *
    * @return
    */
   Integer getBalance();

   /**
    * 取款
    *
    * @param amount
    */
   void withDraw(Integer amount);

   static void execute(Account account) {
       List<Thread> ts = new ArrayList<>();
       for (int i = 0; i < 1000; i++) {
           ts.add(new Thread(() -> {
               account.withDraw(10);
           }));
       }
       long start = System.nanoTime();
       ts.forEach(Thread::start);
       ts.forEach(thread -> {
           try {
               thread.join();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       });
       long end = System.nanoTime();
       System.out.println(account.getBalance() + "cost:" + (end - start) / 1000_00 + "ms");
   }
}
