package online.pandm.demo.rabbitmq.exchange;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: ProducerConsumerConfig
 * @Description: 生产者消费者模式的配置,包括一个队列和两个对应的消费者
 * @Author: Lenovo
 * @Date: 2020/9/6
 * @Version: V1.0
 **/
@Configuration
public class ProducerConsumerConfig {

    @Bean
    public Queue myQueue() {
        Queue queue = new Queue("myqueue");
        return queue;
    }

}
