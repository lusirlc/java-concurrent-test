package cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子基本类的使用
 */
public class TestAccount {
    public static void main(String[] args) {
        Account account1 = new AccountUnsafe(10000);
        Account account2 = new AccountCas(10000);
        Account.execute(account1);
        Account.execute(account2);
    }
}

/**
 * 保护共享资源（无锁）
 */
class AccountCas implements Account {
    private AtomicInteger balance;

    public AccountCas(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withDraw(Integer amount) {
//        while (true) {
//            // 获取当前余额
//            int prev = balance.get();
//            // 修改余额
//            int next = balance.get() - amount;
//            // 真正修改余额
//            if (balance.compareAndSet(prev, next)) {
//                break;
//            }
//        }
        balance.getAndAdd(-amount);
    }
}
/**
 * 保护共享资源（加锁）
 */
class AccountUnsafe implements Account {
    private Integer balance;

    public AccountUnsafe(Integer balance) {
        synchronized (this) {
            this.balance = balance;
        }
    }

    @Override
    public Integer getBalance() {
        return this.balance;
    }

    @Override
    public void withDraw(Integer amount) {
        synchronized (this) {
            this.balance -= amount;
        }
    }
}

