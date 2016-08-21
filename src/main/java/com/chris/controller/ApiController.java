package com.chris.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chris.model.Article;
import com.chris.queue.ArticleQueue;

@RestController
public class ApiController {

	@RequestMapping("/")
	public String produceArticle(@RequestParam("topic") String topic) throws InterruptedException {
		ArticleQueue.getInstance().getRequestQueue()
				.put(new Article(System.currentTimeMillis(), topic, System.nanoTime()));
		return "Create and enqueu Article sucessfully!";
	}

	@RequestMapping("/request/articles")
	public String getRequestArticles() {
		return ArticleQueue.getInstance().getRequestQueue().toString();
	}
}
