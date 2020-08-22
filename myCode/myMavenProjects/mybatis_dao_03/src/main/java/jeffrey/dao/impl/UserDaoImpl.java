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
        // 使用工厂创建SqlSession创建对象
        SqlSession session = factory.openSession();
        // 使用session执行查询所有方法
        List<User> users = session.selectList("jeffrey.dao.IUserDao.findAll");
        session.close();
        return users;

    }
}
