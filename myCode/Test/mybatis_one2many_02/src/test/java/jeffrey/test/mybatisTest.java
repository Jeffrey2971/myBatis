package jeffrey.test;

import jeffrey.dao.IAccountDao;
import jeffrey.dao.IUserDao;
import jeffrey.domain.Account;
import jeffrey.domain.AccountUser;
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

public class mybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;
    private IAccountDao accountDao;

    @Before
    public void init() throws Exception{
        // 1、读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2、根据SqlSessionFactoryBuilder创建SQLSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3、根据SqlSessionFactory获取SqlSession
        sqlSession = factory.openSession(true); // 配置自动提交事物
        // 4、获取代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    @After
    public void destroy() throws Exception{
        // 5、释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有用户
     */
    @Test
    public void testUserFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("用户ID：" + user.getId() + "用户姓名：" + user.getUsername());
        }


    }


    /**
     * 根据id查询账户
     */
    @Test
    public void testUserById(){
        List<User> findByOne = userDao.findByOne(42);
        for (User user : findByOne) {
            System.out.println(user);
        }
    }

    /**
     * 测试查询所有账户
     */
    @Test
    public void testAccountFindAll(){
        List<Account> accountAll = accountDao.findUserAndAccount();
        for (Account account : accountAll) {
            System.out.println(account);

        }
    }


    /**
     * 测试查询所有账户同时包含用户名称和地址
     */
    @Test
    public void testAccountUserFindAll(){
        List<AccountUser> aus = accountDao.findAllAccount();
        for (AccountUser au : aus) {

            System.out.println(au);
            System.out.println(au.getUser());
        }
    }

    /**
     *
     */
    @Test
    public void test1(){
        List<Account> account = accountDao.findAccountAll();
        for (Account account1 : account) {
            System.out.println(account1);
            System.out.println(account1.getUser());
        }
    }


}
