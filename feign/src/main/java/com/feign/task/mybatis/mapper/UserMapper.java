package com.feign.task.mybatis.mapper;

import com.feign.task.mybatis.UserBean;

import java.util.List;

/**
 * @Auther: wangcs
 * @Date: 2019/4/17 15:19
 * @Description:
 */
public interface UserMapper {
    /**
     *增加用户
     * @param user
     * @return
     * @throws Exception
     */
    public int insertUser(UserBean user) throws Exception;

    /**
     * 根据id来更新某用户信息
     * @param user
     * @return
     * @throws Exception
     */

    public int updateUser(UserBean user) throws Exception;

    /**
     * 根据id来删除数据
     * @param id
     * @return
     * @throws Exception
     */

    public int deleteUser(int id) throws Exception;

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     * @throws Exception
     */
    public UserBean selectUserById(int id) throws Exception;

    /**
     * 查询所有用户信息
     * @return
     * @throws Exception
     */
    public List<UserBean> selectAllUser() throws Exception;
}
