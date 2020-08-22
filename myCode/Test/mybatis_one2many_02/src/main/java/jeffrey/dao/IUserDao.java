package jeffrey.dao;

import jeffrey.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有用户，同时获取到用户下所有账户的信息
     * @return
     */
    abstract List<User> findAll();
    abstract List<User> findByOne(Integer uid);

}
