package rabbitmqdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rabbitmqdemo.service.RabbitmqProducerClient;

/**
 * @Auther: cyn
 * @Date: 2019-12-02 01:10
 * @Description:
 */
@RestController
public class Controller {

    @Autowired
    RabbitmqProducerClient client;

    @GetMapping("/hello")
    public  String hello(String param) {
        client.send("hello " + param);
        return "hello" + param;
    }

}
