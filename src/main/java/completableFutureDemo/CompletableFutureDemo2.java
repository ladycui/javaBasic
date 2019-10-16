package completableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Auther: cyn
 * @Date: 2019-10-16 14:51
 * @Description:
 */
public class CompletableFutureDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T1 is running");
            return "T1's result";
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2 is running");
            return "T2's result";
        });

        CompletableFuture<String> f3 = f1.thenCombine(f2, (r1, r2) -> {
            System.out.println("T3 is running");
            System.out.println("get T1's result: " + r1 + ", and get T2's result: " + r2);
            return "T3's result";
        });

        System.out.println(f3.get());
    }
}
