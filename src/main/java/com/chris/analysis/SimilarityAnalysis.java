package com.chris.analysis;

import com.chris.model.Article;

/**
 * Similarity analysis module using Jaccard index. See
 * <a href="https://en.wikipedia.org/wiki/Jaccard_index">https://en.wikipedia.
 * org/wiki/Jaccard_index</a>.
 * 
 * @author Chris
 *
 */
public class SimilarityAnalysis {

	public static void calculateSimilarity(Article newbie, Article exist) {
		int matchedCount = 0;
		for (String word : newbie.getWords()) {
			if (exist.getWords().contains(word)) {
				matchedCount++;
			}
		}

		float similarityScore = (float)matchedCount / (newbie.getWords().size() + exist.getWords().size() - matchedCount);

		// change if new comparison has better similarity score
		decideToSwitchSimilarityArticle(newbie, exist, similarityScore);
	}

	private static void decideToSwitchSimilarityArticle(Article newbie, Article exist, float similarityScore) {
		if (newbie.getSimilarityScore() < similarityScore) {
			newbie.setSimilarId(exist.getId());
			newbie.setSimilarityScore(similarityScore);
		}
		
		if (exist.getSimilarityScore() < similarityScore) {
			exist.setSimilarId(newbie.getId());
			exist.setSimilarityScore(similarityScore);
		}
	}
}
