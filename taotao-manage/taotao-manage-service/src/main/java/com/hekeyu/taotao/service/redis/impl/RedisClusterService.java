package com.hekeyu.taotao.service.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hekeyu.taotao.service.redis.RedisService;

import redis.clients.jedis.JedisCluster;

public class RedisClusterService implements RedisService {
	@Autowired
	private JedisCluster jedisCluster;
	@Override
	public String set(String key, String value) {
		String result = jedisCluster.set(key, value);
		return result;
	}

	@Override
	public String setex(String key, int seconds, String value) {
		String result = jedisCluster.setex(key, seconds,value);
		return result;
	}

	@Override
	public Long expire(String key, int seconds) {
		Long result = jedisCluster.expire(key, seconds);
		return result;
	}

	@Override
	public String get(String key) {
		String result = jedisCluster.get(key);
		return result;
	}

	@Override
	public Long del(String key) {
		Long result = jedisCluster.del(key);
		return result;
	}

	@Override
	public Long incr(String key) {
		Long result = jedisCluster.incr(key);
		return result;
	}

}
