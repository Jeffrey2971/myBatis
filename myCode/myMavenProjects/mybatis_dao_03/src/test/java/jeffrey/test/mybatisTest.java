package jeffrey.test;


import jeffrey.dao.IUserDao;
import jeffrey.dao.impl.UserDaoImpl;
import jeffrey.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * mybatis案例
 */

public class mybatisTest {
    public static void main(String[] args) throws Exception{
        // 1、读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2、创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        // 4、使用工厂创建Dao的对象
        IUserDao userDao = new UserDaoImpl(factory);
        // 5、使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        // 6、释放资源
        is.close();
    }
}
