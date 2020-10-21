package online.pandm.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description: TODO
 * @Author Lenovo
 * @Date 2020/8/6
 * @Version V1.0
 **/
@RestController
public class HelloController {

    private static Logger logger = LoggerFactory.getLogger( HelloController.class );

    @Value("${spring.rabbitmq.username}")
    String name;

    @Value("${server.port}")
    String port;

    @GetMapping("hi")
    public String hi() {

        logger.debug( "debug log..." );
        logger.info( "info log..." );
        logger.warn( "warn log..." );

        return "hi " + name + " ,i am from port:" + port;
    }
}
