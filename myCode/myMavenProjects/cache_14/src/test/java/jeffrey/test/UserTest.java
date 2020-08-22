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
import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 */
public class UserTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;
    private SqlSessionFactory factory;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy() throws Exception {
        //提交事务
        // sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testFistLevelCache() {
        User user_1 = userDao.findById(41);
        System.out.println(user_1);

        /*sqlSession.close();
        // 再次获取sqlSession对象
        sqlSession = factory.openSession();*/

        // 此方法也可以清空缓存
        sqlSession.clearCache();

        userDao = sqlSession.getMapper(IUserDao.class);


        User user_2 = userDao.findById(41);
        System.out.println(user_2);

        System.out.println(user_1 == user_2);
    }

    /**
     * 测试缓存的同步
     */
    @Test
    public void testClearCache() {
        // 根据id查询用户
        User user_1 = userDao.findById(41);
        System.out.println(user_1);

        // 更新用户信息
        user_1.setUsername("update_user_clear_cache");
        user_1.setAddress("深圳市");
        userDao.updateUser(user_1);

        // 再次查询id为41的用户
        User user_2 = userDao.findById(41);
        System.out.println(user_2);

        System.out.println(user_1 == user_2);
    }


}
