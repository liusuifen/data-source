package com.example.mysqloracle;

import org.apache.catalina.core.ApplicationContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextAware;

@MapperScan(basePackages={"com.example.mysqloracle.dao.*","com.example.mysqloracle.mapper.*"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MysqlOracleApplication implements CommandLineRunner, ApplicationContextAware {

    public static void main(String[] args) {
        SpringApplication.run(MysqlOracleApplication.class, args);
//        SpringApplicationBuilder springApplicationBuilder =
//                new SpringApplicationBuilder(MysqlOracleApplication.class);
//        springApplicationBuilder.profiles("dev").logStartupInfo(true).run(args);
}

    @Override
    public void run(String... args) throws Exception {

    }

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {

    }
}
