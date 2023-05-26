package juc;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * attention: when using thread local, consider which threads these tasks are executed on, i.e. don't use thread pool,
 * because thread pool shares common threads
 */
public class ThreadLocalIssue {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadPool.execute(new MyRunnable(threadLocal, "first try"));
        threadPool.execute(new MyRunnable(threadLocal, "second try"));
        threadPool.shutdown();
    }

    @Builder
    @Slf4j
    static class MyRunnable implements Runnable {
        ThreadLocal<String> threadLocal;
        String name;

        @Override
        public void run() {
            try {
                log.info(name + "--before set--\t" + threadLocal.get());//second usage of this thread returns the old data in last usage
                threadLocal.set(name);
                log.info(name + "--after set--\t" + threadLocal.get());
            } finally {
                //solution: when finishing the usage of this data, remove it from threadlocal, then next time, when other transaction won't get old data in this same thread
                threadLocal.remove();
            }
        }
    }
}
