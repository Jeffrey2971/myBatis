package jeffrey.dao;

import jeffrey.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {
    /**
     * 查找所有账户和对应的用户信息
     */

    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            @Result(column = "uid", property = "user", one=@One(select = "jeffrey.dao.IUserDao.findById", fetchType= FetchType.EAGER))
    })

    List<Account> findAll();
}