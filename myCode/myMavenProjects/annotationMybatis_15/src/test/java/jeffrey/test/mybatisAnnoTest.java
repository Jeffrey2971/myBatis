package jeffrey.test;

import jeffrey.dao.IUserDao;
import jeffrey.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class mybatisAnnoTest {
    public static void main(String[] args) throws Exception{
        /**
         * 测试基于注解的mybatis使用
         */

        // 获取字节输入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 根据字节输入流构建sqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 根据SqlSessionFactory生产一个SqlSession
        SqlSession sqlSession = factory.openSession();
        // 使用sqlSession获取dao的代理对昂
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        // 执行dao的方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        // 释放资源
        sqlSession.close();
        in.close();
    }
}
