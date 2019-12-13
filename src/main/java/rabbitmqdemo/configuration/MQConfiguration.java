package rabbitmqdemo.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rabbitmqdemo.entity.RabbitMQConstants;

/**
 * @Auther: cyn
 * @Date: 2019-12-03 09:52
 * @Description:
 */
@Configuration
public class MQConfiguration {

    @Bean
    public Queue getQueue() {
        return QueueBuilder.durable(RabbitMQConstants.MESSAGE_QUEUE_NAME).build();
    }

    @Bean
    public DirectExchange messageDirectExchange() {
        return (DirectExchange) ExchangeBuilder.directExchange(RabbitMQConstants.MESSAGE_EXCHANGE).durable(true).build();
    }

    @Bean
    public Binding messageBinding() {
        return BindingBuilder.bind(getQueue()).to(messageDirectExchange()).with(RabbitMQConstants.MESSAGE_ROUTINE_KEY);
    }

}
