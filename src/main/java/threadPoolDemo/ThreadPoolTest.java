package threadPoolDemo;

import com.alibaba.fastjson.JSON;
import routineProblemsNote.entity.Source;
import sun.java2d.SurfaceDataProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: cyn
 * @Date: 2019-12-31 10:23
 * @Description:
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws Exception {
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 4, 1l, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 4, 1l, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(4), new ThreadPoolExecutor.CallerRunsPolicy());//测试发现main线程也参与重试
        List<Object> resultList = new ArrayList<>();
        int threadNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            threadPool.submit(() -> {
                System.out.println("i'm a runnable: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                Source entity = new Source();
                entity.setAddr("a");
                entity.setPort(80);
                entity.setWeight(80);
                synchronized (ThreadPoolTest.class) {
                    resultList.add(JSON.toJSON(entity));
                    countDownLatch.countDown();
                }
            });

        }
        countDownLatch.await();
        System.out.println("size: " + resultList.size());
        threadPool.shutdown();
        resultList.stream().forEach(ele -> System.out.println(ele));
    }
}
