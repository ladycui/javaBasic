package java8plus;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: cyn
 * @Date: 2019-11-21 10:01
 * @Description: reference: https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/
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
//
//        Stream.of(1, 2, 3, 4, 4, 5)
//                .filter(i -> i % 2 == 0)
//                .map(i -> i * i)
//                .distinct()
//                .collect(Collectors.toList()).forEach(System.out::println);

        String[] words = {"a", "abcd", "abc", "ab", "bc"};
//        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        Arrays.asList(words).sort((a, b) -> Integer.compare(a.length(), b.length()));
        List<String> sorted = Arrays.stream(words).sorted((a, b) -> a.length() - b.length()).collect(Collectors.toList());
        System.out.println(sorted);
        System.out.println(Arrays.asList(words));

        int[] nums = {2, 5, 6, 1, 3, 7};
        Arrays.asList(nums);// 基本数据类型 的结果为 List<int[]> 而非 List<Integer>
        //int[] -> List<Integer>
        List<Integer> integerList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        //List<Integer> -> int[]
        int[] ints = integerList.stream().mapToInt(Integer::valueOf).toArray();
        //List<Integer> -> Integer[]
        Integer[] integers = integerList.toArray(new Integer[0]);

        int[] filterArray = Arrays.stream(nums).filter(e -> e > 5).toArray();
        System.out.println(Arrays.toString(filterArray));
    }


    public void testStreamLimit() {
//        List<Person> people = Arrays.asList(new Person("one", 4),
//                new Person("two", 2), new Person("three", 3), new Person("one", 1));
//        List<Person> lessThan4 = people.stream().filter(p -> p.age <= 4)
//                .limit(3).collect(Collectors.toList());
//        lessThan4.stream().forEach(p -> System.out.println(p));
        String[] words = {"hello", "world"};
        List<String> wordsList = Arrays.asList(words);
//        List<String[]> collect = wordsList.stream().
//                map(w -> w.split(""))
//                .distinct()
//                .collect(Collectors.toList());
//        Stream<String> streamWords = Arrays.stream(words);
        List<Stream<String>> collect = wordsList.stream()
                .map(w -> w.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        /**
         * map(Arrays::stream): Stream<String[]> --> Stream<Stream<String>>
         * flatMap(Arrays::stream): 将map(Arrays::stream)生成的流合并为一个流
         *                      Stream<String[]> --> Stream<Stream<String>> --> Stream<String>
         */
        List<String> collectFlat = wordsList.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<Stream<int[]>> pairs = numbers1.stream()//Stream<Integer>
//        Stream<int[]> stream2 = numbers2.stream().map(j -> new int[]{1, j});
                .map(i -> numbers2.stream().map(j -> new int[]{i, j}))//Stream<Stream<int[]>>
                .collect(Collectors.toList());//List<Stream<int[]>>

        List<int[]> correctPairs = numbers1.stream()
                .flatMap((i -> numbers2.stream().map(j -> new int[]{i, j}))) // Stream<int[]> -> int[]
                .collect(Collectors.toList());

        List<int[]> correctPairs3 = numbers1.stream()
                .flatMap((i -> numbers2.stream().filter(j -> i + j % 3 == 0).map(j -> new int[]{i, j}))) // Stream<int[]> -> int[]
                .collect(Collectors.toList());

        int sum = numbers1.stream().reduce(0, (a, b) -> a + b);
        int sum2 = numbers1.stream().reduce(0, Integer::sum);
        Optional<Integer> reduceSum = numbers1.stream().reduce((a, b) -> a + b);
        reduceSum.ifPresent(s -> System.out.println(s));

        Optional<Integer> max = numbers1.stream().reduce((a, b) -> Integer.max(a, b));
        max.ifPresent(m -> System.out.println(m));
        Optional<Integer> max2 = numbers1.stream().reduce(Integer::max);

    }


}
