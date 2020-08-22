package jeffrey.test;

import jeffrey.dao.IUserDao;
import jeffrey.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class mybatisTest {
    @Test
    public void findAllTest() throws Exception{
        // 获取配置文件的字节输入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 获取dao的代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        // 执行查询所有方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

}
