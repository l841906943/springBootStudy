package online.pandm.demo.rabbitmq.producer.impl;


import online.pandm.demo.rabbitmq.DelayRabbitMqConfig;
import online.pandm.demo.rabbitmq.pojo.Mail;
import online.pandm.demo.rabbitmq.producer.Producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: ProducerImpl
 * @Description: 定义发送接口的实现
 * @Author: Lenovo
 * @Date: 2020/9/6
 * @Version: V1.0
 **/
@Service("producer")
public class ProducerImpl implements Producer {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Override
    public void sendMail(String queue, Mail mail) {
        rabbitTemplate.convertAndSend(queue,mail);
    }

    @Override
    public void sendDelayMsg(String msg, Integer delayTime) {
        rabbitTemplate.convertAndSend(DelayRabbitMqConfig.DELAYED_EXCHANGE_NAME, DelayRabbitMqConfig.DELAYED_ROUTING_KEY, msg, a ->{
            a.getMessageProperties().setDelay(delayTime);
            return a;
        });
    }

}