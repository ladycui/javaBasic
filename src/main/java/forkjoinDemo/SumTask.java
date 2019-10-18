package forkjoinDemo;

import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

/**
 * @Auther: cyn
 * @Date: 2019-10-17 17:54
 * @Description:
 */
public class SumTask extends RecursiveTask<Long> {

    final int THREADHOLD = 5;
    Long[] array;
    private int start;
    private int end;

    public SumTask(Long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THREADHOLD) {
            return cal(array, start, end);
        }
        int mid = (start + end) / 2;
        SumTask task1 = new SumTask(array, start, mid);
        SumTask task2 = new SumTask(array, mid + 1, end);

        invokeAll(task1, task2);//task2 fork出去，task1自己留着
        return task1.join() + task2.join();
        /**
         * 上面两句的效果同下，与ComputeTask中一样
         */
//       task2.fork();
//       return task1.compute() + task2.join();

    }

    private Long cal(Long[] array, int start, int end) {//直接计算  区间: [start, end]
        Long sum = 0l;
        for (int i = start; i <= end; i++) {
            sum += array[i];
        }
        return sum;
    }
}
