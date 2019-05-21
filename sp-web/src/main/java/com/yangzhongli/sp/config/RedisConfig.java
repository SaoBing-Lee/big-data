package com.yangzhongli.sp.config;

import com.yangzhongli.sp.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Bean
	public Object initRedisClient() {
		RedisClient.setTemplate(redisTemplate());
		Object obj = new Object();
		return obj;
	}

	@Bean(name = "dataTemplate")
	public StringRedisTemplate redisTemplate() {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setKeySerializer(stringRedisSerializer);
		template.setValueSerializer(stringRedisSerializer);
		template.setHashKeySerializer(stringRedisSerializer);
		template.setHashValueSerializer(stringRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

}
