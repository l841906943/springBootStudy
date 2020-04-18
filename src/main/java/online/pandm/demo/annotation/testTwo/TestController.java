package online.pandm.demo.annotation.testTwo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("testCheckData")
    public void testCheckData( User user){

    }

}
