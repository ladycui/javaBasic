package reference.phantomeDemo;

/**
 * this is a demo code from blog: https://blog.csdn.net/aitangyong/article/details/39455229
 * 当堆中的对象不存在强引用，只存在幽灵引用的时候，JVM会自动将这个对象的幽灵引用加入到与之相关联的的引用队列中
 * 给堆中的对象heapObject添加了一个监控（注册了一个幽灵引用）。taskMap记录幽灵引用和相应的代码回收逻辑。 之后我们在后台开启了一个CleanerThread线程，不断的轮询引用队列，一旦发现队列中有数据(PhantomReference对象)， 就找出对应的Runnable，调用它的run方法，释放堆对象heapObject中引用的堆外内存
 * when executing, there's an exception about Unsafe. todo[pri: low] fix it.
 */
public class TestClass {
    public static void main(String[] args) {
        while (true) {
            LargeObject heapObject = new LargeObject();
            MyCleaner.clear(heapObject, new FreeMemoryTask(heapObject.getAddress()));
            System.gc();
        }
    }
}
