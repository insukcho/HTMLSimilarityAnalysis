package com.chris.repository;

import java.util.List;

import com.chris.model.Article;

public interface ArticleRepository {
	List<Article> findAll() throws Exception;
	void save(Article article) throws Exception;
	void delete(Article article);
	void deleteAll() throws Exception;
}
