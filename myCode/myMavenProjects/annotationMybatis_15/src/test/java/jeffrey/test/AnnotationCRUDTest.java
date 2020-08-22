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
     * @throws Exception
     */
    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    /**
     * 销毁
     * @throws Exception
     */
    @After
    public void destroy() throws Exception{
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }


    /**
     * 保存用户
     */
    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUsername("张丽芬");
        user.setAddress("中国");
        user.setSex("女");
        user.setBirthday(new Date());
        userDao.saveUser(user);
    }


    /**
     * 更新用户
     */
    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(51);
        user.setUsername("张丽芬");
        user.setAddress("中国");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.updateUser(user);
    }


    /**
     * 删除用户
     */
    @Test
    public void testDeleteUser(){
        userDao.deleteUser(51);
    }

    /**
     * 查找一个
     */
    @Test
    public void testFindByOne(){
        User user = userDao.findByOne(48);
        System.out.println(user);
    }

    /**
     * 查找所有
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 根据用户名模糊查询
     */
    @Test
    public void testFindByName(){
        // List<User> userByName = userDao.findUserByName("%小%");
        List<User> userByName = userDao.findUserByName("小");
        for (User user : userByName) {
            System.out.println(user);
        }
    }

    /**
     * 查询用户总记录数
     */
    @Test
    public void testTotalUser(){
        int totalUser = userDao.findTotalUser();
        System.out.println(totalUser);
    }

}
