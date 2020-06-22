package com.lemon.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @ClassName AlphaConfig
 * @Description TODO 1.3 使用配置类加载第三方（jar包）的Bean
 * @Author Lemon
 * @Date 20.6.20 020 11:55:04
 * @Version 1.0
 */
@Configuration // TODO @Configuration：表明这是一个配置类
public class AlphaConfig {

    // 实例化一个SimpleDateFormat对象，装配到Spring容器中
    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
