package java8plus;

import java.util.stream.IntStream;

public class IntStreamDemo {
    public static void main(String[] args) {
        IntStream.range(0, 6).forEach(System.out::print);
        IntStream.range(0, 6).filter(e -> e % 2 == 0).max();
        IntStream.range(0, 6).sum();
        IntStream.range(0, 6).map(e -> e * 2).forEach(System.out::println);
    }
}
