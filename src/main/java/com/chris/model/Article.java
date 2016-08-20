package com.chris.model;

import java.util.Map;

public class Article {
	private long id;
	private String topic;
	private String url;
	private boolean validity;
	private Map<String, Integer> words;
	private int wordsCount;
	private long similarId;
	private int similearityScore;
	private long createTime;
	private long processedTime;
	private long duration;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isValidity() {
		return validity;
	}

	public void setValidity(boolean validity) {
		this.validity = validity;
	}

	public Map<String, Integer> getWordNCount() {
		return words;
	}

	public void setWordNCount(Map<String, Integer> words) {
		this.words = words;
	}

	public int getWordsCount() {
		return wordsCount;
	}

	public void setWordsCount(int wordsCount) {
		this.wordsCount = wordsCount;
	}

	public long getSimilarId() {
		return similarId;
	}

	public void setSimilarId(long similarId) {
		this.similarId = similarId;
	}

	public int getSimilearityScore() {
		return similearityScore;
	}

	public void setSimilearityScore(int similearityScore) {
		this.similearityScore = similearityScore;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getProcessedTime() {
		return processedTime;
	}

	public void setProcessedTime(long processedTime) {
		this.processedTime = processedTime;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
