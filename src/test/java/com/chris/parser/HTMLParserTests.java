package com.chris.parser;

import java.io.IOException;
import java.util.Set;

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
				+ "<body><p>Parsed HTML into a doc. HTML is a markup language. </p></body></html>";

		Article article = new Article(1);
		HTMLParser.setAllWordsInBody(article, Jsoup.parse(html));
		Set<String> words = article.getWords();
		
		Assert.assertTrue(words.contains("Parsed"));
		Assert.assertTrue(words.contains("HTML"));
		Assert.assertTrue(words.contains("doc"));
		Assert.assertEquals(8, words.size());
	}

}
