package jeffrey.dao;

import jeffrey.domain.User;

import java.util.List;

/**
 * 用户持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 保存方法
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新方法
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据ID删除用户
     * @param userId
     * @return
     */
    void deleteUser(Integer userId);

    /**
     * 根据ID查询用户信息
     * @param userId
     * @return
     */
    User findUserById(Integer userId);

    /**
     * 根据名称模糊查询用户信息
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();
}
