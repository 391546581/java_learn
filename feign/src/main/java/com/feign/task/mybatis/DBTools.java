package com.feign.task.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @Auther: wangcs
 * @Date: 2019/4/17 15:17
 * @Description:
 */
public class DBTools {
    public static SqlSessionFactory SessionFactory;
    static {

        try {
            //加载mybatis的配置文件
            Reader reader = Resources.getResourceAsReader("mybatis.cfg.xml");

            //构建sqlSession的工厂
            SessionFactory = new SqlSessionFactoryBuilder().build(reader);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    //对外提供可以获得sqlSession的方法
    public static SqlSession getSqlSession() {
        return SessionFactory.openSession();
    }
}
