package com.chris.controller;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chris.model.Article;
import com.chris.repository.ArticleRepository;
import com.chris.worker.Consumer;

@RestController
public class ApiController {

	BlockingQueue<Article> requestQueue;
	ArticleRepository articleRepository;
	
	public ApiController(BlockingQueue<Article> requestQueue, ArticleRepository articleRepository) {
		this.requestQueue = requestQueue;
		this.articleRepository = articleRepository;
		
		// start consuming request queue, for 
		new Thread(new Consumer(requestQueue, articleRepository)).start();
	}

	@RequestMapping("/add/article")
	public String produceArticle(@RequestParam("url") String url) throws InterruptedException {
		requestQueue.put(new Article(System.currentTimeMillis(), url, System.nanoTime()));
		return "Create and enqueue new article, successfully!";
	}

	@RequestMapping("/request/articles")
	public String getRequestArticles() {
		return requestQueue.toString();
	}
	
	@RequestMapping("/articles")
	public List<Article> getProcessedArticles() throws Exception {
		return articleRepository.findAll();
	}
	
	@RequestMapping("/delete/articles")
	public String deleteAllArticles() throws Exception {
		articleRepository.deleteAll();
		return "Delete all articles, successfully!!";
	}
}
