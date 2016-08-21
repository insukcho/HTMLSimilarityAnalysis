package com.chris;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.chris.repository.ArticleRepository;
import com.chris.repository.RedisArticleRepository;

@SpringBootApplication
public class Application {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
    @Bean
    ArticleRepository articleRepository(StringRedisTemplate redisTemplate) {
        return new RedisArticleRepository(redisTemplate);
    }
}
