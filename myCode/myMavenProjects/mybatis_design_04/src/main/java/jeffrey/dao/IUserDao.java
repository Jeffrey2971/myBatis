package jeffrey.dao;


import jeffrey.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有
     * @return 返回一个User对象
     */
    List<User> findAll();
}
