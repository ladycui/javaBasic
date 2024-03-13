package collection;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HeapDemo {
    public static void main(String[] args) {
        //default, it's a minimum heap
//        PriorityQueue<Integer> heap = new PriorityQueue<>();
//        heap.offer(3);
//        heap.offer(5);
//        heap.offer(6);
//        heap.offer(2);
//        heap.offer(1);
//        for (int i = 0; i < 3; i++) {
//            System.out.println(heap.poll());
//        }
//
//        PriorityQueue<Integer> heap2 = new PriorityQueue<>((a, b) -> b -a);
//        heap2.offer(3);
//        heap2.offer(5);
//        heap2.offer(6);
//        heap2.offer(2);
//        heap2.offer(1);
//        for (int i = 0; i < 3; i++) {
//            System.out.println(heap2.poll());
//        }

        HeapDemo obj = new HeapDemo();
        int[] nums = {3,0,1,0};
        int[] topKFrequent = obj.topKFrequent(nums, 1);
        System.out.println(Arrays.toString(topKFrequent));

    }

    /**
     * find the top k frequent elements in an array
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];

        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> occurrences.get(a) - occurrences.get(b));
        for (Integer key : occurrences.keySet()) {
            if(heap.size() < k)
                heap.offer(key);
            else if(occurrences.get(heap.peek()) < occurrences.get(key)){
                heap.poll();
                heap.offer(key);
            }
        }

        for (int i = 0; i < k; i++) {
            res[i] = heap.poll();
        }

        return res;
    }

}
