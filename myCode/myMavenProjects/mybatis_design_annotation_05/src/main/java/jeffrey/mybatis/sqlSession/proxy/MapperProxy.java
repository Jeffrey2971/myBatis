package jeffrey.mybatis.sqlSession.proxy;

import jeffrey.mybatis.cfg.Mapper;
import jeffrey.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy implements InvocationHandler {
    // map的key是全限定类名+方法名
    private Map<String , Mapper> mappers;
    private Connection conn;
    public MapperProxy(Map<String ,Mapper> mappers, Connection conn){
        this.mappers = mappers;
		this.conn = conn;
    }
    /**
     * 对方法进行增强，调用selectList方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取方法名
        String methodName = method.getName();
        // 获取方法所在类的名称
        String className = method.getDeclaringClass().getName();
        // 组合key
        String key = className + "." + methodName;
        // 获取mappers中的mapper对象
        Mapper mapper = mappers.get(key);
        // 判断是否有mapper
        if (mapper == null){
            throw new IllegalArgumentException("传入的参数有误");
        }
        // 调用工具类执行查询所有

        return new Executor().selectList(mapper, conn);
    }
}
