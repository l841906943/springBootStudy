package online.pandm.demo.controller;

import lombok.extern.slf4j.Slf4j;
import online.pandm.demo.rabbitmq.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName: DelayMessageTestController
 * @Description: TODO
 * @Author: peng lu
 * @Date: 2020/10/21
 * @Version: V1.0
 **/
@Slf4j
@RestController("mqTest")
public class DelayMessageTestController {

    @Autowired
    public Producer producer;

    @RequestMapping("delayMsg2")
    public void delayMsg2(String msg, Integer delayTime) {
        log.info("当前时间：{},收到请求，msg:{},delayTime:{}", new Date(), msg, delayTime);
        producer.sendDelayMsg(msg, delayTime);
    }
}

