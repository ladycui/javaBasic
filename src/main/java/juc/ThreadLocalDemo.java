package juc;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;


@Slf4j
public class ThreadLocalDemo {

    static ThreadLocal<Info> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            threadLocal.set(new Info("abc"));
            log.info(threadLocal.get().name);
        }).start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info((threadLocal.get() == null) + "");
        }).start();
    }

    @Builder
   static class Info{
        String name = "name";
    }
}
