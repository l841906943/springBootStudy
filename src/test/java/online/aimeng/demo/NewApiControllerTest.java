package online.aimeng.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import online.aimeng.demo.annotation.testTwo.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest(classes=DemoApplication.class)
public class NewApiControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(NewApiControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testEquipmentTypes() throws Exception {
        User user = new User();
        user.setName("卢朋");
        user.setSex("男");
        user.setBirth("1995-0506");
        String userJson = JSONObject.toJSONString(user);
        logger.info(userJson);
        MvcResult authResult = null;
        ResultActions resultActions = mockMvc.perform(post("/test")//使用post方式来调用接口。
                .contentType(MediaType.APPLICATION_JSON)//请求参数的类型
                .content(userJson)//请求的参数（可多个）
        );
        authResult = resultActions.andReturn();
        MockHttpServletResponse mockHttpServletResponse = authResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("UTF-8");
        //获取数据
        JSONObject jsonObject = JSONObject.parseObject(mockHttpServletResponse.getContentAsString());
        logger.info("返回数据："+jsonObject);

    }

}
