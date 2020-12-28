package com.obtk.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

/**
 * 获取数据库连接会话对象和关闭资源的工具类
 */
public class SqlSessionUtil {
    //创建数据库连接的工厂对象
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //读取主配置文件
            Reader reader= Resources.getResourceAsReader("mybatis-config.xml");
            //加载主配置文件，初始化连接工厂对象
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(){
        //返回一个数据库连接会话对象
        return sqlSessionFactory.openSession();
    }

    public static void close(SqlSession sqlSession){
        //判断连接对象不为空，则关闭连接，释放资源
        if(!Objects.isNull(sqlSession)){
            sqlSession.close();
        }
    }




}
