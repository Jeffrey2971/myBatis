# MyBatis框架

## 框架
- 什么是框架
    - 它是软件或项目开发中的一套「解决方案」，不同的框架解决不同的问题，比如mybatis解决的是持久层的问题，而SpringMVC解决的是表现层的问题。换而言之，
    框架其实就是某种应用的半成品，简单的说使用别人搭建好的舞台，你来表演。
- 使用框架的好处
    - 框架封装了很多的「细节」，使开发者可以使用极简的方式实现某些功能，大大提高了开发效率

## 三层架构
- 表现层：用于展示数据
- 业务层：处理业务需求业务逻辑
- 持久层：是和数据库进行交互

## 持久层技术解决方案
- JDBC技术
    - Connection
    - PreparedStatement
    - ResultSet
- Spring的JdbcTemplate
    - spring中对Jdbc的简单封装
- Apache的DBUtils
    - 它和Spring的Jdbc类似，也是对Jdbc的简单封装
- 以上的都不是框架，Jdbc是规范，SpringJdbc和DBUtils都只是工具类，对规范的实现，封装



## Day01
- mybatis概述
    - mybatis是一个持久层框架，用java语言所编写，它封装了jdbc操作的很多细节，使开发者只需要关注sql语句本身，无需关注注册驱动，创建连接等繁琐的过程，它使用了ORM思想实现了结果集的封装
    - ORM
        - Object Relational Mapping(对象关系映射)
        - 简单的说就是把数据库表及实体类的属性对应起来，让开发者操作实体类就相当于实现类操作数据库表
- mybatis环境搭建，使用xml配置
    - 在pom.xml中导入所需坐标
        - ```
          <dependency>
              <groupId>org.mybatis</groupId>
              <artifactId>mybatis-spring</artifactId>
              <version>2.0.5</version>
          </dependency>
          
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>5.1.32</version>
          </dependency>
          ```
    - 创建实体类和Dao的接口
        - jeffrey/domain/User
            - ```
              package jeffrey.domain;
              
              import java.io.Serializable;
              import java.util.Date;
              
              public class User implements Serializable {
                  private Integer id;
                  private String username;
                  private Date birthday;
                  private String sex;
                  private String address;
              
                  public Integer getId() {
                      return id;
                  }
              
                  public void setId(Integer id) {
                      this.id = id;
                  }
              
                  public String getUsername() {
                      return username;
                  }
              
                  public void setUsername(String username) {
                      this.username = username;
                  }
              
                  public Date getBirthday() {
                      return birthday;
                  }
              
                  public void setBirthday(Date birthday) {
                      this.birthday = birthday;
                  }
              
                  public String getSex() {
                      return sex;
                  }
              
                  public void setSex(String sex) {
                      this.sex = sex;
                  }
              
                  public String getAddress() {
                      return address;
                  }
              
                  public void setAddress(String address) {
                      this.address = address;
                  }
              
                  @Override
                  public String toString() {
                      return "User{" +
                              "id=" + id +
                              ", username='" + username + '\'' +
                              ", birthday=" + birthday +
                              ", sex='" + sex + '\'' +
                              ", address='" + address + '\'' +
                              '}';
                  }
              }

              ```
        - jeffrey/Dao/IUserDao
            - ```
              package jeffrey.dao;
              
              
              import jeffrey.domain.User;
              
              import java.util.List;
              
              /**
               * 用户的持久层接口
               */
              public interface IUserDao {
                  /**
                   * 查询所有
                   * @return 返回一个User对象
                   */
                  List<User> findAll();
              }

              ```
    - 创建mybatis的主配置文件
        - jeffrey/resources/SqlMapConfig.xml
            - ```
              <!--导入mybatis名称空间-->
              <?xml version="1.0" encoding="UTF-8"?>
              <!DOCTYPE configuration
                      PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
                      "http://mybatis.org/dtd/mybatis-3-config.dtd">
              
              <!--mybatis的主配置文件-->
              <configuration>
              
                  <!--配置环境-->
                  <environments default="mySql">
                      <!--配置mysql的环境-->
                      <environment id="mySql">
                          <!--配置事务类型-->
                          <transactionManager type="JDBC"></transactionManager>
                          <!--配置数据源(连接池)-->
                          <dataSource type="POOLED">
                              <!--配置连接数据库的四个基本信息-->
                              <property name="driver" value="com.mysql.jdbc.Driver"/>
                              <property name="url" value="jdbc:mysql:///mybatis"/>
                              <property name="username" value="root"/>
                              <property name="password" value="Aa66440254"/>
                          </dataSource>
                      </environment>
                  </environments>
              
                  <!--指定映射配置文件的位置，指的是每个dao的独立配置文件-->
                  <mappers>
                      <mapper resource="jeffrey/dao/IUserDao.xml"></mapper>
                  </mappers>
              
              </configuration>
              ```
    - 创建映射配置文件
        - resource/jeffrey/dao/IUserDao.xml
            - ```
              <?xml version="1.0" encoding="UTF-8"?>
              <!DOCTYPE mapper
                      PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
                      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
              <mapper namespace="jeffrey.dao.IUserDao">
                  <!--配置查询所有-->
                  <select id="findAll">
                      select * from user
                  </select>
              </mapper>
              ```
    - 环境搭建注意事项
        - 在mybatis中他把持久层的操作接口名称和映射文件也叫做：Mapper，所以，IUserDao和IUserMapper时一致的
        - 在idea中创建目录时，它和包是不一样的，例如，jeffrey.dao.impl 它是三层结构，而目录在创建时 jeffrey.dao.impl是一级目录，所以在创建时候得一级一级创建
        - mybatis的映射配置文件位置必须和dao接口的包结构相同
        - 映射配置文件的mapper标签namespace属性的取值必须是dao接口的全限定类名
        - 映射配置文件的操作配置，id属性的取值必须是dao接口的方法名
        - 当遵从了以上的条件后，在开发中就无须再写dao的实现类

- mybatis环境搭建，使用注解方式
    - 删除IUserDao.xml映射文件，并在主配置文件中将```<mapper resource="jeffrey/dao/IUserDao.xml"></mapper>```更改为
    ```<mapper class="jeffrey/dao/IUserDao.java"></mapper>```
    - 在Dao接口中添加 @Select("select * from user") 注解
    
- mybatis搭建环境，使用dao实现类方式
    - 明确：在实际开发中，越简洁越好，所以一般不写dao实现类的方式，但mybatis支持写dao的实现类
        
- mybatis入门案例
    - 创建步骤
        - 读取配置文件
            - 代码
                - ```InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");```
            - 概述或注意事项
                - 一般不使用绝对路径(可能没有某个盘)和相对路径(打包为web工程时，没有src路径)
                - 可使用两种形式加载
                    - 使用类加载器，它只能读取类路径下的配置文件
                    - 使用ServerContext对象的getRealPath()
        - 创建SqlSessionFactory工厂
            - 代码
                - ```
                  SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
                  SqlSessionFactory factory = builder.build(is);
                  ```
            - 概述或注意事项
                - 创建工厂mybatis使用了构建者模式，SqlSessionFactoryBuilder就相当于一对包工队，builder就相当于构建者，
                只需要提供钱，不需要考虑自己工厂的问题，其中 is 就是钱，把对象的创建细节隐藏，使用者直接调用方法即可拿到对象
        - 创建SqlSession对象
            - 代码
                - ```SqlSession session = factory.openSession();```
            - 概述或注意事项
                - 生产SqlSession是用了工厂模式
                - 优势
                    - 解耦(降低类之间的互相依赖关系)
        - 创建Dao接口的代理对象
            - 代码
                - ```IUserDao userDao = session.getMapper(IUserDao.class);```
            - 概述或注意事项
                - 创建Dao接口实现类使用了代理模式
                - 优势
                    - 在不修改源码的基础上对已有的方法增强
        - 执行Dao中的方法
        - 释放资源
    - 注意事项
        - 在映射配置中需告诉mybatis要封装到哪个实体类中
        - 配置的方式
            - 在select标签添加resultType属性，指定实体类的全限定类名
        

- 自定义mybatis框架
    - 分析
        - mybatis在使用代理dao的方式实现增删改查时只做了两件事
            - 创建代理对象
            - 在代理对象中调用selectList
    - 涉及类及接口
        - Resources
        - SqlSessionFactoryBuilder
        
        - SqlSessionFactory
        - SqlSession

## Day02
- mybatis的基本使用
- mybatis的表单crud操作
- mybatis的参数和返回值
- mybatis的dao编写
- mybatis的配置细节
    - properties标签
        - resource属性：比较常用的属性，用于指定配置文件的路径，按照类路径的写法且该配置文件必须在类路径下
        - uri属性：要求按照Url的写法来写地址，全称为Uniform Resource Location(统一资源定位符)。
            - 写法：http://localhost:8080/jeffrey/service
                   协议      主机      端口       uri
    - typeAliases标签
        - typeAliases用于配置别名，它只能配置domain中的类的别名
        - 属性
            - type：指定实体类中的全限定类名
            - alias：指定别名，当指定了别名后将不再区分大小写
    - package标签
        - 用于指定要配置别名的包，当指定之后，该包下的实体类都会注册别名，并且类名就是别名，不再区分大小写
    - mappers和mapper标签
        - sql语句都是写在Mapper配置文件中，当构建SqlSession类之后，就需要读取mapper配置文件中的sql配置，而mappers标签就是用来配置需要加载
        映射sql配置文件路径的。mappers标签下有很多mapper标签，每一个mapper标签都配置了一个独立的映射配置文件的路径
    - resultType标签
        - 用于指定结果集的类型
    - parameterType标签
        - 用于指定传入参数的类型
    - OGNL表达式(对象图导航语言)
        - 全称：Object(对象) Graphic(图) Navigation(导航) Language(语言)
        - 它是通过对象中的取值方法来获取数据，在写法上把get给省略，例如类中的写法 user.getUsername 用OGNL表达式写法 user.username
        - parameterType中已经提供了属性所属的类，所以此时不需要写对象名
        - 格式
            - #{对象.对象}的方式
    - sql语句中使用#{}字符:
        - 它代表占位符，相当于JDBC的?，都是用于执行语句时替换实际的数据，具体的数据是由#{}里面的内容决定的。
    - resultMap标签
        - 使用resultMap完成高级输出结果映射，可以建立查询的列名和实体类的属性名称不一致时建立对应关系，从而实现封装
            - resultMap标签属性
                - type属性：指定实体类的全限定类名
                - id属性：给定一个唯一id标识，用于给查询select标签所引用的
                - id标签：用于指定主键名称
                - result标签：用于指定非主键名称
                - column属性：用于指定数据库列名
                - property属性：用于指定实体类中的属性名称
        

## Day03
- mybatis中的连接池以及事务控制
    - mybatis中连接池使用及分析
        - 连接池
            - 在实际开发中都会使用连接池，因为可以减少获取连接所消耗的时间。
            - 连接池就是用于存入连接的容器，其实就是一个集合对象，该集合必须是线程安全的，不能两个线程拿到同一个连接，同时
            该集合必须实现队列的特性：先进先出
        - mybatis中的连接池
            - mybatis连接池提供了3种方式的配置
                - 配置的位置
                    - 主配置文件SqlMapConfig的dataSource标签，其中type属性表示采用哪种连接池方式
                - type属性的取值
                    - POOLED：采用传统的javax.sql.DataSource规范中的连接池，mybatis有针对规范的实现
                    - UNPOOLED：采用传统的获取连接的方式，虽然也实现了javax.sql.DataSource，但是并没有使用池的思想
                    - JUDI：采用服务器提供的JNDI技术实现，来获取DataSource对象，不同的服务器所能拿到的DataSource是不一样的
                        - 注意：如果不是web或者maven的war工程，是不能使用的，tomcat服务器采用的连接池就是dbcp连接池
    - mybatis事务控制的分析
        - 通过sqlSession对象的commit方法和rollback方法实现事务的提交和回滚
        - 事务的四大特性ACID
        - 不考虑隔离性会产生的3个问题
            - 解决方法：四种隔离级别
            
- mybatis基于XML配置的动态SQL语句使用
    - mappers配置文件中的几个标签
        - ```<if>```
            - 作用
                - 在多条件组合判断中使用
            - 属性
                - test：指定对象的属性名，如果是包装类的对象要使用OGNL表达式的写法，需添加where 1 = 1
        - ```<where>```
            - 作用
                - 简化where 1 = 1
        - ```foreach```
            - 作用
                - 经常用于遍历集合，构建in条件语句或批量操作语句
            - 属性
                - collection：代表要遍历的集合元素
                - open：代表语句要开始的部分
                - close：代表语句结束的部分
                - item：对表遍历集合的每个元素，生成的变量名
                - separator：代表分隔符
        - ```sql```
            - 作用
                - 可将SQL中重复的语句提取出来，使用时用include引用
            - 属性
                - id：代表唯一表示
        - ```association```
            - 作用
                - 一对一关系映射，将另一张表的字段所关联，然后一起映射到实体类（两张表的关联查询, 然后映射到实体类中）
            - 属性
                - property属性：要求是实体类的一个属性
                - column属性：数据库中的列名，或者是列的别名
                - javaType属性：实体类的全限定类名，或者是别名
                - id标签：指定主键名称
                    - property属性：指定实体类中的成员变量
                    - column属性：指定数据库中的列名
                - result标签：指定除主键外其他的字段
                    - column属性：指定数据库中的字段
                    - property属性：指定实体类中的成员变量
        - ```collection```
            - 作用
                - 实现多对多映射关系
            - 属性
                - property属性：实体类里要映射的成员变量名
                - ofType属性：指定关联查询的结果集中的对象类型即 List 中的对象类型,可以使用别名，也可以使用全限定名
                  - id标签：指定主键名称
                    - property属性：指定实体类中的成员变量
                    - column属性：指定数据库中的列名
                - result标签：指定除主键外其他的字段
                    - column属性：指定数据库中的字段
                    - property属性：指定实体类中的成员变量
        - ``````
            
- mybatis中的多表操作
    - 表之间的关系
        - 一对多
        - 多对一
            - 一对多和多对一准确来说不是同一种，例如，一个用户可以下多个订单和多个订单属于同一个用户，用户和订单就是一对多，订单和用户就是多对一
        - 一对一
            - 一个人只能有一个身份证好，一个身份证号只能属于一个人，人和身份证就是一对一
        - 多对多
            - 一个学生可以被多个老师教过，一个老师可以教多个学生，老师和学生之间就是多对多
        - 特例
            - 如果拿出每一个订单，都只能属于一个用户，所以mybatis中就把多对一看成了一对一
    - mybatis中的多表
        - 示例：用户和账户，一个用户可以有多个账户，一个账户只能属于一个用户(多个账户也可以属于同一个用户)
        - 步骤
            - 建立两张表，让用户表和账户表之间具备一对多的关系，需要使用外键在账户注册表中添加
                - 用户表
                - 账户表
            - 建立两个实体类，让用户和账户的实体类能体现出来一对多的关系
                - 用户实体类
                - 账户实体类
            - 建立两个配置文件
                - 用户配置文件
                - 账户配置文件
            - 实现配置
                - 当查询用户时，可以同时得到用户下包括的账户信息，当查询账户时，可以同时得到账户的所属用户信息
    - 示例：用户和角色，用户可以有多个角色，一个角色可以赋予多个用户
    - 步骤
        - 建立两张表，让用户表和角色表之间具备多对多的关系，需要使用中间表，中间表中包含各自的主键，在中间表中是外键
            - 用户表
            - 角色表
        - 建立两个实体类，让用户和角色的实体类能体现出来多对多的关系，各自包含对方一个集合引用
            - 用户实体类
            - 角色实体类
        - 建立两个配置文件
            - 用户配置文件
            - 角色配置文件
        - 实现配置
            - 当查询用户时，可以同时得到用户下包括的账户信息，当查询角色时，可以同时得到角色所赋予的用户信息
## Day04
- 问题
    - 在一对多中，假如又一个用户，拥有100个账户，在查询用户的时候，是否需要把关联的账户查出来，在查询账户的时候，是否需要把关联的用户查出来
    - 在用户下的账户信息应该是什么时候使用，什么时候查询
    - 在查询账户时，账户的所属用户信息应该是伴随着账户查询时一起查询出来

- mybatis中的缓存机制
    - mybatis默认提供了两级缓存，分别是一级缓存和二级缓存
        - 默认情况下，只有一级缓存(sqlSession级别的缓存，成为本地缓存)开启，而二级缓存不开启
        - 二级缓存需要手动开启和配置，是基于namespace级别的缓存，二级缓存也称为全局缓存，也称为应用缓存
        - 为了提供扩展性，mybatis定义了缓存接口Cache，可以通过实现Cache接口自定义二级缓存
        - 总结
            - 一级缓存：SQLSession级别，默认开启
            - 二级缓存：namespace级别，默认关闭，需要手动开启和配置
            - 如果需要实现自定义缓存，需要实现Mybatis的Cache接口
        - 二级缓存配置步骤
            - xml配置
                - 在主配置文件上添加对二级缓存的支持```<settings><setting name="cacheEnabled" value=true><setting/></settings>```
                - 在映射文件配置上添加```<cache/>```标签，需注意标签的顺序位置
            - 注解配置
                - 在主配置文件上添加对二级缓存的支持```<settings><setting name="cacheEnabled" value=true><setting/></settings>```
                - 在dao层的对应接口上添加@CacheNamespace(blocking = true)

- Mybatis中的延迟加载
    - 什么事延迟加载
        - 在真正使用数据时，再发起查询，不用的时候不查询，按需加载(懒加载)
    - 什么是立即加载
        - 不管用不用，只要一调用方法，将立即发起查询
    - 在对应的四种表关系中，一对多，多对一，一对一，多对多
        - 一对多，多对多：通常情况下使用延迟加载
        - 多对一，一对一：通常情况下都是采用立即加载
    - 延迟加载开启配置方式
        - 在主配置文件中添加配置，需注意配置位置顺序
            - ```
              <settings>
                    <setting name="lazyLoadingEnabled" value=true/>
                    <setting name="aggressiveLazyLoading" value=false/>
              </settings>
              ```
- Mybatis中的缓存
    - 什么是缓存
        - 存在于内存中的临时数据
    - 为什么使用缓存
        - 减少和数据库的交互次数，提高执行效率
    - 什么样的数据可以使用缓存，设么样的数据不能使用缓存
        - 适用于缓存
            - 经常查询的数据并且不经常更改的
            - 数据库的正确与否对最终结果影响不大的
        - 不适用于缓存
            - 经常改变的数据
            - 数据的正确与否对最终的结果影响很大的
            - 例如：商品的库存，银行的汇率，股市的拍卖行
    - Mybatis中的一级缓存和二级缓存
        - 一级缓存
            - 指的是Mybatis中sqlSession对象的缓存，当执行查询之后，查询的结果会同时存入到SqlSession提供的一块区域中，该区域的结构为一个Map，
            当再次查询同样的数据，mybatis会先去SqlSession中查看是否有，如果有将直接拿出适用，当SqlSession对象消失时，mybatis的一级缓存也将消失
            - 一级缓存是sqlSession的范围缓存，当调用SqlSession的修改，添加，删除，commit()，close()等方法时，将会清空一级缓存
        - 二级缓存
            - 指的是Mybatis中SqlSessionFactory对象的缓存。由同一个SqlSessionFactory对象创建的SqlSession共享其缓存
            - 使用步骤
                - 开启Mybatis框架支持二级缓存(在SqlMapConfig.xml中配置)
                - 开启映射文件支持二级缓存(在IUserDao.xml中配置)
                - 开启当前的操作支持二级缓存(在select标签中配置)
- mybatis的注解开发
    - 单表CRUD(代理Dao方式)
        - 不再需要写dao接口映射配置文件，而是在接口对应的方法上添加相对应的CRUD四种注解，可以在注解内写入sql语句
            - @Select
                - @Select("select * from user"")
            - @Update
                - @Update("update user set username=#{username}, password=#{password} where id=#{id}")
            - @Insert
                - @Insert("insert into user(username, password)values(#{username}, #{password}) where id={id}")
            - @Delete
                - @Delete("delete from user where id=#{id}")
    - 多表查询查询操作
        - @One注解：一对多(一对一)关联查询
        - @Many注解：一对多关联查询
        
        - @Results注解：当数据库字段名与实体类对应的属性名不一致时使用，功能和resultMap一直，其中的id为唯一标识方便调用，value为定义@Result数组
            - @Result注解：Result被@Results的value属性所包裹。column为数据库字段名，property为实体类属性名，jdbcType为数据库字段数据类型，id为是否为主键。
        - @ResultMap注解：当@Results内的代码需要被重复使用时，为了提高代码的复用性，可以为@Results注解添加唯一表示id，然后可使用@ResultMap来调用这段代码
    - 缓存的配置
        - 在主配置文件上添加对二级缓存的支持```<settings><setting name="cacheEnabled" value=true><setting/></settings>```
        - 在dao层的对应接口上添加@CacheNamespace(blocking = true)
    
