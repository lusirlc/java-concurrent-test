package wn;

import tools.SleepTools;

import java.util.LinkedList;

public class Test4 {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(10);
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                while (true) {
                    messageQueue.get();
                }
            }, "消费者" + i).start();
        }
        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                Message message = new Message(id, "消息" + id);
                messageQueue.put(message);
            }, "生产者" + i).start();
        }
    }

}

/**
 * 消息队列
 */
class MessageQueue {
    // 队列
    private LinkedList<Message> list = new LinkedList<>();
    // 队列容量
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public Message get() {
        synchronized (list) {
            // 检查队列是否为空
            while (list.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + ":队列为空，等待消息进来");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            SleepTools.second(1);
            Message message = list.removeFirst();
            System.out.println(Thread.currentThread().getName() + ":消费了一个消息");
            list.notifyAll();
            return message;
        }
    }

    public void put(Message message) {
        synchronized (list) {
            // 检查队列容量是否已满
            while (list.size() == capacity) {
                System.out.println(Thread.currentThread().getName() + ":队列已满，等待消息消费");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            SleepTools.second(1);
            list.addLast(message);
            System.out.println(Thread.currentThread().getName() + ":向队列中放入了一个消息");
            list.notifyAll();
        }
    }
}

class Message {
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }
}