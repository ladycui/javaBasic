package reference.phantomeDemo;

import sun.misc.Unsafe;

public class FreeMemoryTask implements Runnable {
    private long address = 0;

    public FreeMemoryTask(long address) {
        this.address = address;
    }

    @Override
    public void run() {
        System.out.println("running free memory task");
        if (address == 0) {
            System.out.println("already released");
        } else {
            Unsafe.getUnsafe().freeMemory(address);
        }
    }
}
