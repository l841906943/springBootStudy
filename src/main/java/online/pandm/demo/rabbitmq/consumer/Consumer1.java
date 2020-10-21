package online.pandm.demo.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import online.pandm.demo.rabbitmq.DelayRabbitMqConfig;
import online.pandm.demo.rabbitmq.pojo.Mail;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @ClassName: consumer1
 * @Description: 消费者
 * @Author: Lenovo
 * @Date: 2020/9/6
 * @Version: V1.0
 **/
@Component
@Slf4j
public class Consumer1 {

    /**
     *
     * @Description: 消费者1
     * @MethodName: revice1
     * @param
     * @Result: void
     * @Author: Peng Lu
     * @Data: 2020/9/6 12:35
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "goods"))
    public void receive1(Mail mail, Message message, Channel channel) throws InterruptedException, IOException {
        try {
            log.info("========== message1: " + mail.toString());
            //确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.error("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                log.error("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

    /**
     *
     * @description 消费者2
     * @methodName receive2
     * @param mail
     * @param message
     * @param channel
     * @result void
     * @author peng lu
     * @date 2020/10/21 10:56
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "goods"))
    public void receive2(Mail mail, Message message, Channel channel) throws InterruptedException, IOException {
        try {
            log.info("========== message2: " + mail.toString());
            //确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.error("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                log.error("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

    /**
     *
     * @description 消费者3
     * @methodName receive3
     * @param mail
     * @param message
     * @param channel
     * @result void
     * @author peng lu
     * @date 2020/10/21 10:56
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "goods"))
    public void receive3(Mail mail, Message message, Channel channel) throws InterruptedException, IOException {
        try {
            log.info("========== message3: " + mail.toString());
            //确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.error("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                log.error("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

    /**
     *
     * @description 消费延时消息
     * @methodName receiveD
     * @param message
     * @param channel
     * @result void
     * @author peng lu
     * @date 2020/10/21 10:57
     */
    @RabbitListener(queues = DelayRabbitMqConfig.DELAYED_QUEUE_NAME)
    public void receiveD(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},延时队列收到消息：{}", new Date().toString(), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
