package online.pandm.demo.rabbitmq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RabbitConfig
 * @Description: 连接rabbitMQ的基本配置(修改为自动确认模式)
 * @Author: Lenovo
 * @Date: 2020/9/6
 * @Version: V1.0
 **/
@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private String port;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    /**
     * @MethodName: connectionFactory
     * @Description: 获取mq连接
     * @Result: org.springframework.amqp.rabbit.connection.ConnectionFactory
     * @Author: Peng Lu
     * @Data: 2020/9/6 11:16
     */
    //@Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPort(Integer.valueOf(port));
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    //@Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    //@Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    /**
     * @MethodName: rabbitListenerContainerFactory
     * @Description: 配置消费者监听的容器
     * @Author: Peng Lu
     * @Data: 2020/9/6 11:16
     */
    //@Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        //设置确认模式手工确认
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

}
