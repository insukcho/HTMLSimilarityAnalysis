package com.chris.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.chris.model.Article;
import com.chris.repository.ArticleRepository;
import com.chris.repository.FakeArticleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ApiControllerTest {

	private MockMvc subject;

	BlockingQueue<Article> requestQueue = new LinkedBlockingQueue<Article>();
	ArticleRepository articleRepository = new FakeArticleRepository();

	@Before
	public void setUp() throws Exception {
		subject = MockMvcBuilders.standaloneSetup(new ApiController(requestQueue, articleRepository)).build();
	}

	@Test
	public void testCreateNEnqueueArticle() throws Exception {
		subject.perform(get("/?topic=wow.com").accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
				.andDo((result) -> {
					Assert.assertEquals("Create and enqueu Article sucessfully!",
							result.getResponse().getContentAsString());
				});

		subject.perform(get("/?topic=wowwow.com").accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
				.andDo((result) -> {
					Assert.assertEquals("Create and enqueu Article sucessfully!",
							result.getResponse().getContentAsString());
				});
		Assert.assertEquals(2, requestQueue.size());

	}

	@Test
	public void testGetRequestArticles() throws Exception {
		requestQueue.removeAll(requestQueue);
		subject.perform(get("/?topic=aaa.com").accept(MediaType.TEXT_HTML)).andExpect(status().isOk());
		subject.perform(get("/?topic=bbb.com").accept(MediaType.TEXT_HTML)).andExpect(status().isOk());
		subject.perform(get("/request/articles").accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
				.andDo((result) -> {
					Assert.assertTrue(result.getResponse().getContentAsString().indexOf("aaa.com") > -1);
					Assert.assertTrue(result.getResponse().getContentAsString().indexOf("bbb.com") > -1);
				});
	}
}
