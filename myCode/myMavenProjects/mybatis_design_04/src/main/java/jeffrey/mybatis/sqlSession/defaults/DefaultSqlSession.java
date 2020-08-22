package jeffrey.mybatis.sqlSession.defaults;


import jeffrey.mybatis.cfg.Configuration;
import jeffrey.mybatis.sqlSession.SqlSession;
import jeffrey.mybatis.sqlSession.proxy.MapperProxy;
import jeffrey.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SqlSession接口的实现类
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;
    public DefaultSqlSession(Configuration cfg){
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }
    /**
     * 用于创建代理对象
     * @param daoInterfaceClass dao
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),connection));
    }

    /**
     * 用于释放资源
     */
    public void close() {
        if (connection != null){
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
