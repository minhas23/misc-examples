package com.manjeet.sample.java.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {
	
	public static void main(String[] args) {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
		
		try {
			  Jedis jedis = pool.getResource();
			  jedis.set("foo", "bar");
			  String foobar = jedis.get("foo");
			  System.out.println("foobar : " + foobar );
			}catch(Exception e){
				
			}finally{
				pool.destroy();
			}
	}

}
