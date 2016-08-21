package com.chris.controller;

import java.util.concurrent.BlockingQueue;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chris.model.Article;

@RestController
public class ApiController {

	BlockingQueue<Article> requestQueue;
	
	public ApiController(BlockingQueue<Article> requestQueue) {
		this.requestQueue = requestQueue;
	}

	@RequestMapping("/")
	public String produceArticle(@RequestParam("topic") String topic) throws InterruptedException {
		requestQueue.put(new Article(System.currentTimeMillis(), topic, System.nanoTime()));
		return "Create and enqueu Article sucessfully!";
	}

	@RequestMapping("/request/articles")
	public String getRequestArticles() {
		return requestQueue.toString();
	}
}
