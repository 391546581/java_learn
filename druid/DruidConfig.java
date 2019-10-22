package com.gvt.apos.common.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author BlueWang
 * @ClassName: DruidConfiguration
 * @Description:
 * @date 2019/10/21 9:17
 */
@Configuration
@Slf4j
public class DruidConfig {
    /**
     * 在jvm启动参数中加上：
     *             -Ddruid.filters=mergeStat -Ddruid.useGlobalDataSourceStat=true
     *
     *      <!-- Druid依赖 -->
     * 		<dependency>
     * 			<groupId>com.alibaba</groupId>
     * 			<artifactId>druid-spring-boot-starter</artifactId>
     * <!--			<version>1.1.20</version>-->
     * 		</dependency>
     *
     *spring:
     *   datasource:
     * 	   connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
     *     filters: stat,wall,logback
     *     initialSize: 5
     *     maxActive: 20
     *     maxPoolPreparedStatementPerConnectionSize: 20
     *     maxWait: 60000
     *     minEvictableIdleTimeMillis: 300000
     *     minIdle: 5
     *     poolPreparedStatements: true
     *     testOnBorrow: false
     *     testOnReturn: false
     *     testWhileIdle: true
     *     timeBetweenEvictionRunsMillis: 60000
     *     type: com.alibaba.druid.pool.DruidDataSource
     *     validationQuery: SELECT 1 FROM DUAL
     *     useGlobalDataSourceStat: true
     */

    @WebServlet(urlPatterns = "/druid/*", initParams = {
            // IP白名单(没有配置或者为空，则允许所有访问)
            @WebInitParam(name = "allow", value = "127.0.0.1"),
            // IP黑名单 (存在共同时，deny优先于allow)
            @WebInitParam(name = "deny", value = ""),
            @WebInitParam(name = "loginUsername", value = "root"),
            @WebInitParam(name = "loginPassword", value = "root"),
            @WebInitParam(name = "resetEnable", value = "false")
    })
    public class DruidStatViewServlet extends StatViewServlet {
        private static final long serialVersionUID = 1L;
    }

    @WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {
    @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
    })
    public class DruidStatFilter extends WebStatFilter {

    }

    @Bean
    public WallConfig wallConfig(){
        WallConfig wallConfig = new WallConfig();
        wallConfig.setMultiStatementAllow(true);
        //允许一次执行多条语句
        wallConfig.setNoneBaseStatementAllow(true);
        //是否允许非以上基本语句的其他语句
        wallConfig.setStrictSyntaxCheck(false);
        //是否进行严格的语法检测
        return wallConfig;
    }
}
