package com.example.kafkademo.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author 王振方
 * @date 2020/12/8
 */
public class WzfKafkaProcucer extends Thread{


    KafkaProducer producer;
    String topic;

    public WzfKafkaProcucer(String topic) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.16.2.106:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"wzf-producer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,10);//批量发送大小
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);//批量发送最大间隔时间

        producer = new KafkaProducer(properties);
        this.topic = topic;
    }


    @Override
    public void run() {
        int num = 0;
        String msg = "wzf kafka msg:" + num;
        while (num<50){
            try {
                RecordMetadata recordMetadata = (RecordMetadata)producer.send(new ProducerRecord(topic,msg)).get();
                System.out.println(recordMetadata.offset()+"->"
                        +recordMetadata.partition()+"->"
                        +recordMetadata.topic());

                TimeUnit.SECONDS.sleep(1);
                ++num;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new WzfKafkaProcucer("second-test").start();
    }
}
