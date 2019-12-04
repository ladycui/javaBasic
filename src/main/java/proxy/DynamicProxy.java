package proxy;

import java.lang.reflect.Proxy;

/**
 * @Auther: cyn
 * @Date: 2019-12-04 11:36
 * @Description: 动态代理（JDK 方式），this is a demo of 极客时间.Java核心36讲
 */
public class DynamicProxy {

    public static void main(String[] args) {
        HelloImpl hello = new HelloImpl();
        MyInvocationHandler invocationHandler = new MyInvocationHandler(hello);
        Hello proxy = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(), HelloImpl.class.getInterfaces(), invocationHandler);
        proxy.sayHello();
    }
}
