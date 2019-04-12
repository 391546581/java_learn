package com.feign.task;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Auther: wangcs
 * @Date: 2019/4/12 15:25
 * @Description:
 */
public class JdbcTemplateUtils {
    public static final String username = "root";
    public static final String password = "bwoil@20141030";
    public static final String jdbcUrl = "jdbc:mysql://192.168.101.61:3306/bwoil_migration?allowMultiQueries=true&autoReconnect=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai";
    public static final String driverName = "com.mysql.jdbc.Driver";

    public static JdbcTemplate getJdbcTemplate() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setPassword(password);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setDriverClassName(driverName);

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        return jdbcTemplate;
    }
}