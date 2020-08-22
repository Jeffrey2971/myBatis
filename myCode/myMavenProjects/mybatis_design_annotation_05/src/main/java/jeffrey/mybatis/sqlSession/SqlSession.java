package jeffrey.mybatis.sqlSession;

/**
 * 自定义mybatis中和数据库交互的核心类，它里面可以创建Dao的接口对象
 */
public interface SqlSession {
    /**
     * 根据参数创建一个代理对象
     *
     * @param daoInterfaceClass dao
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 释放资源
     */
    void close();
}