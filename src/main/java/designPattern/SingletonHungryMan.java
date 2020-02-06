package designPattern;

/**
 * @Auther: cyn
 * @Date: 2019-12-25 09:40
 * @Description: features: 1. private 构造方法和实例；2. 对外暴露public static 获取实例方法
 * 饿汉式（类加载时即实例化对象，避免线程同步问题） and 饿汉式
 */
public class SingletonHungryMan {
    private static SingletonHungryMan instance = new SingletonHungryMan();

    private SingletonHungryMan() {
    }

    public static SingletonHungryMan getInstance() {
        return instance;
    }
}
