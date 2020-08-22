package jeffrey.mybatis.sqlSession;

public interface SqlSessionFactory {

    /**
     * 用于打开新的SqlSession对象
     * @return
     */
    SqlSession openSession();
}
