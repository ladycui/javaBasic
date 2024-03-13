package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * 我没搞懂流程
 * 1. 不override finalize(), ref 会有值 并打印；
 * 2. override finalize(), ref 不会打印
 * todo priority: low
 */
public class PhantomReferenceBasic {

    public static void main(String[] args) throws Exception{
        SelfDefinedObject counter = new SelfDefinedObject();
        ReferenceQueue refQueue = new ReferenceQueue<>();
        PhantomReference<Object> p = new PhantomReference<>(counter, refQueue);
        counter = null;
        System.gc();
        Thread.sleep(1000);
        try {
            // Remove 是一个阻塞方法，可以指定 timeout，或者选择一直阻塞
            Reference<Object> ref = refQueue.remove(1000L);
            if (ref != null) {
                // do something
                System.out.println("ref is not null, " + ref);
            } else{
                System.out.println("ref is null, " + ref);
            }
        } catch (InterruptedException e) {
            // Handle it
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
