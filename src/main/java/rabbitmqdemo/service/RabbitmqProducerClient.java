package rabbitmqdemo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rabbitmqdemo.entity.RabbitMQConstants;

/**
 * @Auther: cyn
 * @Date: 2019-12-02 01:11
 * @Description:
 */
@Component
public class RabbitmqProducerClient {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConstants.MESSAGE_EXCHANGE, RabbitMQConstants.MESSAGE_ROUTINE_KEY,message);
    }

}
