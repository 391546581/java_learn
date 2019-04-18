package com.feign.task.mybatis;

import lombok.Data;

/**
 * @Auther: wangcs
 * @Date: 2019/4/17 15:19
 * @Description:
 */
@Data
public class UserBean {
    private int id;
    private String username;
    private String password;
    private double account;
}
