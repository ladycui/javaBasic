package threadPoolDemo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: cyn
 * @Date: 2019-10-12 14:02
 * @Description: corresponding to 22nd, ThreadPool
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        BlockingDeque<Runnable> workingQueue = new LinkedBlockingDeque<>(2);// creating a queue with limit
        ThreadFactory threadFactory = new ThreadFactory() {
            AtomicInteger num = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "pool-test-" + num.getAndIncrement());// rename the thread
            }
        };
        Executor threadPool = new ThreadPoolExecutor(1, 2, 1, TimeUnit.MILLISECONDS, workingQueue, threadFactory);
        for (int i = 0; i < 20; i++) {
            /**
             * here it may throw RuntimeException(e.g. RejectedExecutionException) which you might ignore. So you should catch it.
             * What is worse, it doesn't throw any exception sometimes and you won't realize there happened a problem
             * solution: try{} catch(RuntimeException) {} catch(Throwable) {}
             */
            threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + "running task "));
        }

        Future result = ((ThreadPoolExecutor) threadPool).submit(() -> 10);//accept a callable
        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}

