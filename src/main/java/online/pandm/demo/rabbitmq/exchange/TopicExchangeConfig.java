package online.pandm.demo.rabbitmq.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: TopicExchangeConfig
 * @Description: topic交换机模型，需要一个topic交换机，两个队列和三个binding
 * @Author: Lenovo
 * @Date: 2020/9/6
 * @Version: V1.0
 **/
@Configuration
public class TopicExchangeConfig {
    @Bean
    public TopicExchange topicExchange(){
        TopicExchange topicExchange=new TopicExchange("mytopic");
        return topicExchange;
    }

    @Bean
    public Queue topicQueue1() {
        Queue queue=new Queue("topicqueue1");
        return queue;
    }

    @Bean
    public Queue topicQueue2() {
        Queue queue=new Queue("topicqueue2");
        return queue;
    }

    /**
     * 将交换机和相应队列连起来
     * #表示0个或若干个关键字，*表示一个关键字
     */
    @Bean
    public Binding bindingtopic1(){
        Binding binding= BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("*.orange.*");
        return binding;
    }

    /**
     * 将交换机和相应队列连起来
     * #表示0个或若干个关键字，*表示一个关键字
     */
    @Bean
    public Binding bindingtopic2(){
        Binding binding= BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("*.*.rabbit");
        return binding;
    }

    /**
     * 将交换机和相应队列连起来
     * #表示0个或若干个关键字，*表示一个关键字
     */
    @Bean
    public Binding bindingtopic3(){
        Binding binding= BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("lazy.#");
        return binding;
    }
}