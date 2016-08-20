package com.chris.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chris.model.Article;
import com.chris.queue.ArticleQueue;

@RestController
public class ApiController {
	
	@RequestMapping("/")
	public String createNEnqueueArticle(@RequestParam("topic") String topic) {
		ArticleQueue.getInstance().getRequestQueue().add(new Article(System.currentTimeMillis(), topic));
		return "Create and enqueu Article sucessfully!";
	}
	
	@RequestMapping("/request/articles")
	public String getRequestArticles() {
		return ArticleQueue.getInstance().getRequestQueue().toString();
	}
}
