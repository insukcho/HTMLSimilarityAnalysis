package com.chris.worker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chris.analysis.SimilarityAnalysis;
import com.chris.model.Article;
import com.chris.parser.HTMLParser;
import com.chris.repository.ArticleRepository;

public class Consumer implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

	private BlockingQueue<Article> queue;

	private ArticleRepository articleRepository;

	public Consumer(BlockingQueue<Article> queue, ArticleRepository articleRepository) {
		this.queue = queue;
		this.articleRepository = articleRepository;
	}

	public void run() {
		try {
			while (true) {
				consume(queue.take());
			}
		} catch (Exception e) {
			LOGGER.error("Cannot consume article.", e);
		}
	}

	private void consume(Article article) throws Exception {
		Document doc = null;
		// check validity of URL
		try {
			doc = HTMLParser.htmlParsing(article.getUrl());
			article.setValidity(true);
		} catch (Exception e) {
			LOGGER.error("Fail to parse response HTMML.", e);
			article.setValidity(false);
		}

		// if valid
		if (article.isValidity() && doc != null) {
			// parse and generate valid
			HTMLParser.setAllWordsInBody(article, doc);

			// find the most similar article
			for (Article exist : articleRepository.findAll()) {
				if (exist.isValidity()) {
					SimilarityAnalysis.calculateSimilarity(article, exist, articleRepository);
				}
			}

		}

		// calculate duration for ETA
		article.setProcessedTime(System.nanoTime());
		article.setDurationMS(TimeUnit.NANOSECONDS.toMillis(article.getProcessedTime() - article.getCreateTime()));

		// persist article
		articleRepository.save(article);
	}
}
