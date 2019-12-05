package genericsDemo;

import java.lang.reflect.Array;

/**
 * @Auther: cyn
 * @Date: 2019-12-04 17:49
 * @Description: 范型数组的创建，不能直接使用类型转换
 */
public class GenericsDemo<T> {
    T[] array;
    int size;

    public GenericsDemo(Class type, int size) {
        array = (T[]) Array.newInstance(type, size);
        this.size = size;
    }

    public T[] getArray() {
        return array;
    }
    public static void main(String[] args) {
        GenericsDemo<String> demo = new GenericsDemo<>(String.class, 5);
        System.out.println(demo.getArray().length);
    }
}
