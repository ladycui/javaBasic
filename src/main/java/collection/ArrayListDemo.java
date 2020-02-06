package collection;

import java.util.*;

/**
 * @Auther: cyn
 * @Date: 2020-01-03 15:03
 * @Description:
 */
public class ArrayListDemo {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        System.out.println(list.size());
//        for (int i = 0; i < 10; i++) {
//            list.add("1");
//        }
//        list.add("2");
//        System.out.println(list.size());

//        HashMap<String, String> map = new HashMap<>();
//        for (int i = 0; i < 12; i++) {
//            map.put(String.valueOf(i), String.valueOf(i));
//        }
//        map.put("two", "2");

        List<String> list = new ArrayList<>(Arrays.asList("a1", "ab2", "a3", "ab4", "a5", "ab6", "a7", "ab8", "a9"));
        // 迭代删除方式一：报错 java.util.ConcurrentModificationException
        for (String str : list) {
            System.out.println(str);
            if (str.contains("b")) {
                list.remove(str);
            }
        }

        // 迭代删除方式四:正常删除，推荐使用
        /**
         * use Itr( the inner Iterator of ArrayList)
         */
        for (Iterator<String> ite = list.iterator(); ite.hasNext(); ) {
            String str = ite.next();
            System.out.println(str);
            if (str.contains("b")) {
                ite.remove();
            }

        }


    }
}
