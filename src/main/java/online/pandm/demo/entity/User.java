package online.pandm.demo.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Lenovo
 * PropertySource : 指定文件注入
 */
@Data
@PropertySource(value = {"classpath:user.properties"})
@Component
@ConfigurationProperties(prefix = "user")
public class User {

    private Long id;
    private String username;
    private String password;
    private Date birthday;
}
