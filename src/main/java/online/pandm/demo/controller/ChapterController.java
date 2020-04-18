package online.pandm.demo.controller;

import online.pandm.demo.annotation.testTwo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lenovo
 */
@Controller
@EnableAutoConfiguration
public class ChapterController {

    private static final Logger logger = LoggerFactory.getLogger(ChapterController.class);

    @RequestMapping("/test")
    @ResponseBody
    public Map<String,String> test(@Valid @RequestBody User user, BindingResult bindingResult){

        Map<String,String> map = new HashMap<String,String>(16);

        logger.info(user.toString());

        if (bindingResult.hasErrors()){
            FieldError error = (FieldError) bindingResult.getAllErrors().get(0);
            map.put("code","400");
            map.put("message",error.getDefaultMessage());
            return map;
        }
        map.put("key","value");
        return map;
    }

}
