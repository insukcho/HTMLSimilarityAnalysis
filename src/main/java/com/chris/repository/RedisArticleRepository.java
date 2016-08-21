package com.chris.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.redis.core.StringRedisTemplate;

import com.chris.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisArticleRepository implements ArticleRepository {
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final StringRedisTemplate redisTemplate;

	public RedisArticleRepository(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	private Article jsonToObject(String json) throws Exception {
		return objectMapper.readValue(redisTemplate.opsForValue().get(json), Article.class);
	}

	@Override
	public List<Article> findAll() throws Exception {

		List<Article> articles = new ArrayList<>();

		Iterator<String> it = redisTemplate.keys("*").iterator();

		while (it.hasNext()) {
			articles.add(jsonToObject(it.next()));
		}

		return articles;
	}

	@Override
	public void save(Article article) {
		redisTemplate.opsForValue().set(String.valueOf(article.getId()), article.toString());
	}

	@Override
	public void delete(Article article) {
		redisTemplate.opsForValue().getOperations().delete(String.valueOf(article.getId()));
	}

	@Override
	public void deleteAll() throws Exception {
		Iterator<String> it = redisTemplate.keys("*").iterator();

		while (it.hasNext()) {
			delete(jsonToObject(it.next()));
		}
	}

}
