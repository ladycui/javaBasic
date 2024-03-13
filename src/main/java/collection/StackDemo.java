package collection;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.LinkedList;

public class StackDemo {
    public static void main(String[] args) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addLast(1);
        stack.addLast(2);
        stack.addLast(3);
        stack.addLast(4);
        while (!stack.isEmpty()) {
            System.out.println(stack.removeLast());
        }
    }
}
