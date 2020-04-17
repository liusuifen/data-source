package com.example.mysqloracle.common;

import com.example.mysqloracle.common.ContextConst;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceSign {
    ContextConst.DataSourceType value() default ContextConst.DataSourceType.PRIMARY;
}
