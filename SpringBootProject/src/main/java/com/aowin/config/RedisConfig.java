package com.aowin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//配置bean的类
@Configuration
public class RedisConfig {
	
	//生成一个指定名字的bean
	@Bean("myTemplate")
	public RedisTemplate getRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		StringRedisSerializer s = new StringRedisSerializer();
		template.setConnectionFactory(factory);
		template.setValueSerializer(s);
		template.setHashValueSerializer(s);
		template.setKeySerializer(s);
		template.setHashKeySerializer(s);
		return template;
	}
	
}
