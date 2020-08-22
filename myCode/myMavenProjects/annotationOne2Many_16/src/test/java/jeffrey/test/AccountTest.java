package jeffrey.test;

import jeffrey.dao.IAccountDao;
import jeffrey.dao.IUserDao;
import jeffrey.domain.Account;
import jeffrey.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountTest {
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IAccountDao accountDao;
    private InputStream in;

    /**
     * 初始化
     *
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    /**
     * 销毁
     *
     * @throws Exception
     */
    @After
    public void destroy() throws Exception {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }


    /**
     * 查找所有
     */
    @Test
    public void testFindAll() {
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println("---每个账户的信息---");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }


}
