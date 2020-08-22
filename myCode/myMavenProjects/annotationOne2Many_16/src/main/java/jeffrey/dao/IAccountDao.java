package jeffrey.dao;

import jeffrey.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有账户并且获取每个账户所属的用户信息
     * @return
     */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            @Result(property = "user", column = "uid",one=@One(select="jeffrey.dao.IUserDao.findByOne", fetchType= FetchType.EAGER))
    })
    List<Account> findAll();

    /**
     * 根据用户id查询账户信息
     * @param uid
     * @return
     */
    @Select("select * from account where uid = #{uid}")
    List<Account> findAccountByUid(Integer uid);

}
