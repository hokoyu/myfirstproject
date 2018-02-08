package com.hekeyu.taotao.service.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hekeyu.taotao.service.redis.RedisService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisPoolService implements RedisService {
	@Autowired
	private JedisPool jedisPool;
	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String setex(String key, int seconds, String value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.setex(key, seconds, value);
		return result;
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, seconds);
		return result;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		return result;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		return result;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		return result;
	}

}
