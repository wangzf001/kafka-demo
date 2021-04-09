package com.example.kafkademo.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author HERO
 * @date 2021/4/7
 * @project_name kafka-demo01
 */
@Component
public class KafkaProducer {
    @Autowired
    KafkaTemplate kafkaTemplate ;

    private final static String topic = "third-test";

    public void sendRegisterSuccInfo(String uerVerifyMap) {
        try {
            kafkaTemplate.send(topic,"wzf", uerVerifyMap);
        }catch (Exception e){
            e.printStackTrace();
        }

    }




}
