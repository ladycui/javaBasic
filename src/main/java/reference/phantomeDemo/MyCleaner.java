package reference.phantomeDemo;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public class MyCleaner {

    private static ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
    private static Map<Reference<Object>, Runnable> taskMap = new HashMap<>();

    static {
        new CleanerThread().start();
    }

    public static void clear(Object heapObject, Runnable task) {
        PhantomReference<Object> reference = new PhantomReference<>(heapObject, referenceQueue);
        taskMap.put(reference, task);
    }

    private static class CleanerThread extends Thread {
        public void run() {
            while (true) {
                try {
                    Reference<Object> reference = (Reference<Object>) referenceQueue.remove();
                    Runnable task = taskMap.remove(reference);
                    task.run();
                } catch (InterruptedException e) {

                }
            }
        }
    }
}
