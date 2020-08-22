package jeffrey.mybatis.sqlSession.defaults;

import jeffrey.mybatis.cfg.Configuration;
import jeffrey.mybatis.sqlSession.SqlSession;
import jeffrey.mybatis.sqlSession.SqlSessionFactory;

/**
 * SQLSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;
    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }
    /**
     * 用于创建一个新的操作数据库对象
     * @return
     */
    public SqlSession openSession() {

        return new DefaultSqlSession(cfg);
    }
}
