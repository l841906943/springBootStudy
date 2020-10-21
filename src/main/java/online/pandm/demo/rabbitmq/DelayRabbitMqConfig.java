package online.pandm.demo.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DelayRabbitMqConfig
 * @Description: 通用延时队列 需要先安装rabbitmq_delayed_message_exchange插件
 * @Author: peng lu
 * @Date: 2020/10/21
 * @Version: V1.0
 **/
@Configuration
public class DelayRabbitMqConfig {

    public static final String DELAYED_QUEUE_NAME = "delay.queue.demo.delay.queue";
    public static final String DELAYED_EXCHANGE_NAME = "delay.queue.demo.delay.exchange";
    public static final String DELAYED_ROUTING_KEY = "delay.queue.demo.delay.routingkey";

    @Bean
    public Queue immediateQueue(){
        return new Queue(DELAYED_QUEUE_NAME);
    }

    @Bean
    public CustomExchange customExchange() {
        Map<String, Object> args = new HashMap<>(1);
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingNotify(@Qualifier("immediateQueue") Queue queue,
                                 @Qualifier("customExchange") CustomExchange customExchange) {
        return BindingBuilder.bind(queue).to(customExchange).with(DELAYED_ROUTING_KEY).noargs();
    }

}
