package jeffrey.test;

import jeffrey.dao.IRoleDao;
import jeffrey.dao.IUserDao;
import jeffrey.domain.Role;
import jeffrey.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 *
 * 测试mybatis的crud操作
 */
public class RouteTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IRoleDao iRoleDao;



    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        iRoleDao = sqlSession.getMapper(IRoleDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        sqlSession.close();
        in.close();
    }

    /**
     * 查询所有角色信息
     */
    @Test
    public void testFindAllRole(){
        List<Role> roles = iRoleDao.findAll();
        for (Role role : roles) {
            System.out.println("---每个角色的信息---");
            System.out.println(role);

        }
    }


}
