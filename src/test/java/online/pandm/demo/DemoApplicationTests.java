package online.pandm.demo;

import online.pandm.demo.entity.BigUser;
import online.pandm.demo.entity.User;
import online.pandm.demo.service.TestService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(new User());
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
//        BigDecimal bd = new BigDecimal("1");
//        System.out.println(Integer.parseInt(bd.toString())==1);

        List<Long> longs = new ArrayList<Long>();
        longs.add(1000L);
        longs.add(2000L);
        longs.add(3000L);
        longs.add(4000L);
        longs.stream().forEach(System.out::println);
        longs.remove(2000L);
        longs.stream().forEach(System.out::println);
    }

    @Test
    public void copyObject() throws InvocationTargetException, IllegalAccessException {

        User user1 = new User();
        user1.setMoney(BigDecimal.valueOf(1.11));

        BigUser user2 = new BigUser();
        user2.setBirthday(new Date());

        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
        BeanUtils.copyProperties(user1,user2);

        System.out.println(user1);
        System.out.println(user2);


    }

}
