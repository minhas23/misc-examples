package com.manjeet.sample.java.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class MyKafkaProducer {
 
 public static void main(String[] args) throws Exception{
 
    String topicName = "test";
    String key = "1479450562$172.167.5.42$a9ee75647f84$devdisconnect$udk4";
    String value = "v";
    
    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("acks", "all");
    props.put("retries", 0);
    props.put("batch.size", 16384);
    props.put("linger.ms", 1);
    props.put("buffer.memory", 33554432);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("partitioner.class", "com.manjeet.sample.java.kafka.MyPartitioner");
    
    Producer<String, String> producer = new KafkaProducer<String, String>(props);
          
    producer.send(new ProducerRecord<String, String>(topicName, key , value));
    System.out.println("Message sent successfully");
    producer.close();
 }
}

