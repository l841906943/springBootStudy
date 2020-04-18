package online.pandm.demo.service.impl;

import online.pandm.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    /**
     * Spring的JdbcTemplate是自动配置的，可直接使用
     */
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(String name, String password) {
        int flag = jdbcTemplate.update("insert into t_user(USERNAME, PASSWORD) values(?, ?)", name, password);
        return flag;
    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete from t_user where USERNAME = ?", name);
    }

    @Override
    public Integer getUsersCount() {
        return jdbcTemplate.queryForObject("select count(1) from t_user", Integer.class);
    }

    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("delete from t_user");
    }

}
