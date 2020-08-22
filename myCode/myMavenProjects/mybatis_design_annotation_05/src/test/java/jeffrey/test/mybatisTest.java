package jeffrey.test;



import jeffrey.dao.IUserDao;
import jeffrey.domain.User;
import jeffrey.mybatis.io.Resources;
import jeffrey.mybatis.sqlSession.SqlSession;
import jeffrey.mybatis.sqlSession.SqlSessionFactory;
import jeffrey.mybatis.sqlSession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * mybatis案例
 */

public class mybatisTest {
    public static void main(String[] args) throws Exception{
        // 1、读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2、创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3、使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        // 4、使用SqlSession创建Dao接口的动态代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        // 5、使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        // 6、释放资源
        session.close();
        in.close();
    }
}
