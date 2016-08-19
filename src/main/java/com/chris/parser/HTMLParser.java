package com.chris.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {
	
	private final static String BODY_TAG_NAME = "body";
	private final static String WORD_SEPERATOR = " ";
	
	public static Document htmlParsing(String url) throws IOException {
		return Jsoup.connect(url).get();
	}
	
	public static List<String> getAllWordsInBody(Document doc) {
		List<String> words = new ArrayList<String>();
		
		Elements elements = doc.getElementsByTag(BODY_TAG_NAME);
		for(Element e: elements) {
			if(e.hasText()) {
				words.addAll(Arrays.asList(e.text().split(WORD_SEPERATOR)));
			}
		}
		
		return words;
	}

}
