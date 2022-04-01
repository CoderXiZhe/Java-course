package com.bjpowernode.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtil {
    private SqlSessionUtil() {}
    private static SqlSessionFactory sqlSessionFactory;
    //取得SqlSession对象
    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    private static ThreadLocal<SqlSession> t = new ThreadLocal<SqlSession>();

    public static SqlSession getSqlSession(){
        SqlSession session = t.get();

        if(session == null) {
            session = sqlSessionFactory.openSession();
            t.set(session);
        }
        return session;
    }
    //关闭SqlSession对象
    public static void myClose(SqlSession session) {
        if(session != null) {
            session.close();
            //这句必须加 容易忘
            //每次操作结束后 强制剥离开 线程回到线程池中 等待下次操作被捞起
            t.remove();
        }
    }
}
