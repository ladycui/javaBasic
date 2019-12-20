package exportData;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import routineProblemsNote.entity.Cache;
import routineProblemsNote.entity.Origin;
import routineProblemsNote.entity.Referer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: cyn
 * @Date: 2019-12-01 22:18
 * @Description:
 */
@SpringBootApplication
public class Application implements ApplicationRunner {

    String[] keys = {"domain", "refer", "ipAcl", "cache", "protocol", "certId", "type", "cname", "origion", "platformId", "httpHeader", "httpsFlag", "httpsConfig", "filterFlag"};


    @Autowired
    DomainRepository domainRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        exportData();
//        Domain domain = new Domain();
//        domain.setId(1l);
//
//        JSONObject configObj = (JSONObject) JSON.toJSON(domain);
//        System.out.println(configObj);
    }


    public void exportData() {
        List<Domain> domainList = domainRepository.findAll();
        JSONArray domainsConfig = new JSONArray();
        for (Domain domain : domainList) {
            JSONObject configObj = (JSONObject) JSON.toJSON(domain);
            JSONObject domainConfig = new JSONObject();
            for (String key : keys) {
                switch (key) {
                    case "refer":
                        Referer referer = JSONObject.parseObject((String) configObj.get(key), Referer.class);
                        JSONObject jsonReferer = (JSONObject) JSON.toJSON(referer);
                        domainConfig.put(key, jsonReferer);
                        break;
                    case "cache":
                    case "httpHeader":
//                        [{"key":"Access-Control-Allow-Methods","value":"GET,HEAD"},{"key":"Content-Language","value":"EN"}]
                        List<JSONObject> cacheList = JSONObject.parseObject((String) configObj.get(key), List.class);
                        domainConfig.put(key, cacheList);
                        break;
                    case "origion"://将其转换为origin
                        Origin origin = JSONObject.parseObject((String) configObj.get(key), Origin.class);
                        JSONObject originJson = (JSONObject) JSON.toJSON(origin);
                        domainConfig.put("origin", originJson);
                        break;
                    default:
                        domainConfig.put(key, configObj.get(key));
                }
            }
            domainsConfig.add(domainConfig);
        }
        String content = JSONArray.toJSONString(domainsConfig);
        writeFileWithBuffer("./exportDomainConfig.json", content);
    }


    public void writeFileWithBuffer(String path, String content) {
        try (FileWriter fileWriter = new FileWriter(path);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            System.out.println();
        }
    }
}
