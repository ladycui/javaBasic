package gc;

import java.util.concurrent.TimeUnit;

public class DeadlockDemo {
    public static void main(String[] args) {
        DeadlockDemo demo = new DeadlockDemo();
        Thread t1 = new Thread(() -> demo.method());
        Thread t2 = new Thread(() -> demo.method());
        Thread t3 = new Thread(() -> demo.method());
        Thread t4 = new Thread(() -> demo.method());

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    synchronized void method() {
        System.out.println("thread: {}" + Thread.currentThread().getName());
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
