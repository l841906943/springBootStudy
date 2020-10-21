package online.pandm.demo.service.impl;

import online.pandm.demo.entity.User;
import online.pandm.demo.dao.UserDao;
import online.pandm.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author jlcwlp
 * @since 2020-05-02 15:05:46
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    /**
     * 插入多条数据
     *
     * @return 单条数据
     */
    @Transactional
    public int insertUsers() {

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUsername("LU");
        user.setRealName("LP");
        user.setPassword("JLCW");
        user.setGender(2);
        user.setBirthday(new Date());
        user.setUserType(1);
        users.add(user);
        users.stream().forEach(this.userDao::insert);
        return users.size();
    }

}