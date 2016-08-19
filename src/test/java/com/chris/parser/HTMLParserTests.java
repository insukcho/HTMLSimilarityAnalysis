package com.chris.parser;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class HTMLParserTests {
	private final static String TEST_URL = "https://www.google.com/";
	
	@Test
	public void testHtmlParser() throws IOException {
		Document doc = HTMLParser.htmlParsing(TEST_URL);
		assertThat(doc.getAllElements().size(), equalTo(91));
	}
	
	@Test
	public void testGetAllWordsInBody() {
		String html = "<html><head><title>First parse</title></head>"
				  + "<body><p>Parsed HTML into a doc.</p></body></html>";
		
		List<String> allWordsInBody = HTMLParser.getAllWordsInBody(Jsoup.parse(html));
		assertThat(allWordsInBody, hasItem("Parsed"));
		assertThat(allWordsInBody, hasItem("HTML"));
		assertThat(allWordsInBody, hasItem("doc."));
	}
	
	
}
