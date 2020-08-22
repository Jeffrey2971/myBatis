package jeffrey.test;

import jeffrey.dao.IUserDao;
import jeffrey.domain.QueryVo;
import jeffrey.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 测试mybatis的CRUD
 */
public class mybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws Exception {

        // 读取配置文件生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 获取SQLSession对象
        sqlSession = factory.openSession();
        // 获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);

    }

    @After
    public void destroy() throws Exception {
        // 提交事物
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
        in.close();

    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUserName("zhanglifen");
        user.setUserAddress("中国");
        user.setUserSex("女");
        user.setUserBirthday(new Date());
        System.out.println("保存操作之前：" + user);

        userDao.saveUser(user);

        System.out.println("保存操作之后：" + user);

    }

    /**
     * 测试更新操作
     */
    @Test
    public void updateTest (){
        User user = new User();
        user.setUserId(50);
        user.setUserName("mable");
        user.setUserAddress("马来西亚");
        user.setUserSex("女");
        user.setUserBirthday(new Date());

        userDao.updateUser(user);
    }

    /**
     * 测试删除操作
     */
    @Test
    public void deleteTest (){
        // 删除方法
        userDao.deleteUser(51);
    }

    /**
     * 测试查询一个
     */
    @Test
    public void findOneTest (){
        User user = userDao.findUserById(50);
        if (user == null){
            throw new RuntimeException("没有查到任何对象");
        }
        System.out.println(user);
    }

    /**
     * 测试模糊查询
     */
    @Test
    public void findByNameTest (){
        List<User> users = userDao.findByName("小");
        if (users == null){
            throw new RuntimeException("没有查到任何信息");
        }
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 查询用户总数
     */
    @Test
    public void findTotalCountTest (){
        int total = userDao.findTotal();
        System.out.println(total);
    }

    /**
     * 测试使用QueryVo作为条件查询
     */
    @Test
    public void findByVoTest (){
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUserName("%小%");
        queryVo.setUser(user);

        List<User> users = userDao.findUserByVo(queryVo);
        if (users == null){
            throw new RuntimeException("没有查到任何信息");
        }
        for (User u : users) {
            System.out.println(u);
        }
    }


}
