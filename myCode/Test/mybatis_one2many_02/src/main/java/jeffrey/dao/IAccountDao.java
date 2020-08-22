package jeffrey.dao;

import jeffrey.domain.Account;
import jeffrey.domain.AccountUser;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有账户信息
     * @return
     */
    abstract List<Account> findAccountAll();

    /**
     * 查询所有账户信息同时当前账户所属用户信息
     * @return
     */
    abstract List<Account> findUserAndAccount();

    /**
     * 查询所有账户并且带有用户名称和地址信息
     */
    List<AccountUser> findAllAccount();
}
