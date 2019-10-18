package basicUsage;

/**
 * @Auther: cyn
 * @Date: 2019-10-09 10:03
 * @Description:
 */
public class ExceptionDemo {
    public void main(String[] args) {
        thisIsException();
    }

    /**
     * 被调用方法抛出编译异常，调用方法必须处理；被调用方法抛出运行时异常，调用方法编译时check不到，此处在编程时要注意。
     * 另外,子类抛出的异常类型不能比父类抛出的异常类型更宽泛，
     * 例如 父类抛出了RuntimeException，子类方法抛出Exception会编译异常。
     * 子类可以抛小异常，甚至不抛异常
     *
     * @throws Exception
     */
    public void thisIsException() throws /*InterruptedException, */ RuntimeException {
    }


}
