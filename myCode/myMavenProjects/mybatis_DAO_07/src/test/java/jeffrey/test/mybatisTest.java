package jeffrey.test;

import jeffrey.dao.IUserDao;
import jeffrey.dao.impl.UserDaoImpl;
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

/**
 * 测试mybatis的CRUD
 */
public class mybatisTest {

    private InputStream in;
    private IUserDao userDao;

    @Before
    public void init() throws Exception {

        // 读取配置文件生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 使用工厂对象创建Dao对象
        userDao = new UserDaoImpl(factory);


    }

    @After
    public void destroy() throws Exception {
//        // 提交事物
//        sqlSession.commit();
//        // 释放资源
//        sqlSession.close();
        // 释放资源
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
        user.setUsername("sm");
        user.setAddress("马来西亚");
        user.setSex("女");
        user.setBirthday(new Date());
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
        user.setId(50);
        user.setUsername("mable");
        user.setAddress("英国");
        user.setSex("女");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }



    /**
     * 测试删除方法
     * */
     @Test
    public void deleteTest (){
        // 删除方法
        userDao.deleteUser(51);
    }

    /**
     * 获取一个
     * */
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
     * */

    @Test
    public void findByNameTest (){
        List<User> users = userDao.findByName("%小%");
        if (users == null){
            throw new RuntimeException("没有查到任何信息");
        }
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 查询用户总记录数
     * */

    @Test
    public void findTotalCountTest (){
        int total = userDao.findTotal();
        System.out.println(total);
    }




}
