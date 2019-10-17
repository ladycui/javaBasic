package completableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: cyn
 * @Date: 2019-10-16 14:51
 * @Description: experience the 链式编程 style by using CompletableFuture
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1 is running");
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2 is running");
            return "T2's result";
        });
        /**
         * and 聚合
         *
         */
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, r) -> {//attention here： a special usage of lambda
            System.out.println("T3 is running and get T2's result: " + r);
            return "T3's result";
        });

        System.out.println(f3.get());


        /**
         * 串行
         * f4的输出作为 thenApply 中Function的输入；同理，前一个函数的输出为后续Function的输入（即，前CompletionStage的输出为后面thenApply中Function的输入）
         * thenApply(Function<T,U>) | thenAccept(Consumer<U>) | thenRun(Runnable) | thenCompose
         * 异步方式： thenApplyAsync ...
         */
        CompletableFuture<Integer> f4 = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<String> f6 = f4.thenApply((r4) -> {
            System.out.println("T4's result: " + r4);
            return "r5";    //(1)
        }).thenApply(r5 -> {
            System.out.println("T5's result: " + r5);
            return "r6";    //(2)
        }).thenApply(String::toUpperCase);

        System.out.println(f6.get());

        //demo of 'thenCompose'
        CompletableFuture<String> f10 = f4.thenCompose((r4) -> CompletableFuture.supplyAsync(() -> "r5'"));
        System.out.println(f10.get());

        /**
         * or 聚合， 两个任务都会执行，但当有任务有返回结果时，结束未执行完成的任务
         * applyToEither( , Function<T,U>) | acceptEither( , Consumer<U>) | runAfterEither( , Runnable)
         *
         * and聚合可以多个聚合，但or聚合是二选一；若需要多选一，可以考虑组装，例如1，2，3，4，这样：（1，2）either和（3，4）either之后再either，只是一个思路，方案后面再想下更好的
         *  补充：后面专栏中有这么个思路（用CompletionService实现Dubbo中Forking Cluster）：创建一个阻塞队列，线程池异步启动4个任务，将future入队；从队列中take元素，若非空，则其他future.cancel
         */
        CompletableFuture<String> f7 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T7 is running");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T7 is over");
            return "7";
        });
        CompletableFuture<String> f8 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T8 is running");
            return "8";
        });
        CompletableFuture<Integer> f9 = f7.applyToEither(f8, (t) -> Integer.valueOf(t));
        System.out.println(f9.get());

        /**
         * 异常
         */
        CompletableFuture<Integer> f11 = CompletableFuture.supplyAsync(() -> 7 / 0)
                .thenApply(t -> t * 10)
                .exceptionally(e -> 0);
        System.out.println(f11.join());

        CompletableFuture<Integer> f12 = CompletableFuture.supplyAsync(() -> 7 / 0)
                .thenApply(t -> t * 10)
                .exceptionally(e -> 0)
//                .whenComplete((r, e)-> {            //whenComplete vs handle : handle有返回值
//                    System.out.println("f12 is running");
//                    System.out.println(r);
//                    System.out.println(e);//上面已经catch，此处e为null
//                });
                .handle((t, e) -> t+ 1);
        System.out.println(f12.join());

    }
}
