package jeffrey.dao;

import jeffrey.domain.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {

    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "address", property = "address")
    })

    @Select("select * from user")
    List<User> findAll();


    @ResultMap("userMap")
    @Select("select * from user where id=#{id}")
    User findById(Integer id);


}
