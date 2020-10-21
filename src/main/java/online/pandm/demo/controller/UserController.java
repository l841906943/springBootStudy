package online.pandm.demo.controller;

import online.pandm.demo.entity.BigUser;
import online.pandm.demo.entity.User;
import online.pandm.demo.service.UserService;
import online.pandm.demo.util.IsNullConvertZeroUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * (User)表控制层
 *
 * @author jlcwlp
 * @since 2020-05-02 15:05:46
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }

    /**
     * 插入多条数据
     *
     * @return 单条数据
     */
    @GetMapping("insertUsers")
    public int insertUsers() {
        return this.userService.insertUsers();
    }


    @GetMapping("testGetUser")
    @ResponseBody
    public User testGetUser() {
        User user = new User();
        user.setBirthday(new Date());
        user.setSmileBirthday(new Date());
        user.setBigUser(new BigUser());
        user.setMoney(new BigDecimal("12123.1232132111111"));
        //IsNullConvertZeroUtil.checkIsNull(user);
        return user;
    }

}