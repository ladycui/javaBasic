package cyclicBarrierDemo;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther: cyn
 * @Date: 2019-10-11 14:36
 * @Description: Task1 与 Task2 都独立完成任务后，触发另一任务 //配合 极客时间-JAVA并发编程实战19th
 * usage of CyclicBarrier
 */
@Deprecated
public class CyclicBarrierDemo {
    static int continueFlag = 0;

//    static AtomicInteger i = new AtomicInteger(0);

    static Queue<Integer> queue1 = new LinkedBlockingDeque<>(10);
    static Queue<Integer> queue2 = new LinkedBlockingDeque<>(10);

    public static void main(String[] args) {
        final Executor executor = Executors.newFixedThreadPool(1);
        CyclicBarrier barrier = new CyclicBarrier(2, () -> executor.execute(() -> task3()));
        Thread task1 = new Thread(new Task1(barrier, continueFlag, queue1));
        Thread task2 = new Thread(new Task2(barrier, continueFlag, queue2));
        task1.start();
        task2.start();
//        i.getAndIncrement();
        System.out.println(((ThreadPoolExecutor) executor).getMaximumPoolSize());//查看线程池状态
        System.out.println(barrier.getNumberWaiting());
    }

    static void task3() {
        System.out.println("Task3 has run! flag = " + ++continueFlag +
                "\tconsuming from queque1 : " + queue1.remove() + "\tqueue2 : " + queue2.remove());

    }
}
