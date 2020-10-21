package online.pandm.demo.rabbitmq.producer.impl;

/**
 * @ClassName: PublisherImpl
 * @Description: TODO
 * @Author: Lenovo
 * @Date: 2020/9/6
 * @Version: V1.0
 **/

import online.pandm.demo.rabbitmq.DelayRabbitMqConfig;
import online.pandm.demo.rabbitmq.pojo.Mail;
import online.pandm.demo.rabbitmq.producer.Publisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("publisher")
public class PublisherImpl implements Publisher {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void publishMail(Mail mail) {
        rabbitTemplate.convertAndSend("fanout", "", mail);
    }

    @Override
    public void sendDirectMail(Mail mail, String routingkey) {
        rabbitTemplate.convertAndSend("direct", routingkey, mail);
    }

    @Override
    public void sendTopicMail(Mail mail, String routingkey) {
        rabbitTemplate.convertAndSend("mytopic", routingkey, mail);
    }

}
