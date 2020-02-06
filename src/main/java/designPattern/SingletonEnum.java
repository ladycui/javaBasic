package designPattern;

/**
 * @Auther: cyn
 * @Date: 2019-12-25 10:31
 * @Description: jdk1.5新增
 * 传统单例的一个问题：一旦实现序列化接口，则不再保持单例了，case readObject() 会返回一个新的对象；
 * 解决方案：使用private Object readResolve(){return instance;} 不推荐使用
 */
public enum SingletonEnum {
    instance;

    private SingletonEnum() {
    }

    public void method() {
        //...
    }

    public static void main(String[] args) {
        SingletonEnum instance = SingletonEnum.instance;
        SingletonEnum.instance.method();
    }

}
