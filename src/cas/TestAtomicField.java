package cas;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 测试原子字段更新器
 */
public class TestAtomicField {
    public static void main(String[] args) {
        Student stu = new Student();
        AtomicReferenceFieldUpdater<Student, String> updater
                = AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");
        System.out.println(updater.compareAndSet(stu, null, "张三"));
        System.out.println(stu);
    }
}

class Student{
    volatile String name;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}