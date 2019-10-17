package completionServiceDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther: cyn
 * @Date: 2019-10-17 11:10
 * @Description:
 */
public class CompletionServiceDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        List<Future<Integer>> futures = new ArrayList<>(3);
        futures.add(cs.submit(() -> 1));
        futures.add(cs.submit(() -> 2));
        futures.add(cs.submit(() -> 3));
        Integer result;
        result = cs.take().get();
        if (result != null) {
            System.out.println(result);
            for(Future future : futures)
                future.cancel(true);
        }
        executor.shutdown();
    }

}
