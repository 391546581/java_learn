package com.feign.task.mybatis;

import com.feign.task.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;

/**
 * @Auther: wangcs
 * @Date: 2019/4/17 14:21
 * @Description:
 */
public class ExcuteMybatis {
    public static void main(String[] args) throws Exception {
        SqlSession session = DBTools.getSqlSession();

        UserMapper usermapper = session.getMapper(UserMapper.class);
        System.out.println("----------------");

        UserBean user3 = new UserBean();
        user3.setUsername("Claire6");//修改name字段
        //user3.setPassword("iiiiii");
        user3.setAccount(20.9);
        user3.setId(36);
        usermapper.insertUser(user3);
//        usermapper.updateUser(user3);// 修改的某条id值为36的数据
        session.commit();

        UserBean userBean = usermapper.selectUserById(36);
        System.out.println(userBean);
    }
}
