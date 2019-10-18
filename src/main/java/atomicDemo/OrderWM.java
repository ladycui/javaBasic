package atomicDemo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: cyn
 * @Date: 2019-10-18 14:09
 * @Description: 订单库存（简化）
 */
public class OrderWM {

    final AtomicReference<WMRange> atomicReference = new AtomicReference<>(new WMRange(0, 0));

    void setUpper(int newUpper) {
        while (true) {
            WMRange originalObj = atomicReference.get();
            if (newUpper < originalObj.lower) {
                throw new IllegalArgumentException();
            }
            WMRange newObj = new WMRange(newUpper, originalObj.lower);
            if (atomicReference.compareAndSet(originalObj, newObj)) {
                return;
            }
        }
    }

    class WMRange {
        final int upper;//库存的上限
        final int lower;//下限

        public WMRange(int upper, int lower) {
            this.upper = upper;
            this.lower = lower;
        }
    }

}
