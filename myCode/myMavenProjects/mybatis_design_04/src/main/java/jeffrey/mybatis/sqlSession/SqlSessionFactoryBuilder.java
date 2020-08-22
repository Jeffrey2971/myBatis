package jeffrey.mybatis.sqlSession;

import jeffrey.mybatis.cfg.Configuration;
import jeffrey.mybatis.sqlSession.defaults.DefaultSqlSessionFactory;
import jeffrey.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 用于创建sqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {
    /**
     * 根据参数的字节输入流构建一个SqlSessionFactory工厂
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }

}
