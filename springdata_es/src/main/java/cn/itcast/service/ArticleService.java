package cn.itcast.service;

import cn.itcast.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
	public void save(Article article);

	public void delete(Article article);

	public Article findOne(Integer id);

	public Iterable<Article> findAll();

	public Page<Article> findAll(Pageable pageable);

	public List<Article> findByTitle(String title);

	public Page<Article> findByTitle(String title, Pageable pageable);

	public Page<Article> findByContent(String content, Pageable pageable);

	Page<Article> testTermQuery(String attr,String keyword);

	Page<Article> testWildcardQuery(String attr,String keyword);

	Page<Article> testQueryStringQuery(String attr,String keyword);
}
