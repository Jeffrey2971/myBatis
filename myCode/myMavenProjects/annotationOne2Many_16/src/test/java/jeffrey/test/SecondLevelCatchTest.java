package jeffrey.test;

import jeffrey.dao.IUserDao;
import jeffrey.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class SecondLevelCatchTest {
    private SqlSessionFactory factory;
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

    }

    /**
     * 销毁
     *
     * @throws Exception
     */
    @After
    public void destroy() throws Exception {
        in.close();
    }

    /**
     * 查找一个
     */
    @Test
    public void testFindByOne() {
        SqlSession sqlSession = factory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        User user_1 = userDao.findByOne(48);
        System.out.println(user_1);

        sqlSession.close(); // 释放一级缓存

        SqlSession session_1 = factory.openSession(); // 再次打开session

        IUserDao userDao_2 = session_1.getMapper(IUserDao.class);
        User user_2 = userDao_2.findByOne(48);

        System.out.println(user_2);

        session_1.close();


    }

}
