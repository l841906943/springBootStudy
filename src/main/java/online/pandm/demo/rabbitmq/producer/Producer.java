package online.pandm.demo.rabbitmq.producer;

import online.pandm.demo.rabbitmq.pojo.Mail;

/**
 * @InterfaceName: Produce
 * @Description: TODO
 * @Author: Lenovo
 * @Date: 2020/9/6
 * @Version: V1.0
 **/
public interface Producer {
    /**
     * @MethodName: sendMail
     * @Description: 发送消息
     * @param queue 队列名称
     * @param mail  消息
     * @Result: void
     * @Author: Peng Lu
     * @Data: 2020/9/6 11:32
     */
    void sendMail(String queue, Mail mail);

    /**
     *
     * @description 延时发送
     * @methodName sendDelayMsg
     * @param msg
     * @param delayTime
     * @result void
     * @author peng lu
     * @date 2020/10/21 10:52
     */
    void sendDelayMsg(String msg, Integer delayTime);
}
