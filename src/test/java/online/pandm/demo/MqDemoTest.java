package online.pandm.demo;

import lombok.extern.slf4j.Slf4j;
import online.pandm.demo.rabbitmq.pojo.Mail;
import online.pandm.demo.rabbitmq.producer.impl.ProducerImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName: MqDemoTest
 * @Description: TODO
 * @Author: Lenovo
 * @Date: 2020/9/6
 * @Version: V1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MqDemoTest {

    @Autowired
    ProducerImpl producerImpl;

    @Test
    public void sendMsg(){
        for (int i = 0; i < 100; i++) {
            Mail mail = new Mail();
            mail.setMailId("goods");
            mail.setCountry("goods");
            mail.setWeight((double) i);
            //log.info(mail.toString());
            producerImpl.sendMail("goods",mail);
        }
    }


}
