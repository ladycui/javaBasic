package routineProblemsNote;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Auther: cyn
 * @Date: 2019-11-22 09:58
 * @Description:
 */
public class ListModifyWhenIterating {

    public static void main(String[] args) {
        singleThreadIteratorModification();
    }

    /**
     * this is a wrong demo, it throws java.util.ConcurrentModificationException if you try to modify a list when iterating it.
     * wonder why? you can refer to this blog: https://www.cnblogs.com/dolphin0520/p/3933551.html
     *
     * quick note: after modification modCount = 1, while expectedModCount = 0; then execute iterator.next(),
     *  which contains checkForComodification(), checking modCount != expectedModCount, here if !=, throw exception.
     * foreach is the same.
     */
    public static void wrongDemo() {
        JSONArray list = new JSONArray();
        JSONObject jobj = new JSONObject();
        jobj.put("a", "aaa");
        list.add(jobj);
        for (Object obj : list) {
            JSONObject ele = (JSONObject)obj;
            if ("aaa".equalsIgnoreCase(ele.getString("a"))) {
                list.remove(obj);
            }
        }
    }

    public static void singleThreadIteratorModification() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer integer = iterator.next();
            if(integer==2)
                iterator.remove();   //yes! using iterator's remove method
        }
    }

}
