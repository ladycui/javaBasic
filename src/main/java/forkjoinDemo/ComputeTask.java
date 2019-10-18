package forkjoinDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

/**
 * @Auther: cyn
 * @Date: 2019-10-17 17:11
 * @Description: 统计文档中单词数
 */
public class ComputeTask extends RecursiveTask<Map<String, Long>> {

    private String[] lines;
    private int start;
    private int end;

    public ComputeTask(String[] lines, int start, int end) {
        this.lines = lines;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Map<String, Long> compute() {
        if (end - start == 0) {
            return calLine(lines[start]);
        }
        int mid = (end + start) / 2;
        ComputeTask t1 = new ComputeTask(lines, start, mid);
        t1.fork();//创建一个子任务，计算前一半
        ComputeTask self = new ComputeTask(lines, mid + 1, end);//自己执行后一半任务
        return merge(self.compute(), t1.join());
    }

    private Map<String, Long> merge(Map<String, Long> task1, Map<String, Long> task2) {
        Map<String, Long> results = new HashMap<>();
        results.putAll(task1);
        task2.forEach((k, v) -> {
            Long c = results.get(k);
            if (c != null) {
                results.put(k, c + v);
            } else {
                results.put(k, v);
            }
        });
        return results;
    }

    private Map<String, Long> calLine(String line) {
        if(line == null || line.length() == 0)
            return null;
        Map<String, Long> result = new HashMap<>();
        String[] words = line.split("\\s+");
        for (String word : words) {
            Long v = result.get(word);
            if (v == null) {
                result.put(word, 1L);
            } else {
                result.put(word, v + 1);
            }
        }
        return result;
    }
}
