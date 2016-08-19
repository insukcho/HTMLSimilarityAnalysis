package com.chris.model;

public class Article {
	private long id;
	private String topic;
	private String url;
	private boolean validity;
	private long similarId;
	private int similearityScore;
	private long createTime;
	private long processedTime;
	
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
}
