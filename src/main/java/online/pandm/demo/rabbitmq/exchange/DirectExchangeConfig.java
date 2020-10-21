package online.pandm.demo.rabbitmq.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: DirectExchangeConfig
 * @Description: direct直连模式的交换机配置,包括一个direct交换机，两个队列，三根网线binding
 * @Author: Lenovo
 * @Date: 2020/9/6
 * @Version: V1.0
 **/
@Configuration
public class DirectExchangeConfig {

    @Bean
    public DirectExchange directExchange() {
        DirectExchange directExchange = new DirectExchange("direct");
        return directExchange;
    }

    @Bean
    public Queue directQueue1() {
        Queue queue = new Queue("directqueue1");
        return queue;
    }

    @Bean
    public Queue directQueue2() {
        Queue queue = new Queue("directqueue2");
        return queue;
    }

    /**
     * 将交换机和相应队列连起来,并监听orange消息
     */
    @Bean
    public Binding bindingorange() {
        Binding binding = BindingBuilder.bind(directQueue1()).to(directExchange()).with("orange");
        return binding;
    }

    /**
     * 将交换机和相应队列连起来,并监听black消息
     */
    @Bean
    public Binding bindingblack() {
        Binding binding = BindingBuilder.bind(directQueue2()).to(directExchange()).with("black");
        return binding;
    }

    /**
     * 将交换机和相应队列连起来,并监听green消息
     */
    @Bean
    public Binding bindinggreen() {
        Binding binding = BindingBuilder.bind(directQueue2()).to(directExchange()).with("green");
        return binding;
    }
}