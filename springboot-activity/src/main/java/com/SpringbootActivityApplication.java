package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootActivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootActivityApplication.class, args);
	}

}

//验证
//		启动项目前，连接数据库，查看需要连接数据库中没有表，启动项目完成后，刷新数据库，activiti已经创建相关表，打开act_re_procdef表，流程数据已经存在，即流程已经部署成功。
//		用浏览器访问地址：http://127.0.0.1:8081/activity/activityService/startActivityDemo
//		打印了预计的日志，没有报错信息，查看数据库中的act_ru_task表，发现刚才执行形成的数据，项目成功。
//		PS:只是简单的微服务，没有去写注册服务、网关配置、熔断机制等等，仅用于activiti与springboot的结合
//		=========================后续==========================
//		1.在项目单独作为一个引擎，本身不部署流程的时候，如果resources目录没有“processes”目录，启动项目报错--找不到processes目录。需要在配置文件中添加一下内容：
//		spring:
//		activiti:
//		####校验流程文件，默认校验resources下的processes文件夹里的流程文件
//		check-process-definitions: false
//		即启动项目，不去校验processes。
