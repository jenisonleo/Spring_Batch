package com.kafka.demo;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

public class KafkaApplication {

    public static void main(String[] args) {
        System.out.println("test");
        Properties properties=new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
//        KafkaProducer<String,String> producer=new KafkaProducer<String, String>(properties);
        File f=new File("C:\\Users\\grje9001\\Downloads\\kafka_2.12-2.3.0\\config\\server.properties");
        if(f.exists()){
            System.out.println("null");
        }else {
            System.out.println("not null");
        }
//        try {
//            createTopic("jenisontopicnewnew");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        sendMessage(createProducer());
    }


    public static void createTopic(String topicName) throws Exception{
        Properties properties=new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
//        properties.load(new FileReader(new File("C:\\Users\\grje9001\\Downloads\\kafka_2.12-2.3.0\\config\\server.properties")));
        AdminClient client=AdminClient.create(properties);
        NewTopic newTopic=new NewTopic(topicName,1, (short)1);
        ArrayList topics=new ArrayList<NewTopic>();
        topics.add(newTopic);
        client.createTopics(topics);
        client.close();
    }
    public static Producer<String, String> createProducer(){
        Properties properties=new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        Producer<String,String> producer=new KafkaProducer<String, String>(properties);
        return producer;
    }

    public static void sendMessage(Producer<String,String> producer){
        ProducerRecord<String,String> record=new ProducerRecord<String,String>("jenisontopicnewnew",null,System.currentTimeMillis(),"data","leonardo da vinci");
        producer.send(record);
        producer.close();
    }
}
