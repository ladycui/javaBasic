package threadPoolDemo;

import java.util.concurrent.*;

/**
 * @Auther: cyn
 * @Date: 2019-10-12 15:34
 * @Description: FutureTask extends Runnable, Future<V>
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        FutureTask<Integer> runnable = new FutureTask<>(() -> 10);
        threadPool.submit(runnable);//used as a runnable
        int result = runnable.get();
        System.out.println(result);


        Future future = threadPool.submit(runnable);
        System.out.println(future.get());//this got null, cause the result is put in FutureTask
        System.out.println(runnable.get());



    }
}
