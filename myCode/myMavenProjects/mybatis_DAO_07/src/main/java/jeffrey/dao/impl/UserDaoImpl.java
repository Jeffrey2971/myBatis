package jeffrey.dao.impl;

import jeffrey.dao.IUserDao;
import jeffrey.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {


    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    public List<User> findAll() {
        // 根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 调用SqlSession中的方法，查询列表
        List<User> users = session.selectList("jeffrey.dao.IUserDao.findAll");// 参数就是能获取配置信息的key
        // 释放资源
        session.close();
        return users;
    }

    public void saveUser(User user) {
        // 根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 调用方法实现保存
        session.insert("jeffrey.dao.IUserDao.saveUser", user);
        // 提交事物
        session.commit();
        // 释放资源
        session.close();


    }

    public void updateUser(User user) {
        // 根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 调用方法实现更新
        session.update("jeffrey.dao.IUserDao.updateUser", user);
        // 提交事物
        session.commit();
        // 释放资源
        session.close();
    }

    public void deleteUser(Integer userId) {
        // 根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 调用方法实现删除
        session.delete("jeffrey.dao.IUserDao.deleteUser", userId);
        // 提交事物
        session.commit();
        // 释放资源
        session.close();
    }

    public User findUserById(Integer userId) {
        // 根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 调用SqlSession中的方法，查询列表
        User user = session.selectOne("jeffrey.dao.IUserDao.findUserById", userId);// 参数就是能获取配置信息的key
        // 释放资源
        session.close();
        return user;
    }

    public List<User> findByName(String username) {
        // 根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 调用SqlSession中的方法，查询列表
        List<User> users = session.selectList("jeffrey.dao.IUserDao.findByName", username);// 参数就是能获取配置信息的key
        // 释放资源
        session.close();
        return users;
    }

    public int findTotal() {

        // 根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 调用SqlSession中的方法，查询列表
        Integer total = session.selectOne("jeffrey.dao.IUserDao.findTotal");// 参数就是能获取配置信息的key
        // 释放资源
        session.close();
        return total;
    }
}
