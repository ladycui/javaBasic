package reference.phantomeDemo;


import sun.misc.Unsafe;

public class LargeObject {
    private long address = 0;

    public long getAddress() {
        return address;
    }
    public LargeObject() {
        address = Unsafe.getUnsafe().allocateMemory(2 * 1024);
    }

}

