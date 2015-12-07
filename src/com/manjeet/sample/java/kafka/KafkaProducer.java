package com.manjeet.sample.java.kafka;


import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * Install Scala Plugin to eclipse and put metrics-core, kafka and kafla-client jars in classpath
 * @author manjeet
 *
 */
public class KafkaProducer {
    final static String TOPIC = "pixel";
    static String MSG = "{\"headers\":{\"cookie\":\"_od.uid=4260451463\"},\"uid\":\"3677141939\"}";


    public static void main(String[] argv){
        Properties properties = new Properties();
        properties.put("metadata.broker.list","localhost:9092");
        properties.put("serializer.class","kafka.serializer.StringEncoder");
        ProducerConfig producerConfig = new ProducerConfig(properties);
        Producer<String,String> producer = new Producer<String, String>(producerConfig);
        KeyedMessage<String, String> message =new KeyedMessage<String, String>(TOPIC,MSG);
        producer.send(message);
        producer.close();
    }
}
