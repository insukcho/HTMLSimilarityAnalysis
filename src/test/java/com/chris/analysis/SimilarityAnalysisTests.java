package com.chris.analysis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.chris.model.Article;
import com.chris.repository.FakeArticleRepository;

public class SimilarityAnalysisTests {

	@Test
	public void testCalculateSimilarity() throws Exception {
		Set<String> mock = new HashSet<>();
		mock.add("I");
		mock.add("am");
		mock.add("programmer");
		Article newbie = new Article(1, "", 1);
		newbie.setWords(mock);
		Article exist = new Article(2, "", 2);
		exist.setWords(mock);
		
		SimilarityAnalysis.calculateSimilarity(newbie, exist, new FakeArticleRepository());
		
		Assert.assertEquals(1.0, newbie.getSimilarityScore(), 0);
		Assert.assertEquals(2, newbie.getSimilarId());
		
		Assert.assertEquals(1.0, exist.getSimilarityScore(), 0);
		Assert.assertEquals(1, exist.getSimilarId());
		
		Set<String> newMock = new HashSet<>();
		newMock.add("I");
		newMock.add("am");
		newMock.add("not");
		newMock.add("programmer");
		
		Article secnodNewbie = new Article(3, "", 3);
		secnodNewbie.setWords(newMock);
		
		SimilarityAnalysis.calculateSimilarity(newbie, secnodNewbie, new FakeArticleRepository());
		
		Assert.assertEquals(1.0, newbie.getSimilarityScore(), 0);
		Assert.assertEquals(2, newbie.getSimilarId());
		
		Assert.assertEquals(0.75, secnodNewbie.getSimilarityScore(), 0);
		Assert.assertEquals(1, secnodNewbie.getSimilarId());
		
	}
}
