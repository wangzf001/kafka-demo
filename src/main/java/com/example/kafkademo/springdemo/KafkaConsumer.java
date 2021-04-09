package com.example.kafkademo.springdemo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

/**
 * @author HERO
 * @date 2021/4/7
 * @project_name kafka-demo01
 */
@Component
public class KafkaConsumer {

    private final static String topic = "third-test";
//    private final static String group_id = "mail-group-id";


    @KafkaListener(topics = topic)
    public void receiveInfo(ConsumerRecord record){
        try {
            System.out.println("收到一条消息："+record);
//            acknowledgment.acknowledge();//手动提交消息
        }catch (Exception e){
         e.printStackTrace();
        }
    }


}
