package com.example.bootdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 王振方
 * @date 2020/12/14
 */
@Configuration
public class FirstConfiguration {

    @Bean
    public User user(){
        return new User();
    }
}
