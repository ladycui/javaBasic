package routineProblemsNote;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Auther: cyn
 * @Date: 2019-11-26 17:38
 * @Description: 1. 常用的三种读文件用法，推荐使用Buffered；
 * 2. 遍历JSONArray的食用方式；
 */
@Slf4j
public class ReadFile {

///Users/cyn/practiceCode/javaBasic/src/main/java/routineProblemsNote/ReadFile.java

    public static void main(String[] args) {
        String content = readFileWithBuffer("/Users/cyn/practiceCode/javaBasic/src/main/resources/responseData.json");

        JSONObject contentObj = JSONObject.parseObject(content);
        JSONObject body = contentObj.getJSONObject("body");
        JSONObject data = body.getJSONObject("data");
        List<JSONObject> origin_configs = body.getObject("origin_configs", List.class);
        System.out.println(JSONObject.toJSONString(data));
        System.out.println(JSONObject.toJSONString(origin_configs));
//
//        Set<String> domains = new HashSet<>();
//
//        //遍历JSONArray以后用这种方式
//        final List<JSONObject> list = body.getObject("data", List.class);
//        for (JSONObject ele : list) {
//            domains.add(ele.getString("channel_id"));
//        }
//        System.out.println(list.size());
//        System.out.println(domains.size());


    }

    //字节流
    public static String readFileWithByte(String path) {
        String content = null;
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            StringBuilder buffer = new StringBuilder();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bytes)) != -1) {
                buffer.append(new String(bytes, 0, len));
            }
            content = buffer.toString();
        } catch (IOException e) {
            log.error("reading file happens error: {}", e.getMessage());
        }
        return content;
    }

    //字符流
    public static String readFileWithCharacter(String path) {
        String content = null;
        try (FileReader reader = new FileReader(path)) {
            StringBuilder buffer = new StringBuilder();
            char[] chars = new char[1024];
            int len;
            while ((len = reader.read(chars)) != -1) {
                buffer.append(chars, 0, len);
            }
            content = buffer.toString();
        } catch (IOException e) {
            log.error("reading file happens error: {}", e.getMessage());
        }
        return content;
    }

    //字符缓冲流
    public static String readFileWithBuffer(String path) {
        String content = null;
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
            content = buffer.toString();
        } catch (IOException e) {
            log.error("reading file happens error: {}", e.getMessage());
        }
        return content;
    }

}
