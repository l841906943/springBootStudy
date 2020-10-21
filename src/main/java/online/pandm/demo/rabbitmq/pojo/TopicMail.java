package online.pandm.demo.rabbitmq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName: TopicMail
 * @Description: TODO
 * @Author: Lenovo
 * @Date: 2020/9/6
 * @Version: V1.0
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TopicMail extends Mail {
    /**
     * 路由key
     */
    String routingkey;

}
