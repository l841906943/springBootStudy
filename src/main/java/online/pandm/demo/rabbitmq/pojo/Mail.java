package online.pandm.demo.rabbitmq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: Mail
 * @Description: TODO
 * @Author: Lenovo
 * @Date: 2020/9/6
 * @Version: V1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Mail implements Serializable {

    private static final long serialVersionUID = -8140693840257585779L;
    private String mailId;
    private String country;
    private Double weight;

}
