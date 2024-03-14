package juc;

import org.springframework.util.StopWatch;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * demo of ConcurrentHashMap & LongAdder
 * freqs.computeIfAbsent("b", k -> new LongAdder()).increment();
 * stopwatch in this demo has no meaning, just a demo showing the usage of StopWatch
 */
public class ConcurrentDemo {
    public static void main(String[] args) throws InterruptedException {
        StopWatch watch = new StopWatch();
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        //key 如果不存在，则存入key 和后面的value
        System.out.println(map.computeIfAbsent("a", k -> {
            System.out.println("k: " + k);
            return new Integer(1);
        }));
        System.out.println(map.computeIfAbsent("a", k -> new Integer(2)));
        System.out.println(map.get("a"));

        watch.start("test-1");
        ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>();
        freqs.computeIfAbsent("b", k -> new LongAdder()).increment();
        freqs.computeIfAbsent("b", k -> new LongAdder()).increment();
        System.out.println("b---" + freqs.getOrDefault("b", new LongAdder()));
        watch.stop();

        watch.start("test-2");
        map.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
        watch.stop();
        System.out.println(watch.prettyPrint());


        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                freqs.computeIfAbsent("c", k -> new LongAdder()).increment();// the type of value is LongAdder instead of long
            }).start();
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 1000).parallel().forEach(i -> freqs.computeIfAbsent("d", k -> new LongAdder()).increment()));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);

        //convert value type LongAdder to long
        freqs.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().longValue()));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(freqs.get("c"));
    }
}
