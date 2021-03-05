package com.example.bootdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author HERO
 * @date 2020/12/14
 * @project_name kafka-demo
 */
public class BootDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(FirstConfiguration.class);
        System.out.println(ac.getBean("user"));

    }
}
