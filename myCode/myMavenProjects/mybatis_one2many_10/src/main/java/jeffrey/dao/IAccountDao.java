package jeffrey.dao;

import jeffrey.domain.Account;
import jeffrey.domain.AccountUser;

import java.util.List;

public interface IAccountDao {

    /**
     * 查所有账户，同时获取当前账户的所属用户信息
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有账户，并且带有用户名名称和地址信息
     * @return
     */
    List<AccountUser> findAllAccount();
}
