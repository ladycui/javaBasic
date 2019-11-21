package java8plus;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: cyn
 * @Date: 2019-11-21 10:01
 * @Description:
 */
public class StreamDemo {

    public static void main(String[] args) {

//        Integer result = Stream.of(1, 2, 3, 4, 5).reduce((i, j) -> {//上一次的结果作为下一次的输入
//            System.out.println("i:\t" + i);
//            System.out.println("j:\t" + j);
//            return i + j;
//        }).get();
//        System.out.println(result);
//        Integer reduceFromIdentity = Stream.of(1, 2, 3, 4, 5).reduce(2, (i, j) -> {
//            System.out.println("i:\t" + i);
//            System.out.println("j:\t" + j);
//            return i + j;
//        });
//        System.out.println(reduceFromIdentity);

        Stream.of(1, 2, 3, 4, 4, 5)
                .filter(i -> i % 2 == 0)
                .map(i -> i * i)
                .distinct()
                .collect(Collectors.toList()).forEach(System.out::println);


    }

}
