package cyclicBarrierDemo;

import java.util.Queue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Auther: cyn
 * @Date: 2019-10-11 14:36
 * @Description:
 */
public class Task1 implements Runnable {
    CyclicBarrier barrier;
    int continueFlag;
    Queue<Integer> queue;

    public Task1(CyclicBarrier barrier, int continueFlag, Queue queue) {
        this.barrier = barrier;
        this.continueFlag = continueFlag;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (continueFlag++ < 10) {
            System.out.println("Task1 is running, flag1 = " + continueFlag);
            queue.add(continueFlag);
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
