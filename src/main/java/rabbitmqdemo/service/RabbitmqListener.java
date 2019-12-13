package rabbitmqdemo.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import rabbitmqdemo.entity.RabbitMQConstants;

import java.io.IOException;

/**
 * @Auther: cyn
 * @Date: 2019-12-02 01:16
 * @Description:
 */
@Component
public class RabbitmqListener {

    @RabbitListener(queues = RabbitMQConstants.MESSAGE_QUEUE_NAME)
    public void receive(Channel channel, Message message) {
        System.out.println("收到的 message 是：" + new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
