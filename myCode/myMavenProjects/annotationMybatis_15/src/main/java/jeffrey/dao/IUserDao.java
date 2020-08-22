package jeffrey.dao;

import jeffrey.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有
     * @return
     * 在mybatis中针对CRUD一共有四个注解
     * @SELECT @INSERT @UPDATE @DELETE
     */

    @Select("select * from user")
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    @Insert("insert into user(username, address, sex, birthday)values(#{username}, #{address}, #{sex}, #{birthday})")
    void saveUser(User user);

    /**
     * 更新用户
     */
    @Update("update user set username=#{username}, sex=#{sex}, birthday=#{birthday}, sex=#{sex}, address=#{address} where id=#{id}")
    void updateUser(User user);

    /**
     * 删除用户
     * @param uid
     */
    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer uid);


    /**
     * 查询一个
     * @param uid
     * @return
     */
    @Select("select * from user where id=#{id}")
    User findByOne(Integer uid);

    /**
     * 根据用户名称模糊查询
     * @param username
     * @return
     */
    // @Select("select * from user where username like #{username}")
     @Select("select * from user where username like '%${value}%'")

    List<User> findUserByName(String username);


    /**
     * 查询总用户数量
     * @return
     */
    @Select("select count(*) from user")
    int findTotalUser();


}

