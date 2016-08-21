package com.chris.model;

import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Article {
	private long id;
	private String topic;
	private String url;
	private boolean validity;
	private Set<String> words;
	private long similarId = -1;
	private float similarityScore = -1;
	private long createTime = -1;
	private long processedTime = -1;
	private long durationMS = -1;
	
	private Article() {} // prevent create this object without id and topic
	
	public Article(long id, String topic, long createTime) {
		this.id = id;
		this.topic = topic;
		this.url = topic;
		this.createTime = createTime;
	}

	public long getId() {
		return id;
	}
	
	public String getTopic() {
		return topic;
	}

	public String getUrl() {
		return url;
	}

	public boolean isValidity() {
		return validity;
	}

	public void setValidity(boolean validity) {
		this.validity = validity;
	}

	public Set<String> getWords() {
		return words;
	}

	public void setWords(Set<String> words) {
		this.words = words;
	}

	public long getSimilarId() {
		return similarId;
	}

	public void setSimilarId(long similarId) {
		this.similarId = similarId;
	}

	public float getSimilarityScore() {
		return similarityScore;
	}

	public void setSimilarityScore(float similarityScore) {
		this.similarityScore = similarityScore;
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

	public long getDurationMS() {
		return durationMS;
	}

	public void setDurationMS(long durationMS) {
		this.durationMS = durationMS;
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

	@Override
	public String toString() {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		try {
			json = ow.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return json;
	}
}
