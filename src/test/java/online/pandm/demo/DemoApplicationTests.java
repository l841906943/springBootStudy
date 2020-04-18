package online.pandm.demo;

import online.pandm.demo.entity.User;
import online.pandm.demo.service.TestService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    User user;

    @Test
    public void contextLoads() {
        System.out.println(user);
        System.out.println("测试方法");

    }

    /**
     * 测试数据库连接
     */
    @Autowired
    private TestService testService;

    @Test
    public void testJdbc() {
        /*
         *  插入5个用户
         */
        int flag = testService.create("goods_lp", "1");
        System.out.println(flag);
        testService.create("b", "2");
        testService.create("c", "3");
        testService.create("d", "4");
        testService.create("e", "5");

        /*
         *  查数据库，应该有5个用户
         */
        Assert.assertEquals(5, testService.getUsersCount().intValue());

        /*
         *  删除两个用户
         */
        testService.deleteByName("a");
        testService.deleteByName("e");

        /*
         *  查数据库，应该有5个用户
         */
        Assert.assertEquals(3, testService.getUsersCount().intValue());

    }

    @Test
    public void check() {

    }


}