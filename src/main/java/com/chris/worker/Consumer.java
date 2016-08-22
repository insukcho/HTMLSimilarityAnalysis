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

/**
 * This consumer is Runnable, so it works as a Thread. Consumer consume Queue's
 * article. Consumer keep looking queue and if queue is empty, just wait not
 * using any resources. After new article is enqueued, take right away and do
 * processing.
 * 
 * Processing step is like below.
 * 
 * <pre>
 * 1) Try to get document after parsing HTML page using Article's URL.
 * 2) If it is invalid URL, set validity is false.
 * 3) If URL is valid, get all words from body tag using document.
 * 4) Get all articles in store and do similarity analysis for each article.
 * 5) After finish processing, mark the end time, 
 *    and calculate and mark whole elapse time from creation to the end of processing.
 * 6) Persist this article in store.
 * </pre>
 * 
 * If there are lots of traffic, we can use Thread pool for handling it.
 * 
 * @author insuk.cho
 *
 */
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
			while (true) { // keep watching the queue
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
		article.setEtaMS(TimeUnit.NANOSECONDS.toMillis(article.getProcessedTime() - article.getCreateTime()));

		// persist article
		articleRepository.save(article);
	}
}
