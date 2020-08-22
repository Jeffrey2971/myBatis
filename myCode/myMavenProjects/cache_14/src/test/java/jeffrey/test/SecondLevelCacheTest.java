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

public class SecondLevelCacheTest {

    private InputStream in;
    private SqlSessionFactory factory;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    @After//用于在测试方法执行之后执行
    public void destroy() throws Exception {
        in.close();
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testFistLevelCache() {
        SqlSession sqlSession_1 = factory.openSession();
        IUserDao dao_1 = sqlSession_1.getMapper(IUserDao.class);
        User user_1 = dao_1.findById(41);

        sqlSession_1.close(); // 一级缓存消失
        System.out.println(user_1);

        SqlSession sqlSession_2 = factory.openSession();
        IUserDao dao_2 = sqlSession_2.getMapper(IUserDao.class);
        User user_2 = dao_2.findById(41);
        System.out.println(user_2);

        sqlSession_2.close();

        System.out.println(user_1 == user_2);
    }



}
