package com.chris.parser;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import com.chris.model.Article;

public class HTMLParserTests {
	@Test
	public void testHtmlParser() throws IOException {
		Document doc = HTMLParser.htmlParsing("https://www.google.com/");
		Assert.assertEquals("Google", doc.title());
	}

	@Test
	public void testGetAllWordsInBody() {
		String html = "<html><head><title>First parse</title></head>"
				+ "<body><p>Parsed HTML into a doc. HTML is markup language. </p></body></html>";

		Article article = new Article();
		HTMLParser.setAllWordsInBody(article, Jsoup.parse(html));
		Map<String, Integer> wordNCount = article.getWordNCount();
		
		Assert.assertTrue(wordNCount.containsKey("Parsed"));
		Assert.assertEquals(new Integer(1), wordNCount.get("Parsed"));
		Assert.assertTrue(wordNCount.containsKey("HTML"));
		Assert.assertEquals(new Integer(2), wordNCount.get("HTML"));
		Assert.assertTrue(wordNCount.containsKey("doc"));
		Assert.assertEquals(new Integer(1), wordNCount.get("doc"));
	}

}
