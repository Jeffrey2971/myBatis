package jeffrey.dao;

import jeffrey.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@CacheNamespace(blocking = true)
public interface IUserDao {

    /**
     * 查询所有
     *
     * @return 在mybatis中针对CRUD一共有四个注解
     * @SELECT @INSERT @UPDATE @DELETE
     */

    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "userId"),
            @Result(column = "username", property = "userName"),
            @Result(column = "address", property = "userAddress"),
            @Result(column = "sex", property = "userSex"),
            @Result(column = "birthday", property = "userBirthday"),
            @Result(column = "id", property = "accounts", many = @Many(select = "jeffrey.dao.IAccountDao.findAccountByUid", fetchType = FetchType.LAZY))
    })

    @Select("select * from user")
    List<User> findAll();

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @ResultMap("userMap")
    @Select("select * from user where id=#{id}")
    User findByOne(Integer id);

    /**
     * 根据用户名称模糊查询
     *
     * @param username
     * @return
     */
//    @Select("select * from user where username like #{username}")
//    @ResultMap("userMap")
//    @Select("select * from user where username like '%${value}%'")
//    List<User> findUserByName(String username);


}

