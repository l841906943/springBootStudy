package online.pandm.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lenovo
 */
@SpringBootApplication
@MapperScan("online.pandm.demo.dao")
public class DemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        logger.info("-----------StartProduct------------");
        SpringApplication.run(DemoApplication.class, args);
    }

}
