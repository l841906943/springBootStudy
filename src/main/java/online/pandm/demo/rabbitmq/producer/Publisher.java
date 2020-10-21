package online.pandm.demo.rabbitmq.producer;

import online.pandm.demo.rabbitmq.pojo.Mail;

/**
 * @InterfaceName: Publisher
 * @Description: TODO
 * @Author: Lenovo
 * @Date: 2020/9/6
 * @Version: V1.0
 **/
public interface Publisher {

    /**
     * 发送广播
     * @Description: 发送广播
     * @MethodName: publishMail
     * @param mail
     * @Result: void
     * @Author: Peng Lu
     * @Data: 2020/9/6 11:43
     */
    void publishMail(Mail mail);

    /**
     * 发送智联消息
     * @MethodName: senddirectMail
     * @Description: TODO
     * @param mail
     * @param routingkey
     * @Result: void
     * @Author: Peng Lu
     * @Data: 2020/9/6 11:46
     */
    void sendDirectMail(Mail mail, String routingkey);

    /**
     * 发送topic类消息
     * @Description: TODO
     * @MethodName: sendtopicMail
     * @param mail
     * @param routingkey
     * @Result: void
     * @Author: Peng Lu
     * @Data: 2020/9/6 11:49
     */
    void sendTopicMail(Mail mail, String routingkey);

}
