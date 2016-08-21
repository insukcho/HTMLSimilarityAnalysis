package com.chris.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.chris.Application;
import com.chris.model.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class RedisArticleRepositoryTests {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Test
	public void testSave() throws Exception {
		Article article = new Article(111, "aaa.com", 111);
		articleRepository.save(article);
		Assert.assertTrue(articleRepository.findAll().contains(article));
		articleRepository.delete(article);
	}
	
	@Test
	public void testFindAll() throws Exception {
		articleRepository.save(new Article(111, "aaa.com", 111));
		articleRepository.save(new Article(222, "bbb.com", 222));
		Assert.assertTrue(articleRepository.findAll().size() == 2);
		articleRepository.deleteAll();
	}
}
