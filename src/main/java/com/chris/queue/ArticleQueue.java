package com.chris.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.chris.model.Article;

public class ArticleQueue {

	private static ArticleQueue instance = new ArticleQueue();

	private static BlockingQueue<Article> requestQueue = new LinkedBlockingQueue<Article>();
	private static BlockingQueue<Article> processedQueue = new LinkedBlockingQueue<Article>();

	private ArticleQueue() {} // for singleton

	public static ArticleQueue getInstance() {
		return instance;
	}

	public BlockingQueue<Article> getRequestQueue() {
		return requestQueue;
	}

	public BlockingQueue<Article> getProcessedQueue() {
		return processedQueue;
	}
	
	
}
