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
import java.util.Date;
import java.util.List;

public class AnnotationCRUDTest {
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;
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
        userDao = sqlSession.getMapper(IUserDao.class);
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
     * 查找一个
     */
    @Test
    public void testFindByOne() {
        User user_1 = userDao.findByOne(48);
        System.out.println(user_1);


        User user_2 = userDao.findByOne(48);
        System.out.println(user_2);

        System.out.println(user_1 == user_2);
    }

    /**
     * 查找所有
     */
    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("---每个用户的信息---");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    /**
     * 根据用户名模糊查询
     */
    /*@Test
    public void testFindByName() {
        // List<User> userByName = userDao.findUserByName("%小%");
        List<User> userByName = userDao.findUserByName("小");
        for (User user : userByName) {
            System.out.println(user);
        }
    }*/


}
