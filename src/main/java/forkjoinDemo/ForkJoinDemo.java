package forkjoinDemo;

import java.util.Map;
import java.util.concurrent.ForkJoinPool;

/**
 * @Auther: cyn
 * @Date: 2019-10-17 17:06
 * @Description:
 */
public class ForkJoinDemo {

    public static void main(String[] args) {
//        statisticsWord();

        sumTask();

    }

    public static void statisticsWord() {
        String[] lines = {"hello world",
                "hello me",
                "hello fork",
                "hello join",
                "fork join in world"};
        Long startTime = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(5);
        ComputeTask task = new ComputeTask(lines, 0, 4);
        Map<String, Long> result = pool.invoke(task);
        Long endTime = System.currentTimeMillis();
        System.out.println("this task costs: " + (endTime - startTime));
        result.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    public static void sumTask() {
        Long[] nums = {1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l};
        ForkJoinPool pool = new ForkJoinPool(4);
        SumTask task = new SumTask(nums, 0, 8);
        Long result = pool.invoke(task);
        System.out.println(result);
    }
}
