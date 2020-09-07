package com.lyx.houtai.web;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.lyx.houtai.mongodb.repository.SchoolInfoRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.lyx")
@MapperScan({"com.lyx.houtai.mapper"})
@EnableMongoRepositories(basePackages="com.lyx.houtai.mongodb.repository")
@Slf4j
public class Application {

	public static void main(String[] args) {
		log.info("===========houtai===Application:{}=====begin===","sssssssss");
		ConfigurableApplicationContext context = SpringApplication.run(Application.class,args);
		Environment env = context.getEnvironment();
		System.out.println("==datasource.name:=="+env.getProperty("spring.datasource.name")+"=====spring.application.name:"+env.getProperty("spring.application.name"));
		System.out.println("===========houtai===Application=====end===");
		log.info("项目启动！！");
	}
	
}
