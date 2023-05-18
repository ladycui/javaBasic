package cyclicBarrierDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 1000个订单 每个订单都要读product和delivery数据 读完之后再做一个操作
 * 这里只是一个demo，正式代码用线程池
 */
public class CyclicBarrierDemo2 {
    CyclicBarrier barrier;
    int page = 0;

    public CyclicBarrierDemo2() {
        barrier = new CyclicBarrier(2, () -> {
            System.out.println("sync....");
            page++;
        });
    }

    void prepareProducts(){
        while (page < 1000) {
            try {
                //do some business here, e.g. read product from DB
                System.out.println("reading product data");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void prepareDeliverOrders() {
        while (page < 1000) {
            try {
                //do some business here, e.g. read delivery order from DB
                System.out.println("reading delivery data");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void run() {
        new Thread(this::prepareProducts).start();
        new Thread(this::prepareDeliverOrders).start();
    }

    public static void main(String[] args) {
        CyclicBarrierDemo2 test = new CyclicBarrierDemo2();
        test.run();
    }
}
