package cyclicBarrierDemo;

import java.util.Queue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Auther: cyn
 * @Date: 2019-10-11 14:39
 * @Description:
 */
public class Task2 implements Runnable {
    CyclicBarrier barrier;
    int continueFlag;
    Queue<Integer> queue;

    public Task2(CyclicBarrier barrier, int continueFlag, Queue queue) {
        this.barrier = barrier;
        this.continueFlag = continueFlag;
        this.queue = queue;
    }

    @Override
    public void run() {
        while(continueFlag++ < 10) {
            queue.add(continueFlag);
            System.out.println("Task2 is running, flag = " + continueFlag);
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
