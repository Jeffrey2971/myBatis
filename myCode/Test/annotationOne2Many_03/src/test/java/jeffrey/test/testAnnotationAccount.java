package jeffrey.test;

import jeffrey.dao.IAccountDao;
import jeffrey.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class testAnnotationAccount {

    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IAccountDao accountDao;
    private InputStream in;

    @Before
    public void init() throws Exception {
        // 创建配置文件字节输入流
        in = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 通过SqlSessionFactoryBuild创建SQLSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        // 通过SqlSessionFactory创建sqlSession
        sqlSession = factory.openSession();
        // 通过sqlSession获取dao代理对象
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    @After
    public void destroy() throws Exception {
        // 提交事物
        sqlSession.commit();
        // 归还sqlSession
        sqlSession.close();
        // 关闭字节输入流
        in.close();
    }

    /**
     * 获取所有账户和对应的用户信息
     */
    @Test
    public void testFindAll(){
        List<Account> users = accountDao.findAll();
        for (Account user : users) {
            System.out.println("---每个账户的信息---");
            System.out.println(user);
            System.out.println(user.getUser());
        }
    }
}
