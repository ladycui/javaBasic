package routineProblemsNote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import routineProblemsNote.entity.Source;

/**
 * @Auther: cyn
 * @Date: 2019-12-18 20:41
 * @Description: normal obj can be transferred to JSON object
 */
public class Object2JSON {
    public static void main(String[] args) {
        Source source = new Source();
        source.setAddr("1.1.1.1");
        source.setPort(80);
        source.setWeight(1);
        System.out.println(source.toString());

        JSONObject jsonObject = (JSONObject) JSON.toJSON(source);

    }
}
