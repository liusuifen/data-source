package com.example.mysqloracle.common;

import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.common.DataSourceSign;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Method;
@Component
@Order(-1)// 保证该AOP在@Transactional之前执行
@Aspect
public class DynamicDataSourceAspect {



    @Before("execution(* com.example.mysqloracle.service..*.*(..))")
    public void before(JoinPoint point){
        try {
            DataSourceSign annotationOfClass =
                    point.getTarget().getClass().getAnnotation(DataSourceSign.class);

            String methodName = point.getSignature().getName();

            Class[] parameterTypes =
                    ((MethodSignature) point.getSignature()).getParameterTypes();

            Method method =
                    point.getTarget().getClass().getMethod(methodName, parameterTypes);

            DataSourceSign methodAnnotation =
                    method.getAnnotation(DataSourceSign.class);

            methodAnnotation = methodAnnotation ==
                    null ? annotationOfClass:methodAnnotation;

            ContextConst.DataSourceType dataSourceType =
                    methodAnnotation != null &&  methodAnnotation.value() !=null ?
                            methodAnnotation.value() :ContextConst.DataSourceType.PRIMARY ;
            DataSourceContextHolder.setDataSource(dataSourceType.name());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Bean
    @ConfigurationProperties(prefix="mybatis.configuration")
    public org.apache.ibatis.session.Configuration globalConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

//    @Bean(name="sqlSessionFactory")
//    public SqlSessionFactory getSqlSessionFactory(ContextConst.DataSourceType dataSource, Configuration config) {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setConfiguration(config);
//        try {
//            return bean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @After("execution(* com.example.mysqloracle.service..*.*(..))")
    public void after(JoinPoint point){
        DataSourceContextHolder.clearDataSource();
    }

}
