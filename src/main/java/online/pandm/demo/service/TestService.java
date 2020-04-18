package online.pandm.demo.service;

/**
 * @author Lenovo
 */
public interface TestService {

    /**
     *
     * @param name
     * @param password
     */
    int create(String name,String password);

    /**
     * 根据用户名删除用户
     * @param name
     */
    void deleteByName(String name);

    /**
     * 获取用户总数
     * @return
     */
    Integer getUsersCount();

    /**
     * 删除所有用户
     */
    void deleteAllUsers();

}
