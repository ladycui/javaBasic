package atomicDemo;

/**
 * @Auther: cyn
 * @Date: 2019-10-18 14:08
 * @Description: 修改库存上下限。 honestly, I don't think this demo is proper. I'll update it later when I find a properer one.
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        OrderWM update = new OrderWM();
        update.setUpper(10);
        System.out.println(update.atomicReference.get().upper);
    }
}
