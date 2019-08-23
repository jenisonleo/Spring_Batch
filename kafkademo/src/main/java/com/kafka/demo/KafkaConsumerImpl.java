package com.kafka.demo;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.function.Consumer;

public class KafkaConsumerImpl {

    public static void main(String[] args){
        subscribeConsumer(createConsumer());
        System.out.println("ended");
    }


    public static KafkaConsumer createConsumer(){
        Properties properties=new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"jenisongroup");
        KafkaConsumer<String,String> consumer= new KafkaConsumer<String, String>(properties);
        consumer.subscribe(new ArrayList<String>(){
            {
                add("jenisontopicnewnew");
            }
        });
        return consumer;
    }

    public static void subscribeConsumer(KafkaConsumer kafkaConsumer){
        while (true){
            ConsumerRecords poll = kafkaConsumer.<String,String>poll(Duration.ofSeconds(2));
            poll.forEach(new Consumer() {
                @Override
                public void accept(Object o) {
                    System.out.println("received "+o.toString());
                }
            });
        }
    }
}
