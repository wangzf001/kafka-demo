package com.example.kafkademo.demo;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

/**
 * @author 王振方
 * @date 2020/12/8
 */
public class WzfKafkaConsumer02 extends Thread{

    KafkaConsumer kafkaConsumer;
    String topic;

    public WzfKafkaConsumer02(String topic) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.16.2.106:9092");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG,"wzf-consumer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"wzf-group03");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,1000);
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,30000);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        kafkaConsumer = new KafkaConsumer(properties);
        this.topic = topic;
    }

    @Override
    public void run() {
        kafkaConsumer.subscribe(Collections.singleton(this.topic));
        while (true){
            ConsumerRecords<Integer, String> consumerRecords = kafkaConsumer.poll(1L);
            consumerRecords.forEach(record->{
                System.out.println("consumer:"+record.key()+"->"+record.value()+"->"+record.offset());
            });
        }
    }

    public static void main(String[] args) {
        new WzfKafkaConsumer02("second-test").start();
    }
}
