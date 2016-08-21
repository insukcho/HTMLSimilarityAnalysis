package com.chris.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.chris.model.Article;

public class ArticleQueue {

	private static final ArticleQueue instance = new ArticleQueue();

	private final BlockingQueue<Article> requestQueue = new LinkedBlockingQueue<Article>();
	private final BlockingQueue<Article> processedQueue = new LinkedBlockingQueue<Article>();

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
