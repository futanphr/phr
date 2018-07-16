package com.phr.test;

import java.util.HashSet;
import java.util.Set;

import com.phr.common.redis.RedisClusterUtils;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class TestRedis {

	public static void main(String[] args) {
		JedisPoolConfig config = new JedisPoolConfig();
		config =new JedisPoolConfig();
		       config.setMaxTotal(60000);//设置最大连接数  
		       config.setMaxIdle(1000); //设置最大空闲数 
		       config.setMaxWaitMillis(3000);//设置超时时间  
		       config.setTestOnBorrow(true);


		// 集群结点
		Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
		jedisClusterNode.add(new HostAndPort("192.168.68.130", Integer.parseInt("7001")));
		jedisClusterNode.add(new HostAndPort("192.168.68.130", Integer.parseInt("7002")));
		jedisClusterNode.add(new HostAndPort("192.168.68.130", Integer.parseInt("7003")));
		jedisClusterNode.add(new HostAndPort("192.168.68.130", Integer.parseInt("7004")));
		jedisClusterNode.add(new HostAndPort("192.168.68.130", Integer.parseInt("7005")));
		jedisClusterNode.add(new HostAndPort("192.168.68.130", Integer.parseInt("7006")));

		JedisCluster jc = new JedisCluster(jedisClusterNode, config);
		//JedisCluster jc = new JedisCluster(jedisClusterNode);
		jc.set("name", "zhangsan");
		String value = jc.get("name");
		System.out.println(value);
		
		System.out.println("获取redis中的key:"+jc.get("jfcf-quota__LOCK_123"));
		
		
		System.out.println("===>"+RedisClusterUtils.getJedisCluster().get("jfcf-quota__LOCK_123"));
		
		System.out.println(RedisClusterUtils.get("123"));
	}
}
