package designPattern;

/**
 * @Auther: cyn
 * @Date: 2019-12-25 10:01
 * @Description:
 */
public class SingletonLazyMan {
    private static SingletonLazyMan instance = null;
    private SingletonLazyMan(){}

    /**
     * 线程不安全
     * @return
     */
    public static SingletonLazyMan getInstance1() {
        if (instance == null) {
            instance = new SingletonLazyMan();
        }
        return instance;
    }

    /**
     * 双重锁校验， 线程安全
     * @return
     */
    public static SingletonLazyMan getInstance() {
        if (instance == null) {
            synchronized (SingletonLazyMan.class) {
                if (instance == null) {
                    instance = new SingletonLazyMan();
                }
            }
        }
        return instance;
    }
}
