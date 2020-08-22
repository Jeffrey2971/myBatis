package jeffrey.dao;

import jeffrey.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所用用户
     * @return List
     */
    List<User> findAll();

    /**
     * 保存方法
     * @param user
     */
    void saveUser(User user);
}
