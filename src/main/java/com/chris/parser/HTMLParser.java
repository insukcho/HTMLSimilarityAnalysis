package com.chris.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.chris.model.Article;

/**
 * For HTML parsing and generate words data for analysis.
 * 
 * {@link #getAllWordsInBody(Document)} takes Jsoup document and generate all
 * words for analysis.
 * 
 * @author Chris
 *
 */
public class HTMLParser {

	private final static String BODY_TAG_NAME = "body";
	private final static String WORD_SEPERATOR = " ";
	private final static String EXCEPT_ALPHABET = "[^A-Za-z]";

	/**
	 * Get html page from given URL and parse to Jsoup document which has samme
	 * domm structure.
	 * 
	 * @param url
	 * @return Jsoup document
	 * @throws IOException
	 */
	public static Document htmlParsing(String url) throws IOException {
		return Jsoup.connect(url).get();
	}

	/**
	 * Get all text in 'body' tags and split by {@link #WORD_SEPERATOR}. Remain
	 * only alphabet in the word using regex. Finally, generate map, key is the
	 * word and value is the count of word. This is important for analysis
	 * efficiently.
	 * 
	 * @param doc
	 * @return map with word and each count
	 */
	public static void setAllWordsInBody(Article article, Document doc) {
		int wordCount = 0;
		Map<String, Integer> wordNCount = new HashMap<>();

		Elements elements = doc.getElementsByTag(BODY_TAG_NAME);
		for (Element e : elements) {
			if (e.hasText()) {
				for (String word : e.text().split(WORD_SEPERATOR)) {
					// remove all character which is not alphabet
					word = word.replaceAll(EXCEPT_ALPHABET, "");
					
					if (wordNCount.containsKey(word)) {
						// accumulate the count
						wordNCount.put(word, wordNCount.get(word) + 1);
					} else {
						// initial input
						wordNCount.put(word, 1);
					}
					
					wordCount++;
				}
			}
		}
		
		article.setWordsCount(wordCount);
		article.setWordNCount(wordNCount);
	}

}
