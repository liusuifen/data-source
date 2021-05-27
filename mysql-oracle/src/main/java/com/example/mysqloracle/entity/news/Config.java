package com.example.mysqloracle.entity.news;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = {"application.properties"})
public class Config {

    @Value(value = "${oss.url}") //配置文件中对应的k
    private String ossUrl;
}
