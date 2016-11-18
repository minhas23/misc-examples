package com.manjeet.sample.java.kafka;
import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.utils.Utils;

public class MyPartitioner implements Partitioner {
	 
	  @Override
	  public void configure(Map<String, ?> configs) {
	 
	  }
	 
	  @Override
	  public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
	 
	    int partition = 0;
	    
	    try{
	       String uid = ((String)key).split("\\$")[4];
	       int numPartitions = cluster.partitionCountForTopic(topic);
	       partition =  Utils.abs(uid.hashCode()) % numPartitions;
	       System.out.println("MyPartitioner: key=" +(String)key + " ,uid="+ uid + " ,partition="+ partition + " ,numOfPartitions="+numPartitions);
	    }catch(Exception e){}
	    
	    return partition;
	  }
	 
	  @Override
	  public void close() {
	 
	  }
	}